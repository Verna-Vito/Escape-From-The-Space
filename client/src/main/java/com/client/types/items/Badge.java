/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Room;
import com.client.types.rooms.CommonZone;
import com.client.types.rooms.GoSciSector;
import com.client.types.rooms.Monitoring;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta il Badge, uno degli Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class Badge extends Item {

    /**
     * Costruttore della classe Badge.
     *
     * @param name il nome dell'oggetto
     * @param db il database dal quale leggere le varie informazioni del badge
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB
     */
    public Badge(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo che definisce un comportamento da far compiere all'item quando
     * questo viene usato durante la partita.
     *
     * @param actualRoom la stanza in cui si è in questo momento
     * @param goSci la stanza che rappresenta il Settore Go Sci
     * @param gameframe il frame principale di gioco
     * @param audio l'audio per permettere la corretta riproduzione dei vari
     * audio
     */
    public void use(Room actualRoom, GoSciSector goSci, GameFrame gameframe,
            AudioPlayer audio) {

        if (!this.isUsed()) {
            if ((actualRoom.getClass() == CommonZone.class)
                    || (actualRoom.getClass() == Monitoring.class)
                    && goSci.isLocked()) {
                JOptionPane.showMessageDialog(
                        gameframe,
                        "Hai aperto le porte del Settore Go Sci!",
                        "Autenticazione",
                        3);
                goSci.setLocked(false);

                this.setUsed(true);

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
        }
    }
}
