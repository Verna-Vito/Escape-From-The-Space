/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe che permette la visualizzazione di un frame dal quale il giocatore
 * selezionerà il bottone "Nuova partita", "Carica" o "Esci".
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public class HomeFrame extends JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loadBtn;
    private javax.swing.JButton playBtn;
    private javax.swing.JButton quitBtn;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    /**
     * Attributo di tipo AudioPlayer che permette la riproduzione dei vari
     * audio.
     */
    private final AudioPlayer audio;

    /**
     * Attributo che permette la gestione del gameframe (storyboard, inventario,
     * ...).
     */
    private final GameFrame gf;

    /**
     * Attributo di tipo boolean che indica se il giocatore ha premuto il
     * bottone "Nuova partita".
     */
    private boolean playPressed = false;

    /**
     * Attributo di tipo boolean che indica se il giocatore ha premuto il
     * bottone "Carica".
     */
    private boolean loadPressed = false;

    /**
     * Costruttore della classe HomeFrame.
     *
     * @param gf variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...)
     * @param audio per consentire la corretta riproduzione dei vari audio
     */
    public HomeFrame(GameFrame gf, AudioPlayer audio) {
        initComponents();
        this.gf = gf;
        this.audio = audio;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        loadBtn = new javax.swing.JButton();
        playBtn = new javax.swing.JButton();
        quitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escape from the Space");
        setResizable(false);

        title.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Escape from the Space");

        loadBtn.setFont(new java.awt.Font("Calibri Light", 0, 30)); // NOI18N
        loadBtn.setText("Carica partita");
        loadBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadBtnMouseClicked(evt);
            }
        });
        loadBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loadBtnKeyPressed(evt);
            }
        });

        playBtn.setFont(new java.awt.Font("Calibri Light", 0, 30)); // NOI18N
        playBtn.setText("Nuova partita");
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playBtnMouseClicked(evt);
            }
        });
        playBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                playBtnKeyPressed(evt);
            }
        });

        quitBtn.setFont(new java.awt.Font("Calibri Light", 0, 30)); // NOI18N
        quitBtn.setText("Chiudi");
        quitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitBtnMouseClicked(evt);
            }
        });
        quitBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quitBtnKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(title)
                .addGap(70, 70, 70)
                .addComponent(playBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo che si occupa di avviare la partita, avviando il Thread della
     * musica e il Thread del contatore di gioco generale.
     */
    private void play() {
        this.audio.start();
        this.dispose();

        Thread gameTimerThread = new Thread(this.gf.getGameTimer());
        gameTimerThread.start();
    }

    /**
     * Metodo che controlla la presenza di file di salvataggio.
     *
     * @return <code>true</code> se è presente il file di salvataggio;
     * <code>false</code> altrimenti
     */
    private boolean checkkAvailableSave() {
        File dir = new File("resources/saves/save.dat");
        boolean flag = false;

        if (dir.exists()) {
            flag = true;
        }

        return flag;
    }

    /**
     * Metodo che al click da parte del giocatore sul bottone "Carica" si occupa
     * di compiere dei controlli.
     *
     * <p>
     * Quando l'utente preme il bottone "Nuova partita" viene effettuato un
     * controllo per vedere se ci sono dei salvataggi pregressi, se ci sono
     * verrà chiesto all'utente se ha intenzione di iniziare una nuova partita,
     * perdendo i progressi fatti; altrimenti verrà avviata una nuova
     * partita.</p>
     *
     * @param evt evento click del mouse
     */
    private void playBtnMouseClicked(MouseEvent evt) {
        if (this.checkkAvailableSave()) {
            this.audio.playAlert();
            int option = JOptionPane.showConfirmDialog(this,
                    "Esiste già un salvataggio, sei sicuro di voler sovrascrivere?\nPerderai i progressi fatti finora!",
                    "Sei sicuro?", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                new File("resources/saves/save.dat").delete();

                playPressed = true;
                this.play();
            }
        } else {
            playPressed = true;
            this.play();
        }
    }

    /**
     * Metodo che in base ai tasti della tastiera premuti effettua delle
     * operazioni.
     *
     * <p>
     * Se il giocatore preme il tasto della tastiera ENTER allora verrà
     * effettuato un controllo per verificare la presenza di salvataggi
     * pregressi, se ci sono verrà chiesto all'utente se ha intenzione di
     * iniziare una nuova partita, cancellando i progressi fatti; altrimenti
     * verrà avviata una nuova partita, se invece, viene premuto il tasto
     * FRECCIA GIU allora verrà selezionato il bottone "Carica".</p>
     *
     * @param evt evento pressione dei tasti sulla tastiera
     */
    private void playBtnKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (this.checkkAvailableSave()) {
                    this.audio.playAlert();
                    int option = JOptionPane.showConfirmDialog(this,
                            "Esiste già un salvataggio, sei sicuro di voler sovrascrivere?\nPerderai i progressi fatti finora!",
                            "Sei sicuro?", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        new File("resources/saves/save.dat").delete();

                        playPressed = true;
                        this.play();
                    }
                } else {
                    playPressed = true;
                    this.play();
                }
                break;
            case KeyEvent.VK_DOWN:
                this.loadBtn.requestFocus();
                break;
        }
    }

    /**
     * Metodo che in base ai tasti della tastiera premuti effettua delle
     * operazioni.
     *
     * <p>
     * Se il giocatore preme il tasto della tastiera ENTER allora verrà
     * effettuato un controllo per verificare la presenza di salvataggi
     * pregressi, se non ci sono verrà chiesto all'utente se ha intenzione di
     * iniziare una nuova partita; altrimenti verrà caricato il salvataggio
     * presente. Se invece viene premuto il tasto FRECCIA SU allora viene
     * selezionato il bottone "Nuova partita", se invece, viene premuto il tasto
     * FRECCIA GIU allora verrà selezionato il bottone "Chiudi".</p>
     *
     * @param evt evento pressione dei tasti sulla tastiera
     */
    private void loadBtnKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (!this.checkkAvailableSave()) {
                    this.audio.playAlert();
                    int option = JOptionPane.showConfirmDialog(this,
                            "Non esiste un salvataggio!\nVuoi iniziare una nuova partita?", "Errore",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {

                        playPressed = true;
                        this.play();
                    }
                } else {
                    loadPressed = true;
                    this.play();
                }
                break;
            case KeyEvent.VK_DOWN:
                this.quitBtn.requestFocus();
                break;
            case KeyEvent.VK_UP:
                this.playBtn.requestFocus();
                break;
        }
    }

    /**
     * Metodo che al click da parte del giocatore sul bottone "Carica" si occupa
     * di compiere dei controlli.
     *
     * <p>
     * Quando l'utente preme il bottone "Carica" viene effettuato un controllo
     * per vedere se ci sono dei salvataggi pregressi, se non ci sono verrà
     * chiesto all'utente se ha intenzione di iniziare una nuova partita;
     * altrimenti verrà caricato il salvataggio presente.</p>
     *
     * @param evt evento click del mouse
     */
    private void loadBtnMouseClicked(MouseEvent evt) {
        if (!this.checkkAvailableSave()) {
            this.audio.playAlert();
            int option = JOptionPane.showConfirmDialog(this,
                    "Non esiste un salvataggio!\nVuoi iniziare una nuova partita?", "Errore",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {

                playPressed = true;
                this.play();
            }
        } else {
            loadPressed = true;
            this.play();
        }
    }

    /**
     * Metodo che in base ai tasti della tastiera premuti effettua delle
     * operazioni.
     *
     * <p>
     * Se il giocatore preme il tasto della tastiera ENTER allora il programma
     * viene terminato; altrimenti se il giocatore preme il tasto FRECCIA SU
     * allora viene selezionato il bottone "Carica".</p>
     *
     * @param evt evento pressione dei tasti sulla tastiera
     */
    private void quitBtnKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                System.exit(0);
                break;
            case KeyEvent.VK_UP:
                this.loadBtn.requestFocus();
                break;
        }
    }

    /**
     * Metodo che al click sul mouse sul bottone "Chiudi" fa terminare il
     * programma.
     *
     * @param evt evento click del mouse
     */
    private void quitBtnMouseClicked(MouseEvent evt) {
        System.exit(0);
    }

    /**
     * Metodo che si occupa di restituire <code>true</code> se il giocatore ha
     * selezionato il bottone "Nuova partita"; <code>false</code> altrimenti.
     *
     * @return <code>true</code> se il giocatore ha selezionato il bottone
     * "Nuova partita"; <code>false</code> altrimenti
     */
    public boolean isPlayPressed() {
        return playPressed;
    }

    /**
     * Metodo che si occupa di restituire <code>true</code> se il giocatore ha
     * selezionato il bottone "Carica"; <code>false</code> altrimenti.
     *
     * @return <code>true</code> se il giocatore ha selezionato il bottone
     * "Carica"; <code>false</code> altrimenti
     */
    public boolean isLoadPressed() {
        return loadPressed;
    }
}
