/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.types.Item;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta il computer di bordo, uno degli Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class Computer extends Item {

    /**
     * Attributo di tipo String che contiene un piccolo hint per l'utente.
     */
    private static final String STORY = "Ti conviene scappare!!!";

    /**
     * Costruttore della classe Computer.
     *
     * @param name il nome dell'oggetto
     * @param db il database dal quale leggere le varie informazioni del
     * computer di bordo
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB
     */
    public Computer(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo che definisce un comportamento da far compiere all'item quando
     * questo viene usato durante la partita.
     *
     * @param gameframe il frame principale di gioco
     * @param audio l'audio per permettere la corretta riproduzione dei vari
     * audio
     */
    public void use(GameFrame gameframe, AudioPlayer audio) {
        String input;
        if (gameframe.getStory().getChapter() > 1) {
            if (!this.isUsed()) {
                do {
                    input = JOptionPane.showInputDialog(
                            "Per favore inserisci la password: ");

                    if (input != null) {
                        if (!input.equals("*****")) {
                            audio.playAlert();
                            JOptionPane.showMessageDialog(gameframe,
                                    "Accesso negato! Password errata!\nLa password "
                                    + "dovrebbe essere uguale a *****",
                                    "Accesso negato", 0);
                        } else {
                            JOptionPane.showMessageDialog(gameframe,
                                    "Accesso eseguito correttamente!",
                                    "Accesso autorizzato", 3);
                            this.setUsed(true);
                        }
                    } else {
                        break;
                    }
                } while (!input.equals("*****"));

            } else {
                gameframe.setStoryBoard(STORY);
            }
        } else {
            audio.playAlert();
            JOptionPane.showMessageDialog(gameframe,
                    "Cercavi di speedrunnare? Ti abbiamo fregato!",
                    "=D", 0);
        }
    }
}
