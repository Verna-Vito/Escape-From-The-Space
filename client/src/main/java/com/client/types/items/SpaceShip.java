/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.command.Command;
import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.gameutil.Game;
import com.client.gameutil.rest.WeatherApi;
import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.Room;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta la navicella di salvataggio, uno degli Item di questo
 * gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class SpaceShip extends Item {

    /**
     * Attributo stringa che indica il tempo ricavato dalle API REST.
     */
    private String weatherDesc;

    /**
     * Attributo che contiene la lista di obiettivi del gioco.
     */
    private final List<Objective> objectives;

    /**
     * Costruttore della classe SpaceShip.
     *
     * @param name il nome dell'oggetto.
     * @param db il database dal quale leggere le varie informazioni della
     * navicella di salvataggio.
     * @param objectives lista degli obiettivi.
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB.
     */
    public SpaceShip(String name, DB db, List<Objective> objectives)
            throws SQLException {
        super(name, db);
        this.objectives = objectives;
        WeatherApi weather = new WeatherApi();

        weatherDesc = weather.getWeatherDesc();
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"guarda oggetto"</code>.
     *
     * @param item oggetto guardato.
     * @param game variabile gameframe che permette la gestione delle stampe
     * nella storyboard.
     * @param audio variabile che permette la gestione degli audio di gioco.
     */
    public static void look(Item item, GameFrame game, AudioPlayer audio) {
        if (!item.isLooked()) {
            item.setLooked(true);
        } else {
            audio.playAlert();
            JOptionPane.showMessageDialog(game,
                    "Hai già controllato la navicella!", "Errore", 0);
        }
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"usa oggetto"</code>.
     *
     * @param cmd variabile che permette
     * @param actualRoom variabile che indica la stanza attuale.
     * @param earth variabile che indica la Room "earth".
     * @param game variabile che contiene tutti gli attributi che riguardano il
     * gioco.
     * @param gameframe variabile che permette la gestione del gameframe
     * (storyboard, inventario, ...).
     * @param audio variabile che permette la gestione dell'audio di gioco.
     */
    public void use(Command cmd, Room actualRoom, Room earth, Game game,
            GameFrame gameframe, AudioPlayer audio) {

        boolean completed1 = false;
        boolean completed2 = false;
        boolean completed3 = false;
        boolean completed4 = false;
        boolean completed5 = false;
        boolean completed6 = false;
        boolean completed7 = false;
        Item key1 = null;
        Item key2 = null;

        for (Objective objective : this.objectives) {
            if (objective.getId() == 6
                    && objective.isCompleted()
                    || completed1) {
                completed1 = true;

                if (objective.getId() == 7
                        && objective.isCompleted()
                        || completed2) {
                    completed2 = true;

                    if (objective.getId() == 8
                            && objective.isCompleted()
                            || completed3) {
                        completed3 = true;

                        if (objective.getId() == 9
                                && objective.isCompleted()
                                || completed4) {
                            completed4 = true;

                            if (objective.getId() == 11
                                    && objective.isCompleted()
                                    || completed5) {
                                completed5 = true;

                                if (objective.getId() == 12
                                        && objective.isCompleted()
                                        || completed6) {
                                    completed6 = true;

                                    if (objective.getId() == 13
                                            && objective.isCompleted()) {
                                        completed7 = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (completed1
                && completed2
                && completed3
                && completed4
                && completed5
                && completed6
                && completed7) {
            
            if (!gameframe.getInventory().isEmpty()) {
                for (Item item : gameframe.getInventory()) {
                    if (item.getName().toLowerCase().equals("chiave 1")) {
                        key1 = item;
                    }
                    if (item.getName().toLowerCase().equals("chiave 2")) {
                        key2 = item;
                    }
                }

                if (key1 != null && key2 != null) {
                    gameframe.getInventory().remove(key1);
                    gameframe.updateInventory();
                    gameframe.getInventory().remove(key2);
                    gameframe.updateInventory();
                }
            }

            JOptionPane.showMessageDialog(gameframe,
                    "Dall'oblò della navicella di salvataggio vedi "
                    + "l'asteroide che si schianta con l'Arca...",
                    ":'(",
                    JOptionPane.INFORMATION_MESSAGE);

            actualRoom.setNorth(earth);
            if (!this.isUsed()) {
                this.setUsed(true);
                game.setActualRoom(
                        cmd.goTo(actualRoom, "pianeta terra").getV());

                gameframe.setRoomName(game
                        .getActualRoom().getName().toUpperCase());

                JOptionPane.showMessageDialog(gameframe,
                        "Sei riuscito a raggiungere il "
                        + game.getActualRoom().getName() + "\n"
                        + this.weatherDesc, "Successo", 3);

                gameframe.getOxygenTimer().pause(); // ferma il decremento

                /*
                 * Imposta il valore dell'ossigeno a 100 e 
                 * stoppa decremento della vita.
                 */
                gameframe.getOxygenBar().setValue(100);
                gameframe.getOxygenBar()
                        .setString(gameframe.getOxygenBar()
                                .getValue() + "% Ossigeno");

                // imposta il valore della vita a 100
                gameframe.getLifeBar().setValue(100);
                gameframe.getLifeBar()
                        .setString(gameframe.getLifeBar()
                                .getValue() + "% Vita");
            }

        } else {
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe,
                    "Non sei ancora pronto per partire", "Attenzione", 2);
        }
    }
}
