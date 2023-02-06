/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.form.MiniGame;
import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.types.Item;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta il Quadro elettrico, uno degli Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class ElectricalPanel extends Item {

    /**
     * Costruttore della classe ElectricalPanel.
     *
     * @param name il nome dell'oggetto
     * @param db il database dal quale leggere le varie informazioni del quadro
     * elettrico
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB
     */
    public ElectricalPanel(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"usa oggetto"</code>.
     *
     * @param game variabile che permette la gestione del gameframe
     * (storyboard, inventario, ...).
     * @param audio per la corretta riproduzione di eventuali audio di errore
     */
    public void use(GameFrame game, AudioPlayer audio) {
        if (!this.isUsed()) {
            MiniGame minigame = new MiniGame(game, true, audio);
            if (minigame.isSolved()) {
                JOptionPane.showMessageDialog(game,
                        "Complimenti, sei riuscito a far ritornare la luce!",
                        "E luce fu!", 3);
                this.setUsed(true);
            }
        } else {
            audio.playAlert();
            JOptionPane.showMessageDialog(game,
                    "Hai già ripristinato la luce!", "Errore", 0);
        }
    }
}
