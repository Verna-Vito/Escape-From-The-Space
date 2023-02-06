/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form.gameframe.component.timer;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 * Classe che rappresenta il timer della barra dell'ossigeno e ne definisce il
 * comportamento.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see GameBar
 */
public class OxygenTimer extends GameBar {

    /**
     * Attributo di tipo LifeTimer che permette la gestione della barra della
     * vita in base ai livelli di ossigeno.
     */
    private LifeTimer lifeTimer;

    /**
     * Indica se la barra ha valore 0 o meno.
     */
    private boolean flag = false;

    /**
     * Costruttore della classe OxygenTimer.
     *
     * @param gf variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     * @param oxygenBar la barra grafica dell'ossigeno.
     * @param audio l'audio del gioco.
     * @param lifeTimer timer della barra della vita.
     */
    public OxygenTimer(GameFrame gf, JProgressBar oxygenBar, AudioPlayer audio, 
            LifeTimer lifeTimer) {
        
        super(gf, oxygenBar, audio);
        this.lifeTimer = lifeTimer;
    }

    /**
     * Override del metodo run di Runnable che si occupa del decremento del
     * timer dell'ossigeno e della chiamata al metodo <code>checkBar()</code>.
     */
    @Override
    public void run() {
        Thread lifeThread = new Thread(lifeTimer);
        int newValue;

        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                this.checkBar(lifeThread);

                if (!this.stopThread) {
                    seconds = seconds - 1000;

                    hour = (seconds / 3600000);
                    minute = (seconds / 60000) % 60;
                    //second = (seconds / 1000) % 60;

                    newValue = minute + (hour * 60);

                    bar.setValue(newValue);
                    bar.setString(bar.getValue() + "% Ossigeno");
                    
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo che si occupa di controllare i valori dei timer di gioco
     * OxygenTimer e LifeTimer per gestirne il comportamento in tutte le
     * casistiche possibili.
     *
     * @param lifeThread per avviare o mettere in pausa il timer di gioco
     * LifeTimer.
     */
    private void checkBar(Thread lifeThread) {

        if (gf.getLifeBar().getValue() == 0) {
            audio.playGameover();
            JOptionPane.showMessageDialog(gf, "Sei morto", "Game Over", 0);
            lifeTimer.pause();
            System.exit(0);
        }

        if (gf.getOxygenBar().getValue() > 0) {

            messageDisplayed2 = false;

            if (flag) {

                flag = false;
                lifeTimer.pause();

                this.setSeconds(gf.getOxygenBar().getValue() * 60000);
                this.pause();
            }

            if (gf.getOxygenBar().getValue() >= 10) {
                messageDisplayed1 = false; // basso
            } else if (gf.getOxygenBar().getValue() > 5) {
                messageDisplayed = false; // critico
            }

        }

        if (gf.getOxygenBar().getValue() == 0) {
            flag = true;

            if (messageDisplayed2 == false) {
                messageDisplayed2 = true;
                audio.playAlert();

                this.pause();

                lifeTimer.setSeconds((gf.getLifeBar().getValue()) * 60000);
                lifeTimer.startThread(lifeThread);

                JOptionPane.showMessageDialog(gf, 
                        "L'ossigeno è finito, stai morendo", "Stai morendo", 0);
            }
        } else if (gf.getOxygenBar().getValue() <= 5) {

            if (messageDisplayed == false) {
                messageDisplayed = true;

                audio.playAlert();
                JOptionPane.showMessageDialog(gf, 
                        "Livello di ossigeno critico", "Ossigeno critico", 2);
            }

        } else if (gf.getOxygenBar().getValue() < 10) {

            if (messageDisplayed1 == false) {
                messageDisplayed1 = true;

                audio.playAlert();
                JOptionPane.showMessageDialog(gf, 
                        "Livello di ossigeno basso", "Ossigeno basso", 2);
            }
        }
    }
}
