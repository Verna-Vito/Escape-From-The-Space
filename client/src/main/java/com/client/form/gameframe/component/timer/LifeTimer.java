/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form.gameframe.component.timer;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import javax.swing.JProgressBar;

/**
 * Classe che rappresenta il timer della barra della vita e ne definisce il
 * comportamento.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see GameBar
 */
public class LifeTimer extends GameBar {

    /**
     * Costruttore della classe LifeTimer.
     *
     * @param gf variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     * @param lifeBar la barra grafica della vita.
     * @param audio l'audio del gioco.
     */
    public LifeTimer(GameFrame gf, JProgressBar lifeBar, AudioPlayer audio) {
        super(gf, lifeBar, audio);
    }

    /**
     * Override del metodo run di Runnable che si occupa del decremento del
     * timer della vita.
     */
    @Override
    public void run() {
        int newValue;

        while (!Thread.interrupted()) {
            try {
                Thread.sleep(5000);
                if (!this.stopThread) {
                    seconds = seconds - 600000;

                    hour = (seconds / 3600000);
                    minute = (seconds / 60000) % 60;
                    //second = (seconds / 1000) % 60;

                    newValue = minute + (hour * 60);

                    bar.setValue(newValue);
                    bar.setString(bar.getValue() + "% Vita");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
