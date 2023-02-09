/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form.gameframe.component.timer;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 * Classe che rappresenta il timer di gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Serializable
 * @see Runnable
 */
public class GameTimer implements Serializable, Runnable {

    /**
     * Attributo di tipo int che rappresenta il tempo di gioco in millisecondi.
     */
    private int elapsedTime = 0;

    /**
     * Attributo che rappresenta i secondi utilizzato in fase di conversione da
     * millisecondi.
     */
    private int seconds = 0;

    /**
     * Attributo che rappresenta i minuti utilizzato in fase di conversione da
     * millisecondi.
     */
    private int minutes = 0;

    /**
     * Attributo che rappresenta le ore utilizzato in fase di conversione da
     * millisecondi.
     */
    private int hours = 0;

    /**
     * Attributo di tipo boolean protetto che indica se il Thread è stato messo
     * in pausa o meno.
     */
    private boolean paused = false;

    /**
     * Attributo che rappresenta la label all'interno del gameFrame.
     */
    private JLabel gameTimerString;

    /**
     * Costruttore della classe GameTimer.
     *
     * @param gameTimerString la label da modificare
     */
    public GameTimer(JLabel gameTimerString) {
        this.gameTimerString = gameTimerString;
    }

    /**
     * Metodo che restituisce il tempo trascorso in millisecondi, utile al
     * salvataggio della partita.
     *
     * @return il tempo trascorso in millisecondi
     */
    public int getElapsedTime() {
        return this.elapsedTime;
    }

    /**
     * Metodo che imposta il tempo trascorso in millisecondi, utile al
     * caricamento della partita.
     *
     * @param time il tempo trascorso in millisecondi
     */
    public void setElapsedTime(int time) {
        this.elapsedTime = time;
    }

    public void pause() {
        this.paused = !this.paused;
    }

    /**
     * Override del metodo run di Runnable che si occupa dell'incremento del
     * timer.
     */
    @Override
    public void run() {
        do {
            String secondsString;
            String minutesString;
            String hoursString;

            if (!paused) {
                try {
                    elapsedTime = elapsedTime + 1000;

                    hours = (elapsedTime / 3600000);
                    minutes = (elapsedTime / 60000) % 60;
                    seconds = (elapsedTime / 1000) % 60;

                    secondsString = String.format("%02d", seconds);
                    minutesString = String.format("%02d", minutes);
                    hoursString = String.format("%02d", hours);

                    gameTimerString.setText(
                            hoursString + ":"
                            + minutesString + ":"
                            + secondsString);

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }
}
