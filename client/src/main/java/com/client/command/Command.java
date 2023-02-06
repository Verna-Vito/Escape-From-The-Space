/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.command;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.gameutil.Game;
import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.Room;
import com.client.types.Story;
import com.client.types.items.Badge;
import com.client.types.items.Bandage;
import com.client.types.items.Computer;
import com.client.types.items.ElectricalPanel;
import com.client.types.items.Fuel;
import com.client.types.items.GeigerCounter;
import com.client.types.items.OxygenTank;
import com.client.types.items.ProtectiveWear;
import com.client.types.items.RoomMap;
import com.client.types.items.SpaceShip;
import com.client.types.rooms.Earth;
import com.client.types.rooms.GoSciSector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Questa classe si occupa di definire il comportamento che i vari comandi
 * dell'applicazione devono avere.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 * @see Room
 */
public class Command {

    /**
     * Attributo di tipo AudioPlayer che contiene le informazioni sull'audio.
     *
     * @see AudioPalyer
     */
    private final AudioPlayer audio;

    /**
     * Attributo di tipo GameFrame che permette la corretta visualizzazione di
     * eventuali messaggi di errore/successo e la manipolazione degli elementi
     * visivi del frame a seconda delle operazioni effettuate/da effettuare.
     *
     * @see GameFrame
     */
    private final GameFrame gameframe;

    /**
     * Attributo di tipo booleano che indica se i comandi sono stati bloccati o
     * meno.
     */
    private boolean CMDblocked;

    /**
     * Costruttore che si occupa di assegnare il parametro <i>audio</i> al
     * proprio attributo privato in modo da permettere la corretta riproduzione
     * di eventuali audio e il parametro <i>gf</i> al proprio attributo privato
     * in modo da permettere la corretta visualizzazione correttamente i
     * messaggi d'errore/riuscita di un comando.
     *
     * @param audio l'audio player per la corretta riproduzione dei suoni.
     * @param gf la rootPane per la corretta visualizzazione dei messaggi.
     *
     * @see GameFrame
     * @see AudioPlayer
     */
    public Command(AudioPlayer audio, GameFrame gf) {
        this.audio = audio;
        this.gameframe = gf;
        this.CMDblocked = false;
    }

    /**
     * Metodo pubblico che permette di cambiare lo stato dell'attributo
     * CMDblocked a <code>true</code> se i comandi devono essere bloccati;
     * altrimenti <code>false</code>.
     *
     * @param CMDblocked sarà <code>true</code> se i comandi devono essere
     * bloccati; <code>false</code> altrimenti.
     */
    public void setCMDblocked(boolean CMDblocked) {
        this.CMDblocked = CMDblocked;
    }

    /**
     * Metodo pubblico che restiuisce il valore della variabile CMDblocked.
     *
     * @return <code>true</code> se i comandi sono bloccati; <code>false</code>
     * altrimenti.
     */
    public boolean getCMDblocked() {
        return this.CMDblocked;
    }

