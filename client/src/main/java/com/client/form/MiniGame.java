/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form;

import com.client.gameutil.AudioPlayer;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;

/**
 * Classe che definisce il minigioco Find_Hash, ossia trovare la combinazione
 * lettera-numero che viene ripetuta più volte in una matrice.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public class MiniGame extends javax.swing.JDialog {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enterBtn;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * Attributo di tipo AudioPlayer che permette la gestione dell'audio di
     * gioco.
     *
     * @see AudioPlayer
     */
    private final AudioPlayer audio;

    /**
     * Attributo di tipo String che indica la combinazione vincente.
     */
    private String winning;

    /**
     * Attributo di tipo boolean che è avvalorato: true - se il minigioco è
     * stato risolto; false - altrimenti.
     */
    private boolean solved = false;

    /**
     * Costante che indica l'altezza della matrice.
     */
    private final static int HEIGHT_MATRIX = 6;

    /**
     * Costante che indica la largezza della matrice.
     */
    private final static int WIDTH_MATRIX = 6;

    /**
     * "Tavola di gioco" - matrice di stringhe.
     */
    private String[][] matrix = new String[HEIGHT_MATRIX][WIDTH_MATRIX];

    /**
     * Costante array di stringhe per l'indicazione delle lettere disponibili in
     * esadecimale.
     */
    private final static String LETTERS[] = {"A", "B", "C", "D", "E", "F"};

    /**
     * Costrutture della classe Minigame
     *
     * @param parent Frame di aggancio
     * @param modal impedisce l'utilizzo del frame sottostante
     * @param audio permette la gestione dell'audio
     */
    public MiniGame(java.awt.Frame parent, boolean modal, AudioPlayer audio) {
        super(parent, modal);
        initComponents();
        jTextField1.requestFocus();

        this.audio = audio;
        this.initMiniGame();
        this.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        enterBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trova il codice hex che si ripete di più");
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        enterBtn.setText("Invia");
        enterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterBtnMouseClicked(evt);
            }
        });
        enterBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enterBtnKeyPressed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(255, 255, 153));
        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(0, 0, 0));
        jTextPane1.setText("<html>\r\n  <head>\r\n      <style>\n            .minigame {\n\ttext-align: center;\n\tpadding: 10%;\n\tfont-size: 14px;\n             }\n       </style>\r\n  </head>\r\n  <body>\r\n      \r\n  </body>\r\n</html>\r\n"); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(enterBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo privato che permette di richiedere il focus sulla jTextField1.
     *
     * @param evt evento mouse click.
     */
    private void formMouseClicked(MouseEvent evt) {
        jTextField1.requestFocus();
    }

    /**
     * Metodo privato che viene chiamato quando si preme ENTER.
     *
     * @param evt evento Key Pressed.
     */
    private void jTextField1KeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.checkWin();
                break;
        }
    }

    /**
     * Metodo privato che viene chiamato quando viene usato il pulsante ENTER.
     *
     * @param evt evento Key Pressed.
     */
    private void enterBtnKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.checkWin();
                break;
        }
    }

    /**
     * Metodo privato che viene chiamato quando viene usato il pulsante ENTER.
     *
     * @param evt evento Key Pressed.
     */
    private void enterBtnMouseClicked(MouseEvent evt) {
        this.checkWin();
    }

    /**
     * Metodo privato che controlla se la sequenza è quella vincente.
     */
    private void checkWin() {
        String input;

        input = jTextField1.getText();
        if (!input.toLowerCase().equals(winning.toLowerCase().trim())) {
            audio.playAlert();
            JOptionPane.showMessageDialog(this, "Errore!\nSequenza errata, riprova", "Errore", 0);
            jTextField1.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Complimenti, hai trovato la sequenza corretta!", "Complimenti!", 3);
            solved = true;
            this.dispose();
        }
    }

    /**
     * Metodo che restituisce il valore dell'attributo "solved".
     *
     * @return <code>true</code> se il minigioco è stato risolto;
     * <code>false</code> altrimenti.
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Metodo privato che inizializza il gioco, creando la matrice e
     * riempiendola, infine avvalorizza l'attributo "winning".
     */
    private void initMiniGame() {
        this.fillMat();
        HashMap<String, Integer> freq = this.findFrequencies();
        winning = this.findMax(freq);

        jTextPane1.setText("<div class=\"minigame\";>" + this.matrixString() + "</div>");
        System.out.println(winning);
    }

    /**
     * Metodo privato che restituisce il valore della singola cella nella
     * matrice in posizione i*j.
     *
     * @param i numero riga
     * @param j numero colonna
     * 
     * @return la stringa contenuta nella cella
     */
    private String getCell(int i, int j) {
        return this.matrix[i][j];
    }

    /**
     * Metodo privato che avvalorizza la singola cella nella matrice in
     * posizione i*j.
     *
     * @param i numero riga
     * @param j numero colonna
     * @param combination stringa da inserire nella cella
     */
    private void setCell(int i, int j, String combination) {
        this.matrix[i][j] = combination;
    }

    /**
     * Metodo privato che si occupa di avvalorare l'intera matrice con stringhe
     * esadecimali.
     */
    private void fillMat() {
        String combination;
        int number;
        int k;
        for (int i = 0; i < HEIGHT_MATRIX; i++) {
            for (int j = 0; j < WIDTH_MATRIX; j++) {
                number = (int) (Math.random() * 10);
                k = (int) (Math.random() * 6);
                combination = LETTERS[k] + number;
                this.setCell(i, j, combination);
            }
        }
    }

    /**
     * Metodo privato che calcola la frequenza delle singole combinazioni nella
     * matrice.
     *
     * @return hashmap con chiave = combinazione; valore = frequenza.
     */
    private HashMap<String, Integer> findFrequencies() {
        HashMap<String, Integer> freq = new HashMap<>();
        String cell;
        for (int i = 0; i < HEIGHT_MATRIX; i++) {
            for (int j = 0; j < WIDTH_MATRIX; j++) {
                cell = this.getCell(i, j);
                if (!freq.containsKey(cell)) {
                    freq.put(cell, 1);
                } else {
                    freq.put(cell, freq.get(cell) + 1);
                }
            }
        }

        return freq;
    }

    /**
     * Metodo privato che trova la combinazione con maggiore frequenza nella
     * matrice.
     *
     * @param freq hashmap con le frequenze per ogni combinazione.
     * 
     * @return stringa combinazione con frequenza maggiore.
     */
    private String findMax(HashMap<String, Integer> freq) {
        String combination;
        int max = 0;
        Entry<String, Integer> max_comb = null;
        for (Map.Entry<String, Integer> set : freq.entrySet()) {
            if (max == 0) {
                max = set.getValue();
                max_comb = set;
            } else if (max < set.getValue()) {
                max = set.getValue();
                max_comb = set;
            }
        }

        if (!this.checkMax(max_comb, freq)) {
            boolean flag = false;
            do {
                int i = (int) (Math.random() * HEIGHT_MATRIX);
                int j = (int) (Math.random() * WIDTH_MATRIX);

                if (!this.getCell(i, j).equals(max_comb.getKey())) {
                    this.setCell(i, j, max_comb.getKey());
                    flag = true;
                }
            } while (!flag);
        }

        combination = max_comb.getKey();

        return combination;
    }

    /**
     * Metodo privato che controlla se esiste più di una combinazione con più
     * alta frequenza.
     *
     * @param max entry con chiave = combinazione; valore = frequenza, da
     * comparare con le altre combinazioni.
     * @param freq hashmap con tutta la lista di frequenze per ogni
     * combinazione.
     * 
     * @return true se max > di ogni frequenza; false altrimenti.
     */
    private boolean checkMax(Entry<String, Integer> max, HashMap<String, Integer> freq) {
        boolean flag = true;

        freq.remove(max.getKey());
        if (freq.containsValue(max.getValue())) {
            flag = false;
        }

        return flag;
    }

    /**
     * Metodo privato che converte la matrice in una stringa.
     *
     * @return stringa che corrisponde alla matrice.
     */
    private String matrixString() {
        String str = "";

        for (int i = 0; i < HEIGHT_MATRIX; i++) {
            for (int j = 0; j < WIDTH_MATRIX; j++) {
                str = str + " " + this.getCell(i, j);
            }
            str = str + "<br>";
        }

        return str;
    }
}
