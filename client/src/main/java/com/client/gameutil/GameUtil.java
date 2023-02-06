/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.gameutil;

import com.client.command.Command;
import com.client.command.Generics;
import com.client.form.SplashScreen;
import com.client.form.gameframe.GameFrame;
import com.client.types.Objective;
import com.client.types.Room;
import com.client.types.Story;
import com.client.types.rooms.Earth;
import com.client.types.rooms.GoSciSector;
import com.client.types.rooms.Maintenance;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe che definisce il comportamento dei metodi che permettono il corretto
 * funzionamento del gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
@SuppressWarnings("")
public abstract class GameUtil extends Game {

    /**
     * Metodo che si occupa di inizializzare la connessione con il Server.
     *
     * @throws UnknownHostException nel caso in cui l'host sia
     * sconosciuto/errato.
     * @throws IOException per possibili problemi di I/O con il Server.
     *
     * @see Game#setSocket(java.net.Socket)
     * @see Game#setOutputToServer(java.io.PrintWriter)
     * @see Game#setInputFromServer(java.io.BufferedReader)
     */
    public void initConnectionWithServer()
            throws UnknownHostException, IOException {

        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        this.setSocket(new Socket(addr, 6666));
        System.out.println("socket = " + this.getSocket());
        this.setInputFromServer(
                new BufferedReader(
                        new InputStreamReader(
                                this.getSocket().getInputStream())));

        this.setOutputToServer(
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        this.getSocket().getOutputStream())),
                        true));
    }

    /**
     * Metodo che si occupa di istanziare tutti gli oggetti, stanze, obbiettivi
     * del gioco, imposta la stanza attuale e crea la mappa di gioco.
     *
     * @param db il database dal quale recuperare le informazioni.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @see DB
     */
    public void init(DB db) throws SQLException { //primo avvio del gioco
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);
        this.setObjectiveList(Creator.createObjectives(db));
        this.setItemList(Creator.createItems(db, this.getObjectiveList()));
        this.setRoomList(Creator.createRooms(db, this.getItemList()));

        this.setActualRoom(this.getRoomList().get(0)); //dormitori = 0

        Room.createMap(this.getRoomList(), db);
        splash.dispose();
    }

    /**
     * Metodo che si occupa di rimuovere i doppioni in fase di salvataggio.
     *
     * @param rooms la lista contenente tutte le stanze.
     *
     * @return la lista delle stanze senza doppioni
     */
    public List<Room> roomListCleaner(List<Room> rooms) {

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).equals(this.getActualRoom())) {
                rooms.remove(i);
                break;
            }
        }
        rooms.add(0, this.getActualRoom());

        return rooms;
    }

    /**
     * Metodo che si occupa di leggere il file del salvataggio e caricare tutte
     * le informazioni scritte sul file.
     *
     * @param cmd il comando che permette il corretto caricamento delle varie
     * istanze.
     * @param game variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     * @throws ClassNotFoundException nel caso in cui la classe da leggere non
     * esista.
     */
    public void loadGame(Command cmd, GameFrame game)
            throws FileNotFoundException, IOException, ClassNotFoundException {

        SplashScreen splash = new SplashScreen();
        this.setItemList(new LinkedList<>());
        splash.setVisible(true);

        FileInputStream inputFile = new FileInputStream("resources/saves/save.dat");
        ObjectInputStream inputStream = new ObjectInputStream(inputFile);

        this.setRoomList(cmd.loadRooms(inputStream));
        this.setObjectiveList(cmd.loadObjectives(inputStream));
        this.setGameStarted(cmd.loadExecution(inputStream));
        cmd.loadGameFrame(inputStream);
        this.setStory(cmd.loadStory(inputStream));

        for (int i = 0; i < this.getRoomList().size(); i++) {
            this.getItemList().addAll(this.getRoomList().get(i).getItems());
        }

        for (int i = 0; i < game.getInventory().size(); i++) {
            this.getItemList().add(game.getInventory().get(i));
        }

        //stanza del salvataggio
        this.setActualRoom(this.getRoomList().get(0));

        splash.dispose();
    }

    /**
     * Metodo che rappresenta il gioco vero e proprio.
     *
     * <p>
     * Inizialmente questo metodo imposta il nome della stanza attuale e rende
     * visibile il GameFrame, dopodichè effettua un ciclo in attesa di ricevere
     * una stringa dalla JTextField di input del GameFrame, quando viene premuto
     * il tasto ENTER, manderà la stringa al server in modo che esso possa
     * riconoscere uno dei comandi implementati; se il comando è corretto il
     * server invierà l'id del comando al client (questo programma) che chiamerà
     * il metodo corrispondente dalla classe Command, rendendo cosi
     * l'applicazione giocabile.
     *
     * <br>
     *
     * In caso di comando errato o non riconosciuto verrà riprodotto un suono di
     * Alert e stamperà un messaggio di errore.</p>
     *
     * @param game il frame di gioco.
     * @param db il database dal quale recuperare nuovi pezzi di storia e
     * obbiettivi.
     * @param audio l'audio dell'intero gioco.
     * @param cmd i vari comandi che permettono la corretta esecuzione del
     * gioco.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws IOException per possibili problemi di I/O con Server.
     * @throws InterruptedException per possibili problemi con i Thread.
     */
    public void playGame(GameFrame game, AudioPlayer audio, Command cmd, DB db)
            throws SQLException, InterruptedException, IOException {

        boolean startTimer = false;
        int commandId = 0;

        game.setRoomName(this.getActualRoom().getName());
        game.setVisible(true);

        String str = null;
        String serverResponse;
        String[] strIn;

        if (!this.isGameStarted()) {
            cmd.help(this.isGameStarted());
        }

        do {
            Thread.sleep(1);
            if (game.isEnterPressed() == true) {
                str = game.getInput();
            }
            game.setEnterPressed(false);

            //caricamento dal salvataggio
            if (this.isGameStarted() == true && startTimer == false) {
                this.setStoryChapter(this.getStory().getChapter());

                game.setStory(this.getStory());
                game.getOxygenTimer()
                        .setSeconds(
                                (game.getOxygenBar().getValue() + 1) * 60000);

                if (this.getActualRoom().getClass() != Earth.class) {
                    game.startOxygenTimer();
                }

                game.setCurrentObjective(this.getStory().getObjectives());
                game.setAvailableCommands(this.getActualRoom()
                        .getAvailableCommands());

                startTimer = true;
            }

            if (str != null && str.length() > 0) {

                this.getOutputToServer().println(str);
                str = "";

                serverResponse = this.getInputFromServer().readLine();
                strIn = serverResponse.split(" ");

                commandId = Integer.parseInt(strIn[0]);
                game.setInput("");

                for (int i = 1; i < strIn.length; i++) {
                    str = str + strIn[i] + " ";
                }
                str = str.trim();
                
                if (commandId == 8 && this.isGameStarted() == false) {
                    cmd.quit(this.getSocket(), db);
                } else if (commandId == -1) {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(game, "Comando "
                            + "non valido!", "Errore", 2);

                    //primo avvio del gioco
                } else if (commandId == 0 && this.isGameStarted() == false) {
                    this.setGameStarted(true);

                    this.setStoryChapter(1);
                    this.setStory(
                            new Story(
                                    this.getStoryChapter(),
                                    db,
                                    this.getObjectiveList()));

                    game.setStory(this.getStory());
                    game.setStoryBoard(this.getStory().getPieceStory());

                    if (this.getStory().getLenStory() > 1) {
                        cmd.setCMDblocked(true);
                    }

                    this.getActualRoom().updateAvailableCommands(game, cmd);
                    game.setAvailableCommands(
                            this.getActualRoom().getAvailableCommands());

                    startTimer = true;

                } else if (this.isGameStarted() == false) {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(game, "La partita non è "
                            + "ancora iniziata!\nUsa il "
                            + "comando \"avvia\"!", "Errore", 2);
                } else if (this.isGameStarted() == true) {

                    this.play(commandId, str, cmd, game, audio);

                    this.getActualRoom().updateAvailableCommands(game, cmd);
                    game.setAvailableCommands(
                            this.getActualRoom().getAvailableCommands());
                }
            }

            // controlla completamento obbiettivo
            this.checkCompletationObjective(game, db, cmd);

            str = "";

        } while (commandId != 8); // esci
    }

    /**
     * Metodo che permette il corretto funzionamento del gioco.
     *
     * <p>
     * Questo metodo viene chiamato dal metodo <code>playGame</code> ed effettua
     * uno switch sull'id del comando passato come parametro per chiamare il
     * comando corrispondente.</p>
     *
     * @param commandId l'id del comando da chiamare
     * @param str la stringa restituita dal server
     * @param cmd la classe Command per chiamare il comando associato all'id
     * @param game il frame principale di gioco
     * @param audio per garantire la corretta riproduzione di audio
     *
     * @see Command
     * @see GameFrame
     * @see AudioPlayer
     */
    private void play(int commandId, String str, Command cmd, GameFrame game,
            AudioPlayer audio) {

        switch (commandId) {
            case -1: // comando non valido
                audio.playAlert();
                JOptionPane.showMessageDialog(game, "Comando "
                        + "non valido!", "Errore", 2);
                break;
            case 1: // vai
                if (!str.equals("")) {
                    this.checkGOTO(str, cmd, audio, game);
                } else {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(game,
                            "Ok vado... ma dove?", "Errore", 2);
                }
                break;
            case 2: // prendi
                if (!str.equals("")) {
                    //System.out.println(str); // sys di debug
                    cmd.pickUp(this.getActualRoom(), str);
                } else {
                    JOptionPane.showMessageDialog(game,
                            "Hai preso aria!", "Errore", 3);
                    audio.playAlert();
                    JOptionPane.showMessageDialog(game,
                            "L'aria ti è volata via dalle mani, "
                            + "l'hai persa!",
                            "Errore", 2);
                }
                break;
            case 3: // guarda
                if (!str.equals("")) {
                    cmd.look(this.getActualRoom(), str);
                } else {
                    cmd.look(this.getActualRoom());
                }
                break;
            case 4: // help
                cmd.help(this.isGameStarted());
                break;
            case 5: // combina
                String items[];
                if (str.contains(" e ")) {
                    items = str.split(" e ");
                } else if (str.contains(" con ")) {
                    items = str.split(" con ");
                } else {
                    items = str.split(" ");
                }

                if (items.length == 2) {
                    if (items[0] != null && items[1] != null) {
                        cmd.combine(items[0].trim(), items[1].trim());
                    }
                } else {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(game,
                            "Non posso combinare qualcosa con il nulla!",
                            "Errore", 2);
                }
                break;

            case 6: // usa
                if (!str.equals("")) {
                    cmd.use(
                            this.getActualRoom(),
                            this,
                            this.getRoomList(),
                            str);
                } else {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(game, "Cosa uso?",
                            "Errore", 2);
                }
                break;
            case 7: // leggi
                cmd.readStory();
                break;
        }
    }

    /**
     * Metodo che si occupa di controllare se il comando GOTO è andato a buon
     * fine.
     *
     * <p>
     * Questo metodo si occupa di chiamare il comando <b>GOTO</b> e di
     * controllarne l'esito effettuando uno switch sull'id restituito in modo
     * tale da permettere l'eventuale stampa di messaggi di errore da far
     * visualizzare al giocatore.</p>
     *
     * @param str la stringa restituita dal server
     * @param cmd la classe Command per chiamare il comando associato all'id
     * @param game il frame principale di gioco
     * @param audio per garantire la corretta riproduzione di audio
     *
     * @see Command#goTo(com.client.types.Room, java.lang.String)
     */
    private void checkGOTO(String str, Command cmd, AudioPlayer audio,
            GameFrame game) {

        Generics<Integer, Room> goTo = cmd.goTo(this.getActualRoom(), str);
        Integer errorCode = goTo.getK();
        GoSciSector goSci = null;
        Maintenance electrical = null;

        switch (errorCode) {
            case 0:
                Room room = goTo.getV();

                if (room != null) {
                    for (int i = 0; i < this.getRoomList().size(); i++) {
                        if (this.getRoomList().get(i).getName()
                                .toLowerCase().equals("settore go sci")) {
                            goSci = (GoSciSector) this.getRoomList().get(i);

                        } else if (this.getRoomList().get(i)
                                .getName().toLowerCase()
                                .equals("sala manutenzione e elettrica")) {
                            electrical = (Maintenance) this.getRoomList().get(i);
                        }

                        if (goSci != null && electrical != null) {
                            break;
                        }
                    }

                    if (room.equals(goSci) && (goSci.isLocked())) {
                        audio.playAlert();
                        JOptionPane.showMessageDialog(game,
                                "Le porte del Settore Go Sci "
                                + "sono tutte bloccate!"
                                + "\nCerca un badge di "
                                + "un dipendente del "
                                + "settore Go Sci!",
                                "Errore", 2);
                    } else if (room.equals(electrical)
                            && (!electrical.isFirstTimeEntered())) {
                        electrical.setFirstTimeEntered(true);

                        this.setActualRoom(room);
                        game.setRoomName(this.getActualRoom().getName()
                                .toUpperCase());
                        game.setStoryBoard(("Sei entrato nella stanza: "
                                + this.getActualRoom().getName()));

                        game.getLifeBar().setValue(game.
                                getLifeBar().getValue() - 25);

                        game.getLifeBar().setString(game
                                .getLifeBar().getValue() + "% Vita");
                    } else {
                        this.setActualRoom(room);
                        game.setRoomName(this.getActualRoom()
                                .getName().toUpperCase());
                        game.setStoryBoard(("Sei entrato nella stanza: "
                                + this.getActualRoom().getName()));
                    }
                }
                break;
            case 1:
                audio.playAlert();
                JOptionPane.showMessageDialog(game,
                        "C'è un muro da quella parte..."
                        + "\nNon puoi ancora "
                        + "oltrepassare i muri!",
                        "Errore", 2);
                break;
            case 2:
                audio.playAlert();
                JOptionPane.showMessageDialog(game,
                        "Stanza non valida!",
                        "Errore", 2);
                break;
            case 3:
                audio.playAlert();
                JOptionPane.showMessageDialog(game,
                        "Sei già nella stanza in cui "
                        + "vuoi andare!",
                        "Errore", 2);
                break;
            case -1: //comando bloccato quindi non fare nulla
                break;
        }
    }

    /**
     * Metodo che controlla se l'obbiettivo è stato completato o meno.
     *
     * @param game il frame di gioco.
     * @param db il database dal quale recuperare nuovi pezzi di storia e
     * obbiettivi.
     * @param cmd il comando che permette il corretto salvataggio.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws IOException per possibili problemi di I/O con il file di
     * salvataggio.
     */
    private void checkCompletationObjective(GameFrame game, DB db, Command cmd)
            throws SQLException, IOException {

        Objective objective;

        if (this.getStory() != null) {
            for (int i = 0; i < this.getStory().getObjectives().size(); i++) {
                objective = this.getStory().getObjectives().get(i);
                objective.checkCompleted(this.getItemList(), game);
                if (objective.isCompleted()) {
                    this.getStory().getObjectives().remove(objective);

                    // prossimo pezzo di storia + obbiettivi
                    if (this.getStory().getObjectives().isEmpty()) {
                        this.setStoryChapter(this.getStoryChapter() + 1);
                        this.setStory(new Story(
                                this.getStoryChapter(),
                                db,
                                this.getObjectiveList()));

                        this.getStory().setSubChapter(0);

                        if (this.getStory().getLenStory() > 1) {
                            cmd.setCMDblocked(true);
                        }

                        game.setStory(this.getStory());
                        game.setStoryBoard(this.getStory().getPieceStory());

                    }
                    game.setCurrentObjective(this.getStory().getObjectives());
                    cmd.save(
                            this.roomListCleaner(this.getRoomList()),
                            this.getObjectiveList(),
                            this.isGameStarted(),
                            game,
                            this.getStory());
                    break;
                }
            }
        }
    }
}
