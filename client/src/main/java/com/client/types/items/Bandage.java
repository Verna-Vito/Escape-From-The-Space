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
 * Classe che rappresenta le bende, uno degli Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class Bandage extends Item {

    /**
     * Costruttore della classe Bandage.
     *
     * @param name il nome dell'oggetto
     * @param db   il database dal quale leggere le varie informazioni del badge
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB
     */
    public Bandage(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo che definisce un comportamento da far compiere all'item quando
     * questo viene usato durante la partita.
     *
     * @param gameframe il frame principale di gioco
     * @param audio     per la corretta riproduzione di eventuali audio di errore
     */
    public void use(GameFrame gameframe, AudioPlayer audio) {
        if (!this.isUsed()) {
            Objective objective = null;

            for (int i = 0; i < gameframe.getStory().getObjectives().size(); i++) {
                if (gameframe.getStory().getObjectives().get(i).getId() == 4) {
                    objective = gameframe.getStory().getObjectives().get(i);
                    break;
                }
            }

            if (objective != null) {
                this.setUsed(true);

                gameframe.getLifeBar()
                        .setValue(gameframe.getLifeBar().getValue() + 10);

                gameframe.getLifeBar()
                        .setString(gameframe.getLifeBar().getValue() + "% Vita");

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
