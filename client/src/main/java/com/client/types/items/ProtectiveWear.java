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

import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Classe che rappresenta la tuta protettiva, uno degli Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class ProtectiveWear extends Item {

    /**
     * Costruttore della classe ProtectiveWear.
     *
     * @param name il nome dell'oggetto.
     * @param db il database dal quale leggere le varie informazioni della
     * tuta protettiva.
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB.
     */
    public ProtectiveWear(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"usa oggetto"</code>.
     *
     * @param gameframe variabile che permette la gestione del gameframe
     * (storyboard, inventario, ...).
     * @param audio variabile che permette la gestione dell'audio di gioco.
     *
     */
    public void use(GameFrame gameframe, AudioPlayer audio) {
        if (!this.isUsed()) {
            Objective objective = null;
            
            for(int i = 0; i < gameframe.getStory().getObjectives().size(); i++) {
                if(gameframe.getStory().getObjectives().get(i).getId() == 11) {
                    objective = gameframe.getStory().getObjectives().get(i);
                    break;
                }
            }

            if(objective != null) {
                this.setUsed(true);

                gameframe.getInventory().remove(this);
                gameframe.updateInventory();
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
