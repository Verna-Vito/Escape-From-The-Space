/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form.gameframe;

import com.client.form.ItemDescriptionDialog;
import com.client.form.gameframe.component.timer.GameTimer;
import com.client.form.gameframe.component.timer.LifeTimer;
import com.client.form.gameframe.component.timer.OxygenTimer;
import com.client.gameutil.AudioPlayer;
import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.Story;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

/**
 * Classe che permette la visualizzazione del frame principale di gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public class GameFrame extends JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane availableCommand;
    private javax.swing.JLabel gameTimerString;
    private javax.swing.JPanel generalPanelContainer;
    private javax.swing.JLabel itemFrame1;
    private javax.swing.JLabel itemFrame10;
    private javax.swing.JLabel itemFrame11;
    private javax.swing.JLabel itemFrame12;
    private javax.swing.JLabel itemFrame2;
    private javax.swing.JLabel itemFrame3;
    private javax.swing.JLabel itemFrame4;
    private javax.swing.JLabel itemFrame5;
    private javax.swing.JLabel itemFrame6;
    private javax.swing.JLabel itemFrame7;
    private javax.swing.JLabel itemFrame8;
    private javax.swing.JLabel itemFrame9;
    private javax.swing.JPanel itemFramePanel;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPanel5;
    private javax.swing.JProgressBar life;
    private javax.swing.JPanel mapContainer;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JPanel obj_commandPanel;
    private javax.swing.JTextPane objectives;
    private javax.swing.JProgressBar oxygen;
    private javax.swing.JPanel progress_barPanelContainer;
    private javax.swing.JButton quitMapBtn;
    private javax.swing.JLabel roomName;
    private javax.swing.JTextPane storyBoard;
    private javax.swing.JPanel storyBoardPanel;
    private javax.swing.JTextField textFieldInput;
    private javax.swing.JLabel volumeLabel;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables

    /**
     * Attributo di tipo AudioPlayer che permette la riproduzione dei vari
     * audio.
     */
    private AudioPlayer audio;

    /**
     * Attributo di tipo Story che permette all'utente di leggere la storia
     * sulla StoryBoard.
     */
    private Story story;

    /**
     * Attributo che contiene la stringa che scrive l'utente da mandare al
     * server.
     */
    private String input = "";

    /**
     * Attributo boolean che indica se il giocatore ha premuto il tasto ENTER
     * sulla tastiera.
     */
    private boolean entered = false;

    /**
     * Attributo che contiene la lista dei vari item che il giocatore ha in quel
     * momento.
     */
    private final List<Item> inventory = new LinkedList<>();

    /**
     * Attributo che rappresenta il timer di gioco totale.
     */
    private GameTimer gameTimer;

    /**
     * Attributo che permette la manipolazione del timer della barra
     * dell'ossigeno.
     */
    private OxygenTimer oxygenTimer;

    /**
     * Attributo che permette la manipolazione del timer della barra della vita.
     */
    private LifeTimer lifeTimer;

    /**
     * Costruttore della classe GameFrame.
     *
     * @param audio per consentire la corretta riproduzione dei vari audio
     */
    public GameFrame(AudioPlayer audio) {
        initComponents();
        this.audio = audio;
        textFieldInput.requestFocus();
        initMyComponents();
    }

    /**
     * Metodo che si occupa di inizializzare i vari componenti del GameFrame da
     * noi creati.
     */
    private void initMyComponents() {
        this.gameTimer = new GameTimer(gameTimerString);

        this.lifeTimer = new LifeTimer(this, this.life, this.audio);
        this.oxygenTimer = new OxygenTimer(this, this.oxygen, this.audio, lifeTimer);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generalPanelContainer = new javax.swing.JPanel();
        progress_barPanelContainer = new javax.swing.JPanel();
        javax.swing.UIManager.put("ProgressBar.selectionForeground", java.awt.Color.black);
        javax.swing.UIManager.put("ProgressBar.selectionBackground", java.awt.Color.black);
        life = new javax.swing.JProgressBar();
        gameTimerString = new javax.swing.JLabel();
        javax.swing.UIManager.put("ProgressBar.selectionForeground", java.awt.Color.black);
        javax.swing.UIManager.put("ProgressBar.selectionBackground", java.awt.Color.black);
        oxygen = new javax.swing.JProgressBar();
        storyBoardPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        storyBoard = new javax.swing.JTextPane();
        roomName = new javax.swing.JLabel();
        textFieldInput = new javax.swing.JTextField();
        itemFramePanel = new javax.swing.JPanel();
        itemFrame1 = new javax.swing.JLabel();
        itemFrame2 = new javax.swing.JLabel();
        itemFrame3 = new javax.swing.JLabel();
        itemFrame4 = new javax.swing.JLabel();
        itemFrame5 = new javax.swing.JLabel();
        itemFrame6 = new javax.swing.JLabel();
        itemFrame7 = new javax.swing.JLabel();
        itemFrame8 = new javax.swing.JLabel();
        itemFrame9 = new javax.swing.JLabel();
        itemFrame10 = new javax.swing.JLabel();
        itemFrame11 = new javax.swing.JLabel();
        itemFrame12 = new javax.swing.JLabel();
        volumeLabel = new javax.swing.JLabel();
        volumeSlider = new javax.swing.JSlider(-40, 6);
        obj_commandPanel = new javax.swing.JPanel();
        jScrollPanel5 = new javax.swing.JScrollPane();
        availableCommand = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        objectives = new javax.swing.JTextPane();
        mapContainer = new javax.swing.JPanel();
        mapLabel = new javax.swing.JLabel();
        quitMapBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escape from the Space");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        generalPanelContainer.setBackground(new java.awt.Color(255, 255, 255));
        generalPanelContainer.setMaximumSize(new java.awt.Dimension(1020, 632));
        generalPanelContainer.setPreferredSize(new java.awt.Dimension(1020, 632));
        generalPanelContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        progress_barPanelContainer.setBackground(new java.awt.Color(255, 255, 255));
        progress_barPanelContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        life.setBackground(new java.awt.Color(255, 102, 102));
        life.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        life.setForeground(new java.awt.Color(255, 51, 51));
        life.setToolTipText("");
        life.setValue(100);
        life.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        life.setBorderPainted(false);
        life.setString("10% Vita");
        life.setStringPainted(true);
        life.setString(life.getValue() + "% Vita");
        life.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        gameTimerString.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        gameTimerString.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameTimerString.setText("00:00:00");
        gameTimerString.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gameTimerString.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        oxygen.setBackground(new java.awt.Color(0, 153, 255));
        oxygen.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        oxygen.setForeground(new java.awt.Color(0, 102, 255));
        oxygen.setToolTipText("");
        oxygen.setValue(100);
        oxygen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        oxygen.setBorderPainted(false);
        oxygen.setString("10% Ossigeno");
        oxygen.setStringPainted(true);
        oxygen.setString(oxygen.getValue() + "% Ossigeno");
        oxygen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout progress_barPanelContainerLayout = new javax.swing.GroupLayout(progress_barPanelContainer);
        progress_barPanelContainer.setLayout(progress_barPanelContainerLayout);
        progress_barPanelContainerLayout.setHorizontalGroup(
            progress_barPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progress_barPanelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(life, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(gameTimerString, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(oxygen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        progress_barPanelContainerLayout.setVerticalGroup(
            progress_barPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progress_barPanelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(progress_barPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(progress_barPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(oxygen, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(gameTimerString, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(life, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        storyBoardPanel.setBackground(new java.awt.Color(255, 255, 255));
        storyBoardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        storyBoard.setEditable(false);
        storyBoard.setBackground(new java.awt.Color(0, 0, 0));
        storyBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        storyBoard.setContentType("text/html"); // NOI18N
        storyBoard.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        storyBoard.setForeground(new java.awt.Color(255, 255, 255));
        storyBoard.setText("<html>\r\n  <head>\r\n       <style>\n           body {\n\tpadding: 10px;\n\ttext-align: justify;\n\tcolor: white;\n\tfont-size: 15px;\n             }\n       </style>\n\n  </head>\r\n  <body>\r\n          \n  </body>\r\n</html>\r\n"); // NOI18N
        jScrollPane3.setViewportView(storyBoard);

        roomName.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        roomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roomName.setText("Nome Stanza");
        roomName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        textFieldInput.setBackground(new java.awt.Color(204, 204, 204));
        textFieldInput.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        textFieldInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        textFieldInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textFieldInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldInputKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout storyBoardPanelLayout = new javax.swing.GroupLayout(storyBoardPanel);
        storyBoardPanel.setLayout(storyBoardPanelLayout);
        storyBoardPanelLayout.setHorizontalGroup(
            storyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roomName, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
            .addComponent(textFieldInput)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        storyBoardPanelLayout.setVerticalGroup(
            storyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, storyBoardPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roomName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        itemFramePanel.setBackground(new java.awt.Color(255, 255, 255));
        itemFramePanel.setOpaque(false);
        itemFramePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        itemFrame1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame1.setOpaque(true);
        itemFrame1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame1MouseClicked(evt);
            }
        });

        itemFrame2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame2.setOpaque(true);
        itemFrame2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame2MouseClicked(evt);
            }
        });

        itemFrame3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame3.setOpaque(true);
        itemFrame3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame3MouseClicked(evt);
            }
        });

        itemFrame4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame4.setOpaque(true);
        itemFrame4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame4MouseClicked(evt);
            }
        });

        itemFrame5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame5.setOpaque(true);
        itemFrame5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame5MouseClicked(evt);
            }
        });

        itemFrame6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame6.setOpaque(true);
        itemFrame6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame6MouseClicked(evt);
            }
        });

        itemFrame7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame7.setOpaque(true);
        itemFrame7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame7MouseClicked(evt);
            }
        });

        itemFrame8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame8.setOpaque(true);
        itemFrame8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame8MouseClicked(evt);
            }
        });

        itemFrame9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame9.setOpaque(true);
        itemFrame9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame9MouseClicked(evt);
            }
        });

        itemFrame10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame10.setOpaque(true);
        itemFrame10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame10MouseClicked(evt);
            }
        });

        itemFrame11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame11.setOpaque(true);
        itemFrame11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame11MouseClicked(evt);
            }
        });

        itemFrame12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        itemFrame12.setOpaque(true);
        itemFrame12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemFrame12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout itemFramePanelLayout = new javax.swing.GroupLayout(itemFramePanel);
        itemFramePanel.setLayout(itemFramePanelLayout);
        itemFramePanelLayout.setHorizontalGroup(
            itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemFramePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemFramePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(itemFrame5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(itemFrame6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(itemFramePanelLayout.createSequentialGroup()
                        .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(itemFramePanelLayout.createSequentialGroup()
                                .addComponent(itemFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(itemFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(itemFramePanelLayout.createSequentialGroup()
                                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(itemFrame3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itemFrame11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(itemFrame12, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itemFrame4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(itemFramePanelLayout.createSequentialGroup()
                                .addComponent(itemFrame9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemFrame10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(itemFramePanelLayout.createSequentialGroup()
                                .addComponent(itemFrame7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemFrame8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        itemFramePanelLayout.setVerticalGroup(
            itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemFramePanelLayout.createSequentialGroup()
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemFrame3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemFrame4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemFrame6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemFrame5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemFrame8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemFrame7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemFrame9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemFrame10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(itemFramePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(itemFrame11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemFrame12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        volumeLabel.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        volumeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volumeLabel.setText("Volume generale");
        volumeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        volumeSlider.setBackground(new java.awt.Color(255, 255, 255));
        volumeSlider.setPaintLabels(true);
        volumeSlider.setOpaque(true);
        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeSliderStateChanged(evt);
            }
        });
        volumeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        obj_commandPanel.setBackground(new java.awt.Color(255, 255, 255));
        obj_commandPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        jScrollPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        availableCommand.setEditable(false);
        availableCommand.setContentType("text/html"); // NOI18N
        availableCommand.setText("<html>\r\n  <head>\r\n       <style>\n           body {\n\tpadding: 1px;\n\tcolor: black;\n\tfont-size: 11.75px;\n             }\n       </style>\n  </head>\r\n  <body>\r\n          \n  </body>\r\n</html>\r\n"); // NOI18N
        availableCommand.setOpaque(false);
        jScrollPanel5.setViewportView(availableCommand);

        jScrollPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frameMouseClicked(evt);
            }
        });

        objectives.setEditable(false);
        objectives.setContentType("text/html"); // NOI18N
        objectives.setText("<html>\r\n  <head>\r\n       <style>\n           body {\n\tpadding: 1px;\n\tcolor: black;\n\tfont-size: 12px;\n             }\n       </style>\n  </head>\r\n  <body>\r\n          \n  </body>\r\n</html>\r\n");
        objectives.setOpaque(false);
        jScrollPane4.setViewportView(objectives);

        javax.swing.GroupLayout obj_commandPanelLayout = new javax.swing.GroupLayout(obj_commandPanel);
        obj_commandPanel.setLayout(obj_commandPanelLayout);
        obj_commandPanelLayout.setHorizontalGroup(
            obj_commandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
            .addGroup(obj_commandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(obj_commandPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(obj_commandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addComponent(jScrollPanel5))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        obj_commandPanelLayout.setVerticalGroup(
            obj_commandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(obj_commandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(obj_commandPanelLayout.createSequentialGroup()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout generalPanelContainerLayout = new javax.swing.GroupLayout(generalPanelContainer);
        generalPanelContainer.setLayout(generalPanelContainerLayout);
        generalPanelContainerLayout.setHorizontalGroup(
            generalPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelContainerLayout.createSequentialGroup()
                .addGroup(generalPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(progress_barPanelContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(generalPanelContainerLayout.createSequentialGroup()
                        .addComponent(obj_commandPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(storyBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(generalPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(volumeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(itemFramePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        generalPanelContainerLayout.setVerticalGroup(
            generalPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelContainerLayout.createSequentialGroup()
                .addComponent(progress_barPanelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelContainerLayout.createSequentialGroup()
                        .addGroup(generalPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storyBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(generalPanelContainerLayout.createSequentialGroup()
                                .addComponent(itemFramePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(volumeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addComponent(obj_commandPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        mapContainer.setMaximumSize(new java.awt.Dimension(1020, 617));
        mapContainer.setPreferredSize(new java.awt.Dimension(1020, 617));

        quitMapBtn.setText("Chiudi mappa");
        quitMapBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitMapBtnMouseClicked(evt);
            }
        });
        quitMapBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quitMapBtnKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout mapContainerLayout = new javax.swing.GroupLayout(mapContainer);
        mapContainer.setLayout(mapContainerLayout);
        mapContainerLayout.setHorizontalGroup(
            mapContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
            .addGroup(mapContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapContainerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mapLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)))
            .addGroup(mapContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapContainerLayout.createSequentialGroup()
                    .addContainerGap(447, Short.MAX_VALUE)
                    .addComponent(quitMapBtn)
                    .addContainerGap(448, Short.MAX_VALUE)))
        );
        mapContainerLayout.setVerticalGroup(
            mapContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
            .addGroup(mapContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mapContainerLayout.createSequentialGroup()
                    .addComponent(mapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 37, Short.MAX_VALUE)))
            .addGroup(mapContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapContainerLayout.createSequentialGroup()
                    .addContainerGap(572, Short.MAX_VALUE)
                    .addComponent(quitMapBtn)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(generalPanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mapContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(generalPanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(mapContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                    .addGap(8, 8, 8)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo che al click del mouse imposta il focus sulla text field di input.
     *
     * @param evt evento click del mouse
     */
    private void frameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameMouseClicked
        textFieldInput.requestFocus();
    }//GEN-LAST:event_frameMouseClicked

    /**
     * Metodo che si occupa di "chiudere" il frame di visualizzazione della
     * mappa al click del mouse sul pulsante "Chiudi mappa".
     *
     * @param evt evento click del mouse
     */
    private void quitMapBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quitMapBtnKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                generalPanelContainer.setVisible(true);
                this.setTitle("Escape form the Space");
                mapContainer.setVisible(false);
                textFieldInput.requestFocus();
                break;
        }
    }//GEN-LAST:event_quitMapBtnKeyPressed

    /**
     * Metodo che si occupa di "chiudere" il frame di visualizzazione della
     * mappa al click di un qualsiasi tasto della tastiera.
     *
     * @param evt evento click sulla tastiera
     */
    private void quitMapBtnMouseClicked(java.awt.event.MouseEvent evt) {
        generalPanelContainer.setVisible(true);
        this.setTitle("Escape form the Space");
        mapContainer.setVisible(false);
        textFieldInput.requestFocus();
    }

    /**
     * Metodo che si occupa di restituire il timer di gioco.
     *
     * @return il timer di gioco
     *
     * @see GameTimer
     */
    public GameTimer getGameTimer() {
        return this.gameTimer;
    }

    /**
     * Metodo che si occupa di restituire il timer dell'ossigeno.
     *
     * @return il timer dell'ossigeno
     *
     * @see OxygenTimer
     */
    public OxygenTimer getOxygenTimer() {
        return this.oxygenTimer;
    }

    /**
     * Metodo che si occupa di avviare il timer dell'ossigeno.
     *
     * @see OxygenTimer
     */
    public void startOxygenTimer() {
        this.oxygenTimer.startThread();
    }

    /**
     * Metodo che si occupa di impostare il contenuto della storyboard.
     *
     * @param str la stringa da far visualizzare al giocatore
     */
    public void setStoryBoard(String str) {
        storyBoard.setText(str);
    }

    /**
     * Metodo che si occupa di restituire la stringa che contiene la storyboard.
     *
     * @return la stringa che è contenuta nella storyboard
     */
    public String getStoryBoard() {
        return this.storyBoard.getText();
    }

    /**
     * Metodo che imposta e stampa la lista dei vari comandi disponibili in quel
     * momento e in quella stanza.
     *
     * @param availableCommands la mappa chiave-valore dei comandi disponibili
     *
     * @see
     * com.client.types.Room#updateAvailableCommands(com.client.form.gameframe.GameFrame,
     * com.client.command.Command)
     */
    public void setAvailableCommands(Map<String, Integer> availableCommands) {
        String str = "Comandi<br/> disponibili:<br/>";

        for (Map.Entry<String, Integer> commands : availableCommands.entrySet()) {
            if (commands.getValue() == 1) {
                str = str + commands.getKey() + "<br/>";
            }

            this.availableCommand.setText(str);
        }
    }

    /**
     * Metodo che si occupa di impostare la stringa che il giocatore ha scritto.
     *
     * <p>
     * Questo metodo si occupa di impostare la stringa che il giocatore scrive
     * quando viene rilevata la pressione del tasto ENTER sulla tastiera e viene
     * chiamato dal metodo textFieldInputKeyPressed(KeyEvent).</p>
     *
     * @param input la stringa del giocatore
     *
     * @see GameFrame#textFieldInputKeyPressed(java.awt.event.KeyEvent)
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Metodo che si occupa di restiuire la stringa data in input dal giocatore.
     *
     * <p>
     * Questo metodo si occupa di restituire la stringa data in input
     * dall'utente quando il metodo playGame(GameFrame, AudioPlayer, Command,
     * DB) della classe GameUtil rileva la pressione del tasto ENTER sulla
     * tastiera, in modo da poter far analizzare la stringa dal server e farsi
     * restituire l'id del comando corrispondente e permette al giocatore di
     * giocare correttamente.</p>
     *
     * @return la stringa data in input
     *
     * @see
     * com.client.gameutil.GameUtil#playGame(com.client.form.gameframe.GameFrame,
     * com.client.gameutil.AudioPlayer, com.client.command.Command,
     * com.client.gameutil.DB)
     */
    public String getInput() {
        return input;
    }

    /**
     * Metodo che quando viene cambiata stanza si occupa di impostare il nome
     * sulla storyboard.
     *
     * @param str il nome da visualizzare
     */
    public void setRoomName(String str) {
        roomName.setText(str.toUpperCase());
    }

    /**
     * Metodo che imposta e stampa la lista dell'/degli obbiettivo/i disponibili
     * in quel momento e in quella stanza.
     *
     * @param objectivesList la lista degli obbiettivi da completare
     *
     */
    public void setCurrentObjective(List<Objective> objectivesList) {
        String str = "Obbiettivi:<br/>";

        for (int i = 0; i < objectivesList.size(); i++) {
            str = str + objectivesList.get(i).toString() + "<br/>";
        }

        objectives.setText(str);
    }

    /**
     * Metodo che si occupa di restituire il valore dell'attributo di questa
     * classe che indica se il tasto ENTER è stato premuto o meno.
     *
     * @return <code>true</code> se ENTER è stato premuto; <code>false</code>
     * altrimenti
     */
    public boolean isEnterPressed() {
        return entered;
    }

    /**
     * Metodo che si occupa di impostare il valore dell'attributo di questa
     * classe che indica se il tasto ENTER è stato premuto o meno.
     *
     * @param entered <code>true</code> se ENTER è stato premuto;
     * <code>false</code> altrimenti
     */
    public void setEnterPressed(boolean entered) {
        this.entered = entered;
    }

    /**
     * Metodo che si occupa di aggiungere l'item appena preso nell'inventario
     * del giocatore.
     *
     * <p>
     * Questo metodo viene chiamato ogni volta che un item viene preso dalla
     * stanza in cui è il giocatore.<br>
     * Dopo aver aggiunto l'item all'inventario chiama il metodo
     * "<code>updateInventory</code>".</p>
     *
     * @param item l'item appena preso.
     *
     * @see com.client.command.Command#pickUp(com.client.types.Room,
     * java.lang.String)
     * @see GameFrame#updateInventory()
     */
    public void setInventory(Item item) {
        inventory.add(item);
        updateInventory();
    }

    /**
     * Metodo che si occupa di restituire l'intero inventario dell'utente.
     *
     * @return la lista degli item che il giocatore ha.
     */
    public List<Item> getInventory() {
        return this.inventory;
    }

    /**
     * Metodo che si occupa di restituire la JProgressBar che rappresenta la
     * barra dell'ossigeno.
     *
     * @return la JProgressBar che rappresenta la barra dell'ossigeno
     */
    public JProgressBar getOxygenBar() {
        return this.oxygen;
    }

    /**
     * Metodo che si occupa di restituire la JProgressBar che rappresenta la
     * barra della vita.
     *
     * @return la JProgressBar che rappresenta la barra della vita
     */
    public JProgressBar getLifeBar() {
        return this.life;
    }

    /**
     * Metodo che si occupa di restituire l'attributo di tipo JTextField.
     *
     * <p>
     * Metodo deprecato in quanto usato solo per una classe che è stata
     * deprecata anch'essa.</p>
     *
     * @return l'attributo di tipo JTextField
     * @deprecated
     *
     * @see com.client.form.gameframe.component.StoryBoard
     */
    @Deprecated
    public JTextField getTextFieldInput() {
        return this.textFieldInput;
    }

    /**
     * Metodo che si occupa di far visualizzare la mappa al giocatore.
     */
    public void showMap() {
        mapContainer.setVisible(true);
        generalPanelContainer.setVisible(false);
        this.setTitle("Escape form the Space - Mappa");
    }

    /**
     * Metodo che si occupa di restituire la label dove verrà inserita
     * l'immagine della mappa da far visualizzare al giocatore.
     *
     * @return la JLabel dove inserire la mappa
     */
    public JLabel getMapLabel() {
        return mapLabel;
    }

    /**
     * Metodo che si occupa di restituire l'attributo di tipo Story di questa
     * classe.
     *
     * @return la storia
     *
     * @see Story
     */
    public Story getStory() {
        return this.story;
    }

    /**
     * Metodo che si occupa di impostare l'attributo di tipo Story di questa
     * clase.
     *
     * @param story il nuovo valore di story
     *
     * @see Story
     */
    public void setStory(Story story) {
        this.story = story;
    }

    /**
     * Metodo che si occupa di verificare quale tasto della tastiera è stato
     * premuto.
     *
     * <p>
     * Questo metodo si occupa di effettuare un controllo sul codice del tasto
     * della tastiera e se corrisponde al codice del tasto ENTER, allora imposta
     * il contenuto della JTextField come input inserito dall'utente chiamando
     * il metodo setInput(String) e impostando setEnterPressed(boolean) a
     * <code>true</code>, permettendo così al metodo playGame() di GameUtil di
     * mandare la stringa al server per farla analizzare e tokenizzare; infine
     * imposta il contenuto della JTextField ad una stringa vuota.</p>
     *
     * @param evt evento click sulla tastiera
     *
     * @see
     * com.client.gameutil.GameUtil#playGame(com.client.form.gameframe.GameFrame,
     * com.client.gameutil.AudioPlayer, com.client.command.Command,
     * com.client.gameutil.DB)
     */
    private void textFieldInputKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                String input1 = textFieldInput.getText().toLowerCase();

                this.setInput(input1);
                this.setEnterPressed(true);
                textFieldInput.setText("");
                break;
        }
    }

    /**
     * Metodo che si occupa di cambiare il volume generale del gioco.
     *
     * @param evt evento di cambiamento
     *
     * @see AudioPlayer
     */
    private void volumeSliderStateChanged(ChangeEvent evt) {
        audio.setCurrentVolume(volumeSlider.getValue());

        if (audio.getCurrentVolume() == -40) {
            audio.setCurrentVolume(-80);
        }

        audio.setVolume();
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame1MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame1.getName() != null) {
                if (itemFrame1.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame2MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame2.getName() != null) {
                if (itemFrame2.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame3MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame3.getName() != null) {
                if (itemFrame3.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame4MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame4.getName() != null) {
                if (itemFrame4.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame5MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame5.getName() != null) {
                if (itemFrame5.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame6MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame6.getName() != null) {
                if (itemFrame6.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame7MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame7.getName() != null) {
                if (itemFrame7.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame8MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame8.getName() != null) {
                if (itemFrame8.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame9MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame9.getName() != null) {
                if (itemFrame9.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame10MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame10.getName() != null) {
                if (itemFrame10.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame11MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame11.getName() != null) {
                if (itemFrame11.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare una finestra di dialog con l'utente per
     * mostrare informazioni utili sull'item appena cliccato.
     *
     * @param evt evento click del mouse
     */
    private void itemFrame12MouseClicked(MouseEvent evt) {
        for (Item item1 : inventory) {
            if (itemFrame12.getName() != null) {
                if (itemFrame12.getName().equals(item1.getName())) {
                    ItemDescriptionDialog dialog = new ItemDescriptionDialog(this, true, item1, this);
                    dialog.setVisible(true);
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di aggiornare le immagini dell'itemframe.
     */
    public void updateInventory() {
        this.showItemIcon();
    }

    /**
     * Metodo che si occupa di mostrare le icone degli item presenti
     * nell'inventario.
     *
     * <p>
     * Ogni volta che un item viene preso o utilizzato è necessario aggiornare
     * l'itemframe, quindi questo metodo viene chiamato dal metodo
     * "<code>updateInventory</code>".</p>
     *
     * @see GameFrame#updateInventory()
     */
    private void showItemIcon() {
        Image image;
        ImageIcon icon;

        if (inventory.isEmpty()) {
            itemFrame1.setIcon(new ImageIcon(""));
            itemFrame1.setName(" ");
        }

        for (int i = 0; i < inventory.size(); i++) {
            switch (i) {
                case 0:
                    itemFrame2.setIcon(new ImageIcon(""));
                    itemFrame2.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame1.getWidth(), itemFrame1.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame1.setIcon(new ImageIcon(image));
                    itemFrame1.setName(inventory.get(i).getName());

                    break;
                case 1:
                    itemFrame3.setIcon(new ImageIcon(""));
                    itemFrame3.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame2.getWidth(), itemFrame2.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame2.setIcon(new ImageIcon(image));
                    itemFrame2.setName(inventory.get(i).getName());

                    break;
                case 2:
                    itemFrame4.setIcon(new ImageIcon(""));
                    itemFrame4.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame3.getWidth(), itemFrame3.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame3.setIcon(new ImageIcon(image));
                    itemFrame3.setName(inventory.get(i).getName());
                    break;
                case 3:
                    itemFrame5.setIcon(new ImageIcon(""));
                    itemFrame5.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame4.getWidth(), itemFrame4.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame4.setIcon(new ImageIcon(image));
                    itemFrame4.setName(inventory.get(i).getName());
                    break;
                case 4:
                    itemFrame6.setIcon(new ImageIcon(""));
                    itemFrame6.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame5.getWidth(), itemFrame5.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame5.setIcon(new ImageIcon(image));
                    itemFrame5.setName(inventory.get(i).getName());
                    break;
                case 5:
                    itemFrame7.setIcon(new ImageIcon(""));
                    itemFrame7.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame6.getWidth(), itemFrame6.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame6.setIcon(new ImageIcon(image));
                    itemFrame6.setName(inventory.get(i).getName());
                    break;
                case 6:
                    itemFrame8.setIcon(new ImageIcon(""));
                    itemFrame8.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame7.getWidth(), itemFrame7.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame7.setIcon(new ImageIcon(image));
                    itemFrame7.setName(inventory.get(i).getName());
                    break;
                case 7:
                    itemFrame9.setIcon(new ImageIcon(""));
                    itemFrame9.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame8.getWidth(), itemFrame8.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame8.setIcon(new ImageIcon(image));
                    itemFrame8.setName(inventory.get(i).getName());
                    break;
                case 8:
                    itemFrame10.setIcon(new ImageIcon(""));
                    itemFrame10.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame9.getWidth(), itemFrame9.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame9.setIcon(new ImageIcon(image));
                    itemFrame9.setName(inventory.get(i).getName());
                    break;
                case 9:
                    itemFrame11.setIcon(new ImageIcon(""));
                    itemFrame11.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame10.getWidth(), itemFrame10.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame10.setIcon(new ImageIcon(image));
                    itemFrame10.setName(inventory.get(i).getName());
                    break;
                case 10:
                    itemFrame12.setIcon(new ImageIcon(""));
                    itemFrame12.setName(" ");

                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame11.getWidth(), itemFrame11.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame11.setIcon(new ImageIcon(image));
                    itemFrame11.setName(inventory.get(i).getName());
                    break;
                case 11:
                    icon = new ImageIcon(inventory.get(i).getIMAGE_PATH());
                    image = icon.getImage().getScaledInstance(itemFrame12.getWidth(), itemFrame12.getHeight(),
                            Image.SCALE_SMOOTH);
                    itemFrame12.setIcon(new ImageIcon(image));
                    itemFrame12.setName(inventory.get(i).getName());
                    break;
            }
        }
    }
}
