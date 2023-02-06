/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form.gameframe.component.timer;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import java.io.Serializable;
import javax.swing.JProgressBar;

/**
 * Classe astratta che definisce il tipo GameBar.
 *
 * <p>
 * Implementa le interfacce Serializable per permettere il salvataggio dei vari
 * item e Runnable per il metodo run di un'ipotetico Thread.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Runnable
 * @see Serializable
 */
public abstract class GameBar implements Serializable, Runnable {

    /**
     * Attributo di tipo int protetto che rappresenta il valore in secondi della
     * barra.
     */
    protected int seconds;

    /**
     * Attributo di tipo int protetto che rappresenta il valore in minuti della
     * barra.
     */
    protected int minute;

    /**
     * Attributo di tipo int protetto che rappresenta il valore in ore della
     * barra.
     */
    protected int hour;

    /**
     * Attributo di tipo boolean protetto che indica se il Thread è stato messo
     * in pausa o meno.
     */
    protected boolean stopThread = false;

    /**
     * Attributo di tipo boolean privato che indica se il Thread è stato avviato
     * o meno.
     */
    private boolean started = false;

    /**
     * Attributo di tipo boolean protetto che indica se è stato stampato il
     * messaggio che indica se il livello di ossigeno è uguale a 0 o meno.
     */
    // livello di ossigeno uguale a 0
    protected boolean messageDisplayed = false; 

    /**
     * Attributo di tipo boolean protetto che indica se è stato stampato il
     * messaggio che indica se il livello di ossigeno è basso o meno.
     */
    // livello di ossigeno basso
    protected boolean messageDisplayed1 = false; 

    /**
     * Attributo di tipo boolean protetto che indica se è stato stampato il
     * messaggio che indica se il livello di ossigeno è critico o meno.
     */
    // livello di ossigeno critico
    protected boolean messageDisplayed2 = false; 

    /**
     * Attributo protetto che rappresenta la barra grafica.
     */
    protected final JProgressBar bar;

    /**
     * Attributo protetto che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     */
    protected final GameFrame gf;

    /**
     * Attributo protetto che rappresenta l'audio di gioco.
     */
    protected final AudioPlayer audio;

    /**
     * Costruttore della classe GameBar.
     *
     * @param gf variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     * @param bar variabile che rappresenta la barra grafica.
     * @param audio l'audio di gioco.
     */
    public GameBar(GameFrame gf, JProgressBar bar, AudioPlayer audio) {
        this.gf = gf;
        this.bar = bar;
        this.audio = audio;
    }

    /**
     * Metodo che si occupa di restituire il valore in secondi della barra.
     *
     * @return il valore dell'attributo seconds.
     */
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * Metodo che si occupa di impostare il valore all'attributo seconds.
     *
     * @param seconds valore da impostare all'attributo.
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Metodo che inverte il valore dell'attributo stopThread per mettere in
     * pausa/far ripartire il Thread.
     */
    public void pause() {
        this.stopThread = !this.stopThread;
    }

    /**
     * Metodo che si occupa di creare il Thread dell'oggetto e di startarlo. Se
     * il Thread risulta già avviato, lo mette in pausa per evitare problemi.
     */
    public void startThread() {
        if (!this.started) {
            started = true;
            Thread thread = new Thread(this);
            thread.start();
        } else {
            this.pause();
        }
    }

    /**
     * Metodo che si occupa di startare il Thread dato come parametro. Se il
     * Thread risulta già avviato, lo mette in pausa per evitare problemi.
     *
     * @param thread il thread da avviare.
     */
    public void startThread(Thread thread) {
        if (!this.started) {
            started = true;
            thread.start();
        } else {
            this.pause();
        }
    }

    @Override
    public void run() {
    }

}
