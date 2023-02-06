/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.Room;
import com.client.types.rooms.Monitoring;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta il carburante, uno degli Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class Fuel extends Item {

    /**
     * Costruttore della classe Fuel.
     *
     * @param name il nome dell'oggetto
     * @param db   il database dal quale leggere le varie informazioni del
     *             carburante
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB
     */
    public Fuel(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"usa oggetto"</code>.
     *
     * @param actualRoom la stanza attuale
     * @param gameframe  variabile che permette la gestione del gameframe
     *                   (storyboard, inventario, ...).
     * @param audio      per la corretta riproduzione di eventuali audio di errore
     */
    public void use(Room actualRoom, GameFrame gameframe, AudioPlayer audio) {
        if (!this.isUsed()) {

            Objective objective = null;

            for (int i = 0; i < gameframe.getStory().getObjectives().size(); i++) {
                if (gameframe.getStory().getObjectives().get(i).getId() == 6) {
                    objective = gameframe.getStory().getObjectives().get(i);
                    break;
                }
            }

            if (objective != null) {
                this.setUsed(true);

                if (actualRoom.getClass() == Monitoring.class) {
                    JOptionPane.showMessageDialog(gameframe,
                            "Hai riempito i serbatoi della navicella al 100%!",
                            "Serbatoi pieni", 3);

                    gameframe.getInventory().remove(this);
                    gameframe.updateInventory();
                } else {
                    audio.playAlert();
                    JOptionPane.showMessageDialog(
                            gameframe,
                            "Non puoi usare questo item qui!",
                            "Errore",
                            2);
                }
            } else {
                audio.playAlert();
                JOptionPane.showMessageDialog(
                        gameframe,
                        "Non è ancora il momento di utilizzare questo item!",
                        "Errore",
                        2);
            }
        }
    }
}