    /**
     * Metodo che verifica se la stanza nella quale si vuole andare esiste o
     * meno.
     *
     * <p>
     * In particolare, verifica se la stanza ha un'altra stanza nei punti
     * cardinali <b>EST</b>, <b>OVEST</b>, <b>NORD</b> e <b>SUD</b>, e se la
     * stringa inserita dall'utente è simile all'espressione regolare
     * corrispondente alla direzione o se il nome della stanza è uguale alla
     * stringa di input, se questo controllo va a buon fine allora restituisce
     * la stanza in cui si ha intenzione di andare; altrimenti inizia un'altra
     * sequenza di controlli, ma sugli alias della stanza, se uno di questi
     * controlli va a buon fine restituisce la stanza in cui si vuole andare,
     * altrimenti restituisce la stanza in cui si è stampando a video anche un
     * messaggio di errore accompagnato da un suono di alert.
     * </p>
     *
     * <p>
     * Codici e loro significati:
     * <ul>
     * <li>-1 - comando bloccato;</li>
     * <li>0 - stanza valida;</li>
     * <li>1 - muro presente in quella direzione;</li>
     * <li>2 - stanza non valida;</li>
     * <li>3 - si è già nella stanza in cui si vuole andare;</li>
     * </ul>
     *
     * <p>
     * Algoritmo di ricerca usato: <b><i>Ricerca con sentinella</i></b>.
     * </p>
     *
     * @param actualRoom stanza in cui si è in questo momento.
     * @param toRoom stringa (in input) che identifica in quale stanza si vuole
     * andare.
     *
     * @return il codice di errore e la stanza in cui si vuole andare/quella in
     * cui si è.
     *
     * @see Room
     * @see AudioPlayer#playAlert()
     */
    public Generics<Integer, Room> goTo(Room actualRoom, String toRoom) {
        final String EAST = "e|es|est";
        final String WEST = "o|ov|ove|ovest";
        final String NORTH = "n|no|nor|nord";
        final String SOUTH = "s|su|sud";
        boolean flag = false;
        boolean flag1 = false;
        Room tmp = actualRoom;
        Generics<Integer, Room> toRoomMap = new Generics<>();

        if (!CMDblocked) {
            if (tmp.getName().toLowerCase().equals(toRoom.toLowerCase())) {
                flag1 = true;
                toRoomMap.put(3, tmp);
            } else {
                if (!tmp.getAlias().isEmpty()) {
                    for (int i = 0; i < tmp.getAlias().size(); i++) {
                        if (toRoom.toLowerCase().equals(tmp.getAlias().get(i).toLowerCase())) {
                            toRoomMap.put(3, tmp);
                            flag = true;
                            break;
                        }
                    }
                }
            }

            if (tmp.getEast() != null && (toRoom.matches(EAST)
                    || toRoom.equals(tmp.getEast().getName().toLowerCase()))) {
                toRoomMap.put(0, tmp.getEast());
                flag = true;
            } else if (tmp.getWest() != null && (toRoom.matches(WEST)
                    || toRoom.equals(tmp.getWest().getName().toLowerCase()))) {
                toRoomMap.put(0, tmp.getWest());
                flag = true;
            } else if (tmp.getNorth() != null && (toRoom.matches(NORTH)
                    || toRoom.equals(tmp.getNorth().getName().toLowerCase()))) {
                toRoomMap.put(0, tmp.getNorth());
                flag = true;
            } else if (tmp.getSouth() != null && (toRoom.matches(SOUTH)
                    || toRoom.equals(tmp.getSouth().getName().toLowerCase()))) {
                toRoomMap.put(0, tmp.getSouth());
                flag = true;
            } else if ((toRoom.matches(SOUTH) && (tmp.getSouth() == null))
                    || (toRoom.matches(NORTH) && (tmp.getNorth() == null))
                    || (toRoom.matches(WEST) && (tmp.getWest() == null))
                    || (toRoom.matches(EAST) && (tmp.getEast() == null))) {

                flag1 = true;
                toRoomMap.put(1, tmp);

            } else {
                // Riempie la lista temporanea con gli Alias della stanza a est
                if (tmp.getEast() != null) {

                    for (int i = 0; i < tmp.getEast().getAlias().size(); i++) {
                        if (toRoom.toLowerCase().equals(tmp.getEast().getAlias().get(i).toLowerCase())) {
                            toRoomMap.put(0, tmp.getEast());
                            flag = true;
                            break;
                        }
                    }
                }

                // Riempie la lista temporanea con gli Alias della stanza a ovest
                if (tmp.getWest() != null) {

                    for (int i = 0; i < tmp.getWest().getAlias().size(); i++) {
                        if (toRoom.toLowerCase().equals(tmp.getWest().getAlias().get(i).toLowerCase())) {
                            toRoomMap.put(0, tmp.getWest());
                            flag = true;
                            break;
                        }
                    }
                }

                // Riempie la lista temporanea con gli Alias della stanza a nord
                if (tmp.getNorth() != null) {

                    for (int i = 0; i < tmp.getNorth().getAlias().size(); i++) {
                        if (toRoom.toLowerCase().equals(tmp.getNorth().getAlias().get(i).toLowerCase())) {
                            toRoomMap.put(0, tmp.getNorth());
                            flag = true;
                            break;
                        }
                    }
                }

                // Riempie la lista temporanea con gli Alias della stanza a sud
                if (tmp.getSouth() != null) {

                    for (int i = 0; i < tmp.getSouth().getAlias().size(); i++) {
                        if (toRoom.toLowerCase().equals(tmp.getSouth().getAlias().get(i).toLowerCase())) {
                            toRoomMap.put(0, tmp.getSouth());
                            flag = true;
                            break;
                        }
                    }
                }
            }

            if (!flag && !flag1) { // stanza non valida
                toRoomMap.put(2, null);
            }
        } else {    //I comandi sono bloccati
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Devi finire di leggere "
                    + "la storia!", "Attenzione!", 2);
            toRoomMap.put(-1, null);
        }
        return toRoomMap;
    }

    /**
     * Metodo che compie diversi controlli sull'oggetto che si vuole prendere.
     *
     * <p>
     * In particolare, verifica se la stanza in cui si è ha degli oggetti se si,
     * allora per ogni oggetto presente si verifica se il nome corrisponde alla
     * stringa data in input dall'utente e se può essere preso, se si, allora
     * viene rimosso dalla stanza e stampato a video un messaggio di successo,
     * altrimenti, se non è prendibile, stampa un messaggio di errore
     * accompagnato da un suono di alert; se invece il nome dell'oggetto
     * presente non corrisponde alla stringa, si procede con il controllo degli
     * alias dell'oggetto e si verifica se l'alias del oggetto corrisponde alla
     * stringa e se è prendibile, se lo è l'oggetto viene rimosso dalla stanza e
     * viene visualizzato un messaggio di successo, altrimenti se non è
     * prendibile, stampa un messaggio di errore accompagnato da un suono di
     * alert; se la sentinella rimane inizializzata a <code>false</code> allora
     * stampa un messaggio di errore accompagnato da un suono di alert, se
     * invece la stanza non ha oggetti, stampa un messaggio di errore
     * accompagnato da un suono di alert.
     * </p>
     *
     * <p>
     * Algoritmo di ricerca usato: <b><i>Ricerca con sentinella</i></b>.
     * </p>
     *
     * @param actualRoom stanza in cui si è in questo momento.
     * @param item stringa (in input) che identifica quale oggetto si vuole
     * prendere.
     *
     * @see Item
     * @see AudioPlayer#playAlert()
     */
    public void pickUp(Room actualRoom, String item) {
        Item item1;
        boolean flag = false;

        if (!CMDblocked) {
            if (!actualRoom.getItems().isEmpty()) {

                for (int i = 0; i < actualRoom.getItems().size(); i++) {
                    item1 = actualRoom.getItems().get(i);

                    if (item1.getName().toLowerCase().equals(item.toLowerCase())) {
                        if (item1.isPickupable()) {
                            actualRoom.getItems().remove(item1);
                            if (gameframe.getStoryBoard().contains("Vedo degli oggetti")) {
                                this.look(actualRoom);
                            }
                            JOptionPane.showMessageDialog(gameframe, "Item preso "
                                    + "con successo:\n" + item1.getName(),
                                    "Item preso", 3);
                            gameframe.setInventory(item1);
                            flag = true;
                        } else {
                            audio.playAlert();
                            JOptionPane.showMessageDialog(gameframe,
                                    "Quest'oggetto non può essere preso",
                                    "Errore", 2);
                            flag = true;
                        }
                    } else if (!item1.getAlias().isEmpty()) {
                        for (int j = 0; j < item1.getAlias().size(); j++) {
                            if (item1.getAlias().get(j).toLowerCase().equals(item.toLowerCase())) {
                                if (item1.isPickupable()) {
                                    actualRoom.getItems().remove(item1);
                                    if (gameframe.getStoryBoard().contains("Vedo degli oggetti")) {
                                        this.look(actualRoom);
                                    }
                                    JOptionPane.showMessageDialog(gameframe,
                                            "Item preso con successo:\n"
                                            + item1.getName(),
                                            "Item preso", 3);
                                    gameframe.setInventory(item1);
                                    flag = true;
                                } else {
                                    audio.playAlert();
                                    JOptionPane.showMessageDialog(gameframe,
                                            "Quest'oggetto non può essere preso",
                                            "Errore", 2);
                                    flag = true;
                                }
                            }
                        }
                    }
                }
                if (flag == false) {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(gameframe, "Quest'oggetto non "
                            + "esiste in questa stanza", "Errore", 2);
                }

            } else {
                audio.playAlert();
                JOptionPane.showMessageDialog(gameframe, "Questa stanza non ha "
                        + "oggetti", "Nessun oggetto presente", 2);
            }
        } else {    //se i comandi sono bloccati
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Devi finire di leggere "
                    + "la storia!", "Attenzione!", 2);
        }
    }

    /**
     * Metodo che si occupa di stampare a video una breve descrizione di quello
     * che è presente nella stanza ed eventuali oggetti contenuti in essa.
     *
     * @param actualRoom la stanza da guardare.
     */
    public void look(Room actualRoom) {
        List<String> items = new ArrayList<>();

        if (!CMDblocked) {
            if (actualRoom.getClass() != Earth.class) {
                if (actualRoom.getItems().isEmpty()) {
                    gameframe.setStoryBoard(actualRoom.getDesc() + "<br/>"
                            + actualRoom.getLook() + "<br/>Non sono presenti oggetti "
                            + "in questa stanza.");
                } else {
                    actualRoom.getItems().stream()
                            .map(i -> i.getName())
                            .forEach(name -> items.add(name));

                    gameframe.setStoryBoard(actualRoom.getDesc() + "<br/>"
                            + actualRoom.getLook() + "<br/>Vedo degli oggetti: "
                            + items.toString().replace("[", "").replace("]", "").trim());
                }
            } else {
                gameframe.setStoryBoard(actualRoom.getDesc() + "<br/>" + actualRoom.getLook());
            }
        } else {    //se i comandi sono bloccati
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Devi finire di leggere "
                    + "la storia!", "Attenzione!", 2);
        }
    }

    /**
     * Metodo che si occupa di stampare una breve descrizione sull'oggetto
     * appena guardato o compiere delle azioni.
     *
     * @param actualRoom la stanza attuale
     * @param item la stringa che indica quale oggetto si vuole guardare
     */
    public void look(Room actualRoom, String item) {
        Item item1;
        boolean flag = false;

        if (!CMDblocked) {
            if (!actualRoom.getItems().isEmpty()) {
                for (int i = 0; i < actualRoom.getItems().size(); i++) {
                    if (actualRoom.getItems().get(i).isLookable()) {
                        if (actualRoom.getItems().get(i).getName().toLowerCase().startsWith("mappa")) {

                            if (actualRoom.getItems().get(i).getName().toLowerCase().equals(item.toLowerCase())) {
                                RoomMap.look(gameframe, i, actualRoom);
                                flag = true;
                                break;

                            } else {
                                for (int j = 0; j < actualRoom.getItems().get(i).getAlias().size(); j++) {
                                    if (actualRoom.getItems().get(i).getAlias().get(j).toLowerCase()
                                            .equals(item.toLowerCase())) {
                                        RoomMap.look(gameframe, i, actualRoom);
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        } else if (actualRoom.getItems().get(i).getName().toLowerCase().equals("navicella")) {

                            if (actualRoom.getItems().get(i).getName().toLowerCase().equals(item.toLowerCase())) {
                                item1 = actualRoom.getItems().get(i);
                                SpaceShip.look(item1, gameframe, audio);
                                flag = true;
                                break;

                            } else {
                                for (int j = 0; j < actualRoom.getItems().get(i).getAlias().size(); j++) {
                                    if (actualRoom.getItems().get(i).getAlias().get(j).toLowerCase()
                                            .equals(item.toLowerCase())) {
                                        item1 = actualRoom.getItems().get(i);
                                        SpaceShip.look(item1, gameframe, audio);
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if (!flag) {
                    JOptionPane.showMessageDialog(gameframe, "L'oggetto con questo "
                            + "nome: " + item + " non può essre guardato!\n", "Errore", 0);
                }
            }
        } else {    //se i comandi sono bloccati
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Devi finire di leggere "
                    + "la storia!", "Attenzione!", 2);
        }
    }

    /**
     * Metodo che si occupa di far visualizzare una breve descrizione su come
     * usare questo gioco.
     *
     * @see Command#help(boolean)
     */
    private void help() {
        int option = JOptionPane.showConfirmDialog(gameframe, "Benvenuto in questa avventura!\n\n"
                + "In questa breve panoramica, capirai \n"
                + "come interpretare la finestra di gioco e come poter giocare!", "Aiuto", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            option = JOptionPane.showConfirmDialog(gameframe,
                    "- in alto a sinistra c'è la barra della vita;\n"
                    + "    devi stare attento a non farla arrivare a 0 altrimenti morirai!\n"
                    + "- in alto al centro c'è il timer di gioco totale.\n"
                    + "- in alto a destra c'è la barra dell'ossigeno;\n"
                    + "    quando questa barra arriverà a 0 inizierai a perdere vita!\n"
                    + "- a sinistra ci sono la lista degli obbiettivi correnti\n"
                    + "    e la lista dei comandi disponibili all'interno della stanza attuale.\n"
                    + "- al centro c'è la storyboard dove verrà raccontata\n"
                    + "    la storia man mano che completi obbiettivi o compi azioni.\n"
                    + "    Sulla storyboard è presente il nome della stanza attuale.\n"
                    + "- a destra c'è il tuo inventario, se vorrai avere\n"
                    + "    delle informazioni riguardo un item che hai appena preso puoi cliccare su quel item.\n"
                    + "- in basso a destra c'è il volume generale di tutto il gioco.\n"
                    + "- in basso al centro c'è la barra dove dovrai scrivere i vari comandi.", "Aiuto - Come interpretare la finestra pricipale", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(gameframe,
                        "- avviare il gioco: \"avvia\" da usare solo quando si clicca su \"Nuova partita\";\n"
                        + "- muoverti: \"vai\" direzione o nome della stanza;\n"
                        + "- sapere delle informazioni sulla stanza: \"guarda\";\n"
                        + "- guardare degli oggetti: \"guarda\" oggetto;\n"
                        + "- prendere un oggetto: \"prendi\" oggetto;\n"
                        + "- combinare tra loro gli oggetti: \"combina\" oggetto1 oggetto2;\n"
                        + "- uscire \"esci\";\n"
                        + "P.S.: puoi usare alias per gli oggetti, direzioni/stanze e comandi!\n\n"
                        + "Buon divertimento!!", "Aiuto - Come giocare", JOptionPane.QUESTION_MESSAGE);
            }
        }
    }

    /**
     * Comando che si occupa di prendere in giro il giocatore nel caso in cui
     * egli scelga di non voler leggere la descrizione.
     *
     * @param started indica se è la prima volta che il gioco che è stato
     * avviato
     */
    public void help(boolean started) {

        if (!started) {
            int option = JOptionPane.showConfirmDialog(gameframe,
                    "Vuoi leggere una breve introduzione al gioco?", "Benvenuto", JOptionPane.YES_NO_OPTION);

            switch (option) {
                case JOptionPane.YES_OPTION:
                    this.help();
                    break;
                case JOptionPane.NO_OPTION:
                    JOptionPane.showMessageDialog(gameframe,
                            "Come farai a giocare se non sai come?",
                            "????", 0);
                    option = JOptionPane.showConfirmDialog(gameframe,
                            "Te lo richiedo, vuoi leggere una breve introduzione al gioco?", "Benvenuto", JOptionPane.YES_NO_OPTION);

                    switch (option) {
                        case JOptionPane.YES_OPTION:
                            this.help();
                            break;
                        case JOptionPane.NO_OPTION:
                            JOptionPane.showMessageDialog(gameframe,
                                    "Va bene, come vuoi!",
                                    ">:(", 0);
                            break;
                    }
                    break;
            }
        } else {
            this.help();
        }
    }

    /**
     * Metodo che fornisce un comportamento al comando <b>USE</b>.
     *
     * <p>
     * Quando questo metodo viene chiamato effettua prima un controllo sulla
     * stanza attuale per verificare che l'item che l'utente vuole usare sia
     * presente nella stanza; se così fosse chiama il metodo <code>use</code>
     * dell'item che si vuole usare; altrimenti inizia una ricerca all'interno
     * dell'inventario dell'utente se questo non è vuoto, se l'item viene
     * trovato, viene chiamato il metodo <code>use</code> altrimenti viene
     * visualizzato un messaggio di errore.</p>
     *
     * @param actualRoom la stanza attuale
     * @param game il frame di gioco principale
     * @param rooms la lista di tutte le stanze
     * @param item l'item che si vuole usare
     */
    public void use(Room actualRoom, Game game, List<Room> rooms, String item) {
        Item item1 = null;
        List<Item> inv = gameframe.getInventory();
        boolean flag = false;

        if (!CMDblocked) {
            if (!actualRoom.getItems().isEmpty()) {
                for (int i = 0; i < actualRoom.getItems().size(); i++) {
                    if (actualRoom.getItems().get(i).getName().toLowerCase().equals("computer di bordo")) {
                        Computer computer;

                        if (actualRoom.getItems().get(i).getName().toLowerCase().equals(item.toLowerCase())) {
                            item1 = actualRoom.getItems().get(i);
                            computer = (Computer) item1;

                            computer.use(gameframe, audio);

                            flag = true;
                            break;

                        } else {
                            for (int j = 0; j < actualRoom.getItems().get(i).getAlias().size(); j++) {
                                if (actualRoom.getItems().get(i).getAlias().get(j).toLowerCase()
                                        .equals(item.toLowerCase())) {
                                    item1 = actualRoom.getItems().get(i);
                                    computer = (Computer) item1;

                                    computer.use(gameframe, audio);

                                    flag = true;
                                    break;

                                }
                            }
                        }
                    } else if (actualRoom.getItems().get(i).getName().toLowerCase().equals("quadro elettrico")) {
                        ElectricalPanel panel;

                        if (actualRoom.getItems().get(i).getName().toLowerCase().equals(item.toLowerCase())) {
                            item1 = actualRoom.getItems().get(i);

                            panel = (ElectricalPanel) item1;
                            panel.use(gameframe, audio);

                            flag = true;
                            break;

                        } else {
                            for (int j = 0; j < actualRoom.getItems().get(i).getAlias().size(); j++) {
                                if (actualRoom.getItems().get(i).getAlias().get(j).toLowerCase()
                                        .equals(item.toLowerCase())) {
                                    item1 = actualRoom.getItems().get(i);

                                    panel = (ElectricalPanel) item1;
                                    panel.use(gameframe, audio);

                                    flag = true;
                                    break;

                                }
                            }
                        }
                    } else if (actualRoom.getItems().get(i).getName().toLowerCase().equals("navicella")) {
                        SpaceShip spaceShip;

                        if (actualRoom.getItems().get(i).getName().toLowerCase().equals(item.toLowerCase())) {
                            item1 = actualRoom.getItems().get(i);

                            Room earth = null;
                            for (Room room : rooms) {
                                if (room.getName().toLowerCase().equals("pianeta terra")) {
                                    earth = room;
                                    break;
                                }
                            }

                            spaceShip = (SpaceShip) item1;
                            spaceShip.use(this, actualRoom, earth, game, gameframe, audio);

                            flag = true;
                            break;

                        } else {
                            for (int j = 0; j < actualRoom.getItems().get(i).getAlias().size(); j++) {
                                if (actualRoom.getItems().get(i).getAlias().get(j).toLowerCase()
                                        .equals(item.toLowerCase())) {
                                    item1 = actualRoom.getItems().get(i);

                                    Room earth = null;
                                    for (Room room : rooms) {
                                        if (room.getName().toLowerCase().equals("pianeta terra")) {
                                            earth = room;
                                            break;
                                        }
                                    }

                                    spaceShip = (SpaceShip) item1;
                                    spaceShip.use(this, actualRoom, earth, game, gameframe, audio);

                                    flag = true;
                                    break;

                                }
                            }
                        }
                    }
                }
            }

            if (!flag) {
                if (!inv.isEmpty()) {
                    for (int i = 0; i < inv.size(); i++) {
                        if (inv.get(i).getName().toLowerCase().equals(item.toLowerCase())) {
                            item1 = inv.get(i);
                            break;
                        } else {
                            if (!inv.get(i).getAlias().isEmpty()) {
                                for (int j = 0; j < inv.get(i).getAlias().size(); j++) {
                                    if (inv.get(i).getAlias().get(j).toLowerCase().equals(item.toLowerCase())) {
                                        item1 = inv.get(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (item1 != null && item1.isUsable()) {
                        switch (item1.getName().toLowerCase()) {
                            case "badge":
                                GoSciSector goSci;
                                Badge badge = (Badge) item1;
                                for (int i = 0; i < rooms.size(); i++) {
                                    if (rooms.get(i).getName().toLowerCase().equals("settore go sci")) {
                                        goSci = (GoSciSector) rooms.get(i);

                                        badge.use(actualRoom, goSci, gameframe, audio);
                                    }
                                }

                                break;
                            case "bombola di ossigeno":
                                OxygenTank tank = (OxygenTank) item1;
                                tank.use(gameframe, this.audio);
                                break;
                            case "bende":
                                Bandage bandage = (Bandage) item1;
                                bandage.use(gameframe, this.audio);
                                break;
                            case "carburante":
                                Fuel fuel = (Fuel) item1;
                                fuel.use(actualRoom, gameframe, audio);
                                break;
                            case "tuta protettiva":
                                ProtectiveWear wear = (ProtectiveWear) item1;
                                wear.use(gameframe, audio);
                                break;
                            case "contatore di geiger":
                                GeigerCounter geiger = (GeigerCounter) item1;
                                geiger.use(actualRoom, gameframe, audio);
                                break;

                        }
                    } else {
                        if (item1 == null) {
                            audio.playAlert();
                            JOptionPane.showMessageDialog(gameframe,
                                    "L'oggetto che vuoi usare esiste "
                                    + "solo nella tua testa!", "Errore", 2);
                        } else {
                            audio.playAlert();
                            JOptionPane.showMessageDialog(gameframe,
                                    "L'oggetto non è utilizzabile!", "Errore", 2);
                        }
                    }

                } else {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(gameframe, "Non hai oggetti "
                            + "nell'inventario", "Errore", 2);
                }
            }
        } else {    //se i comandi sono bloccati
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Devi finire di leggere la storia!", "Attenzione!", 2);
        }
    }

    /**
     * Metodo che fornisce il comportamento al comando <b>COMBINE</b>.
     *
     * <p>
     * Quando questo metodo viene chiamato effettua dei controlli
     * sull'inventario dell'utente per verificare che abbia gli Item che vuole
     * combinare. Se gli item vengono trovati vengono assegnati a delle
     * variabili temporanee e se sono entrambi diversi da <code>NULL</code>
     * allora vengono combinati.</p>
     *
     * @param item1 l'item da combinare
     * @param item2 l'item da combinare
     *
     * @see GeigerCounter#combine(com.client.types.Item,
     * com.client.form.gameframe.GameFrame)
     */
    public void combine(String item1, String item2) {
        boolean flag = false;
        Item itemFromInv1 = null;
        Item itemFromInv2 = null;
        GeigerCounter geiger;

        if (!CMDblocked) {
            if (!gameframe.getInventory().isEmpty()) {
                for (int i = 0; i < gameframe.getInventory().size(); i++) {
                    if (gameframe.getInventory().get(i).getName().toLowerCase().equals(item1.toLowerCase())
                            || gameframe.getInventory().get(i).getName().toLowerCase().equals(item2.toLowerCase())) {
                        if (!flag) {
                            itemFromInv1 = gameframe.getInventory().get(i);
                            flag = true;
                        } else {
                            itemFromInv2 = gameframe.getInventory().get(i);
                        }
                    } else if (!gameframe.getInventory().get(i).getAlias().isEmpty()) {
                        for (String alias : gameframe.getInventory().get(i).getAlias()) {
                            if (alias.toLowerCase().equals(item1.toLowerCase())
                                    || alias.toLowerCase().equals(item2.toLowerCase())) {
                                if (!flag) {
                                    itemFromInv1 = gameframe.getInventory().get(i);
                                    flag = true;
                                } else {
                                    itemFromInv2 = gameframe.getInventory().get(i);
                                }
                            }
                        }
                    }
                }
            } else {
                audio.playAlert();
                JOptionPane.showMessageDialog(gameframe,
                        "Il tuo inventario è vuoto!",
                        "Errore", 2);
            }

            if (itemFromInv1 != null && itemFromInv2 != null) {

                if (itemFromInv1.isCombinable() && itemFromInv2.isCombinable()) {
                    if (itemFromInv1.getName().toLowerCase().equals("contatore di geiger")
                            || itemFromInv2.getName().toLowerCase().equals("contatore di geiger")) {

                        if (itemFromInv1.getName().toLowerCase().equals("contatore di geiger")) {
                            geiger = (GeigerCounter) itemFromInv1;
                            geiger.combine(itemFromInv2, gameframe);
                        } else if (itemFromInv2.getName().toLowerCase().equals("contatore di geiger")) {
                            geiger = (GeigerCounter) itemFromInv2;
                            geiger.combine(itemFromInv1, gameframe);
                        } else {
                            audio.playAlert();
                            JOptionPane.showMessageDialog(gameframe, "Questi oggetti "
                                    + "non sono combinabili tra loro!", "Attenzione", 2);
                        }
                    }
                } else {
                    String str;
                    if (!itemFromInv1.isCombinable() && !itemFromInv2.isCombinable()) {
                        str = "Questi oggetti: " + itemFromInv1.getName() + ", " + itemFromInv2.getName()
                                + " non sono combinabili!";
                    } else if (!itemFromInv2.isCombinable()) {
                        str = "Questo oggetto: " + itemFromInv2.getName()
                                + " non è combinabile!";
                    } else {
                        str = "Questo oggetto: " + itemFromInv1.getName()
                                + " non è combinabile!";
                    }
                    audio.playAlert();
                    JOptionPane.showMessageDialog(gameframe, str, "Attenzione", 2);
                }
            } else {
                audio.playAlert();
                JOptionPane.showMessageDialog(gameframe, "Non puoi combinare l'oggetto con se stesso", "Attenzione", 2);
            }
        } else {    //se i comandi sono bloccati
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Devi finire di leggere la storia!", "Attenzione!", 2);
        }
    }

    /**
     * Metodo che fornisce il comportamento al comando <b>LEGGI</b>.
     *
     * <p>
     * Controlla se nella StoryBoard è presente la stringa "Scrivi 'leggi' per
     * continuare...", se il controllo va a buon fine, mostra al giocatore il
     * prossimo sotto-capitolo; altrimenti mostra messaggio di errore.</p>
     */
    public void readStory() {
        if (gameframe.getStory().getSubChapter() < gameframe.getStory().getLenStory()) {
            if (gameframe.getStory().getPieceStory().contains("Scrivi 'leggi' per continuare...")) {

                if (gameframe.getStory().getChapter() == 1 && gameframe.getStory().getSubChapter() == 1) {
                    this.audio.playSirens();
                    this.audio.playExplosion();
                    gameframe.setCurrentObjective(gameframe.getStory().getObjectives());

                    gameframe.getOxygenBar().setValue(11);
                    gameframe.getOxygenBar().setString(gameframe.getOxygenBar().getValue() + "% Ossigeno");

                    gameframe.getOxygenTimer().setSeconds(gameframe.getOxygenBar().getValue() * 60000);
                    gameframe.startOxygenTimer();
                }

                gameframe.getStory().setSubChapter(gameframe.getStory().getSubChapter() + 1);
                gameframe.setStoryBoard(gameframe.getStory().getPieceStory());

                if (gameframe.getStory().getSubChapter() == gameframe.getStory().getLenStory()) {
                    this.CMDblocked = false;
                }
            }
        } else {
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe, "Non c'è niente da leggere!", "Attenzione", 2);
        }
    }

    /**
     * Comando che chiude il gioco e la connessione con il Server e con il
     * Database.
     *
     * @param socket la connessione con il server.
     * @param db la connessione con il database.
     *
     * @throws java.io.IOException per possibili problemi con input/output.
     * @throws java.sql.SQLException per possibili problemi con la connessione
     * con il database.
     *
     * @see DB
     */
    public void quit(Socket socket, DB db) throws IOException, SQLException {
        socket.close();
        db.close();

        System.exit(0);
    }

    /**
     * Comado che si occupa di creare un salvataggio del gioco.
     *
     * <p>
     * Questo comando/metodo viene chiamato ogniqualvolta un obbiettivo viene
     * completato o quando l'utente chiama il comando "esci".</p>
     *
     * @param rooms la lista di tutte le stanze del gioco
     * @param objectives la lista di tutti gli obbiettivi del gioco
     * @param started da memorizzare per garantire il corretto funzionamento del
     * programma nel momento del caricamento
     * @param game il frame principale di gioco
     * @param story il pezzo di storia da cui riprendere
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     *
     * @see
     * com.client.gameutil.GameUtil#checkCompletationObjective(com.client.form.gameframe.GameFrame,
     * com.client.gameutil.DB, com.client.command.Command)
     */
    public void save(List<Room> rooms, List<Objective> objectives, boolean started, GameFrame game, Story story) throws FileNotFoundException, IOException {
        File dir = new File("resources/saves");

        if (!dir.exists()) {
            dir.mkdir();
        }

        FileOutputStream saveFile = new FileOutputStream("resources/saves/save.dat");
        ObjectOutputStream stream = new ObjectOutputStream(saveFile);

        stream = this.save(rooms, stream);
        stream = this.save(objectives, stream);
        stream = this.save(started, stream);
        stream = this.save(game.getInventory(), stream);
        stream = this.save(game.getGameTimer().getElapsedTime(), stream);
        stream = this.save(game.getOxygenBar().getValue(), stream);
        stream = this.save(game.getLifeBar().getValue(), stream);
        stream = this.save(game.getStoryBoard(), stream);
        this.save(story, stream);

        stream.close();
        saveFile.close();
    }

    /**
     * Metodo che si occupa di creare un salvataggio della partita.
     *
     * <p>
     * Questo metodo viene chiamato dal metodo save di questa classe.</p>
     *
     * @param object l'oggetto da salvare
     * @param stream lo stream di output rappresentante l'oggetto
     *
     * @return lo stream da salvare
     *
     * @throws IOException per eventuali problemi con l'I/O
     *
     * @see Command#save(java.util.List, java.util.List, boolean,
     * com.client.form.gameframe.GameFrame, com.client.types.Story)
     */
    @SuppressWarnings("unchecked")
    private ObjectOutputStream save(Object object, ObjectOutputStream stream) throws IOException {

        if (object.getClass().equals(LinkedList.class)) {
            List<Object> list = (LinkedList<Object>) object;

            stream.writeObject(list.size());

            for (int i = 0; i < list.size(); i++) {
                stream.writeObject(list.get(i));
            }
        } else {
            stream.writeObject(object);
        }

        return stream;
    }

    /**
     * Metodo che si occupa di leggere dal file di salvataggio le stanze del
     * gioco e le restituisce.
     *
     * @param stream il file di salvataggio dal quale leggere le informazioni
     *
     * @return le stanze del gioco
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     * @throws ClassNotFoundException nel caso in cui la classe da leggere non
     * esista.
     */
    public List<Room> loadRooms(ObjectInputStream stream) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Room> rooms = new LinkedList<>();

        Integer size = (Integer) stream.readObject();

        for (int i = 0; i < size; i++) {
            rooms.add((Room) stream.readObject());
        }

        return rooms;
    }

    /**
     * Metodo che si occupa di leggere dal file di salvataggio gli obbiettivi
     * del gioco e li restituisce.
     *
     * @param stream il file di salvataggio dal quale leggere le informazioni
     *
     * @return gli obbiettivi del gioco rimanenti
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     * @throws ClassNotFoundException nel caso in cui la classe da leggere non
     * esista.
     */
    public List<Objective> loadObjectives(ObjectInputStream stream) throws FileNotFoundException, IOException, ClassNotFoundException {
        List<Objective> objectives = new LinkedList<>();

        Integer size = (Integer) stream.readObject();

        for (int i = 0; i < size; i++) {
            objectives.add((Objective) stream.readObject());
        }

        return objectives;
    }

    /**
     * Metodo che si occupa di leggere dal file di salvataggio il valore dello
     * stato di esecuzione del programma e lo restituisce.
     *
     * @param stream il file di salvataggio dal quale leggere le informazioni
     *
     * @return lo stato di esecuzione appena letto
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     * @throws ClassNotFoundException nel caso in cui la classe da leggere non
     * esista.
     */
    public boolean loadExecution(ObjectInputStream stream) throws FileNotFoundException, IOException, ClassNotFoundException {
        return (boolean) stream.readObject();
    }

    /**
     * Metodo che si occupa di leggere dal file di salvataggio le informazioni
     * riguardanti il gameframe.
     *
     * @param stream il file di salvataggio dal quale leggere le informazioni
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     * @throws ClassNotFoundException nel caso in cui la classe da leggere non
     * esista.
     */
    public void loadGameFrame(ObjectInputStream stream) throws FileNotFoundException, IOException, ClassNotFoundException {
        Integer size = (Integer) stream.readObject();

        for (int j = 0; j < size; j++) {
            gameframe.setInventory((Item) stream.readObject());
        }

        gameframe.getGameTimer().setElapsedTime((Integer) stream.readObject());

        gameframe.getOxygenBar().setValue((Integer) stream.readObject());
        gameframe.getOxygenBar().setString(gameframe.getOxygenBar().getValue() + "% Ossigeno");

        gameframe.getLifeBar().setValue((Integer) stream.readObject());
        gameframe.getLifeBar().setString(gameframe.getLifeBar().getValue() + "% Vita");

        gameframe.setStoryBoard((String) stream.readObject());
    }

    /**
     * Metodo che si occupa di leggere dal file di salvataggio il pezzo di
     * storia e lo restituisce.
     *
     * @param stream il file di salvataggio dal quale leggere le informazioni
     *
     * @return il pezzo di storia appena letto
     *
     * @throws FileNotFoundException nel caso in cui il file non esista.
     * @throws IOException per possibili problemi di I/O con il file.
     * @throws ClassNotFoundException nel caso in cui la classe da leggere non
     * esista.
     */
    public Story loadStory(ObjectInputStream stream) throws FileNotFoundException, IOException, ClassNotFoundException {
        return (Story) stream.readObject();
    }
}
