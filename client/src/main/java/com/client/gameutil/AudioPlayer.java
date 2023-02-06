/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.gameutil;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Questa classe viene usata per la riproduzione dei suoni durante l'esecuzione
 * dell'applicazione.
 *
 * <p>
 * Fornisce i metodi per la riproduzione ed il controllo del volume.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Thread
 */
public final class AudioPlayer extends Thread {

    /**
     * Attributo che memorizza la cartella dove sono contenuti i file audio.
     */
    private final String soundsFolder = 
            "resources" + File.separator + "audios"+ File.separator;

    /**
     * Attributo che memorizza il valore corrente del volume.
     */
    private float currentVolume = -17;

    /**
     * Attributo che permette di modificare correttamente il volume dell'audio
     * di background durante l'esecuzione del gioco.
     */
    private FloatControl fcBackground;

    /**
     * Attributo che permette di modificare correttamente il volume dell'audio
     * di alert durante l'esecuzione del gioco.
     */
    private FloatControl fcAlert;

    /**
     * Attributo che permette di modificare correttamente il volume dell'audio
     * di gameover durante l'esecuzione del gioco.
     */
    private FloatControl fcGameOver;

    /**
     * Attributo che permette di modificare correttamente il volume dell'audio
     * di explosion durante l'esecuzione del gioco.
     */
    private FloatControl fcExplosion;

    /**
     * Attributo che permette di modificare correttamente il volume dell'audio
     * delle sirene durante l'esecuzione del gioco.
     */
    private FloatControl fcSirens;

    /**
     * Attributo che memorizza la posizione ed il nome del file audio di alert.
     */
    private final String alertPath = soundsFolder + "Alert.wav";

    /**
     * Attributo che memorizza la posizione ed il nome del file audio di
     * gameover.
     */
    private final String gameoverPath = soundsFolder + "GameOver.wav";

    /**
     * Attributo che memorizza la posizione ed il nome del file audio di
     * esplosione.
     */
    private final String explosionPath = soundsFolder + "Explosion.wav";

    /**
     * Attributo che memorizza la posizione ed il nome del file audio di
     * background.
     */
    private final String backgroundPath = soundsFolder + "Background.wav";

    /**
     * Attributo che memorizza la posizione ed il nome del file audio delle
     * sirene.
     */
    private final String sirensPath = soundsFolder + "Sirens.wav";

    /**
     * Attributo che contiene le informazioni per la corretta apertura e lettura
     * del file audio di alert.
     */
    private Clip alertSound;

    /**
     * Attributo che contiene le informazioni per la corretta apertura e lettura
     * del file audio di background.
     */
    private Clip backgroundSound;

    /**
     * Attributo che contiene le informazioni per la corretta apertura e lettura
     * del file audio di gameover.
     */
    private Clip gameoverSound;

    /**
     * Attributo che contiene le informazioni per la corretta apertura e lettura
     * del file audio di esplosione.
     */
    private Clip explosionSound;

    /**
     * Attributo che contiene le informazioni per la corretta apertura e lettura
     * del file audio delle sirene.
     */
    private Clip sirensSound;

    private static AudioPlayer singleInstance = null;

    /**
     * Costruttore che si occupa di inizializzare i vari attributi della classe
     * per permettere la corretta riproduzione dei suoni.
     *
     * @throws javax.sound.sampled.LineUnavailableException
     * @throws javax.sound.sampled.UnsupportedAudioFileException se il file non
     * è supportato
     * @throws java.io.IOException
     *
     * @see javax.sound.sampled.LineUnavailableException
     * @see javax.sound.sampled.UnsupportedAudioFileException
     * @see java.io.IOException
     */
    private AudioPlayer() 
            throws LineUnavailableException, UnsupportedAudioFileException, 
            IOException {
        
        this.alertSound = AudioSystem.getClip();
        this.gameoverSound = AudioSystem.getClip();
        this.explosionSound = AudioSystem.getClip();
        this.backgroundSound = AudioSystem.getClip();
        this.sirensSound = AudioSystem.getClip();

        this.alertSound.open(AudioSystem.getAudioInputStream(
                new File(this.alertPath).getAbsoluteFile()));
        this.gameoverSound.open(AudioSystem.getAudioInputStream(
                new File(this.gameoverPath).getAbsoluteFile()));
        this.explosionSound.open(AudioSystem.getAudioInputStream(
                new File(this.explosionPath).getAbsoluteFile()));
        this.backgroundSound.open(AudioSystem.getAudioInputStream(
                new File(this.backgroundPath).getAbsoluteFile()));
        this.sirensSound.open(AudioSystem.getAudioInputStream(
                new File(this.sirensPath).getAbsoluteFile()));

        this.fcBackground = (FloatControl) this.backgroundSound.getControl(
                FloatControl.Type.MASTER_GAIN);
        this.fcAlert = (FloatControl) this.alertSound.getControl(
                FloatControl.Type.MASTER_GAIN);
        this.fcGameOver = (FloatControl) this.gameoverSound.getControl(
                FloatControl.Type.MASTER_GAIN);
        this.fcExplosion = (FloatControl) this.explosionSound.getControl(
                FloatControl.Type.MASTER_GAIN);
        this.fcSirens = (FloatControl) this.sirensSound.getControl(
                FloatControl.Type.MASTER_GAIN);

        this.setVolume();
    }

    public static AudioPlayer initAudioPlayer() 
            throws LineUnavailableException, UnsupportedAudioFileException, 
            IOException {
        
        if (singleInstance == null) {
            singleInstance = new AudioPlayer();
        }
        return singleInstance;
    }

    /**
     * Questo metodo si occupa di riprodurre la musica di background del gioco.
     */
    public void playBackground() {
        this.backgroundSound.setFramePosition(0);
        this.backgroundSound.start();
        this.backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Questo metodo si occupa di riprodurre un suono di alert.
     */
    public void playAlert() {
        this.alertSound.setFramePosition(0);
        this.alertSound.start();
    }

    /**
     * Questo metodo si occupa di riprodurre un suono quando l'utente perde.
     */
    public void playGameover() {
        this.gameoverSound.setFramePosition(0);
        this.gameoverSound.start();
    }

    /**
     * Questo metodo si occupa di riprodurre un suono di esplosione.
     */
    public void playExplosion() {
        this.explosionSound.setFramePosition(0);
        this.explosionSound.start();
    }

    /**
     * Questo metodo si occupa di riprodurre un suono delle sirene.
     */
    public void playSirens() {
        this.sirensSound.setFramePosition(0);
        this.sirensSound.start();
    }

    /**
     * Questo metodo si occupa di prendere il valore corrente del volume.
     *
     * @return il valore corrente del volume.
     */
    public float getCurrentVolume() {
        return this.currentVolume;
    }

    /**
     * Questo metodo si occupa di impostare il valore corrente del volume
     *
     * @param currentVolume il valore da impostare.
     */
    public void setCurrentVolume(float currentVolume) {
        this.currentVolume = currentVolume;
    }

    /**
     * Questo metodo si occupa di impostare il volume dei vari effetti audio del
     * programma.
     */
    public void setVolume() {
        this.fcBackground.setValue(currentVolume);
        this.fcAlert.setValue(currentVolume);
        this.fcGameOver.setValue(currentVolume);
        this.fcExplosion.setValue(currentVolume);
        this.fcSirens.setValue(currentVolume);
    }

    @Override
    public void start() {
        this.playBackground();
    }
}
