/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form.gameframe.component;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;

/**
 * Classe che definisce il comportamento del frame StoryBoard.
 *
 * <p>
 * Classe nata dall'idea di rendere cliccabile la StoryBoard per poter leggere i
 * vari sotto-capitoli di una storia.<br>
 * 
 * Deprecata poiché si è optato, successivamente, di inserire il comando "leggi"
 * per la stessa funzione.<br><br>
 * Per implementare questa classe, bisogna aggiungere un MouseEventListener alla
 * StoryBoard, che metterà l'attributo <code>pause</code> da true a false, per
 * poi notificare il thread (che va avviato nel costruttore di
 * GameFrame).</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Runnable
 * @deprecated
 */
@Deprecated
public class StoryBoard implements Runnable {

    /**
     * Attributo che permette la gestione del gameframe (storyboard, inventario,
     * ...).
     * @deprecated
     */
    @Deprecated
    private final GameFrame game;

    /**
     * Attributo privato che rappresenta l'audio di gioco.
     * @deprecated
     */
    @Deprecated
    private final AudioPlayer audio;

    /**
     * Attributo privato di tipo boolean che indica se il Thread è in pausa o
     * meno.
     * @deprecated
     */
    @Deprecated
    private boolean pause = false;

    /**
     * 
     * @deprecated
     */
    @Deprecated
    private String story;

    /**
     * 
     * @deprecated
     */
    @Deprecated
    private final Object lock = new Object();

    /**
     * Costruttore della classe StoryBoard.
     *
     * @param game variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     * @param audio l'audio di gioco.
     * @deprecated
     */
    @Deprecated
    public StoryBoard(GameFrame game, AudioPlayer audio) {
        this.game = game;
        this.audio = audio;
    }

    /**
     * Metodo privato che permette di cambiare lo stato dell'attributo pause da
     * <code>true</code> a <code>false</code>, o viceversa.
     * @deprecated
     */
    @Deprecated
    public void setPause() {
        synchronized (this.lock) {
            this.pause = true;
            lock.notifyAll();
        }
    }

    /**
     * 
     * @deprecated
     */
    @Deprecated
    public void setResume() {
        synchronized (this.lock) {
            this.pause = false;
            lock.notifyAll();
        }
    }

    /**
     * Metodo privato che restituisce il valore booleano dell'attributo pause.
     *
     * @return il valore di pause.
     * @deprecated
     */
    @Deprecated
    public boolean isPaused() {
        return this.pause;
    }

    /**
     * 
     * @return -
     * @deprecated
     */
    @Deprecated
    public String getStory() {
        return this.story;
    }

    /**
     * Override del metodo <code>run()</code> di Runnable che contiene le
     * istruzioni che rendono la StoryBoard cliccabile quando ci sono più pezzi
     * di storia.
     * @deprecated
     */
    @Override
    @Deprecated
    public void run() {
        while (true) {
            synchronized (lock) {
                while (isPaused()) {
                    System.out.println("Pausato");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (!isPaused()) {
                if (game.getStory() != null) {
                    if (game.getStory().getLenStory() - 1 > game.getStory().getSubChapter()) {
                        if (game.getStory().getPieceStory().matches(
                                "(.|\\s|\\n)*(Clicca(\\s|\\n)*qui(\\s|\\n)*per(\\s|\\n)*continuare...)(.|\\s|\\n)*")) {

                            if (game.getStory().getChapter() == 1 && game.getStory().getSubChapter() == 1) {
                                this.audio.playSirens();
                                this.audio.playExplosion();
                                game.setCurrentObjective(game.getStory().getObjectives());

                                game.getOxygenBar().setValue(11);
                                game.getOxygenBar().setString(game.getOxygenBar().getValue() + "% Ossigeno");

                                game.getOxygenTimer().setSeconds(game.getOxygenBar().getValue() * 60000);
                                game.startOxygenTimer();

                            }

                            if (game.getStory().getLenStory() != game.getStory().getSubChapter()) {
                                story = game.getStory().getPieceStory();

                                game.getStory().setSubChapter(game.getStory().getSubChapter() + 1);
                                game.setStoryBoard(game.getStory().getPieceStory());
                            }

                        }

                        if (game.getStory().getSubChapter() == game.getStory().getLenStory() - 1) {

                            game.getTextFieldInput().setEditable(true);
                            game.getTextFieldInput().requestFocus();

                        }
                    }
                }
                this.setPause();

            }
        }
    }
}
