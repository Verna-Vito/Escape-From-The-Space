/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form;

import com.client.types.Item;
import java.awt.Frame;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 * Classe che permette la visualizzazione dell'immagine dell'item cliccato
 * dall'ItemFrame.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 * @see com.client.form.gameframe.GameFrame
 */
public class ItemImageDialog extends JDialog {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel img;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * Attributo che memorizza l'item da visualizzare.
     */
    private Item item;

    /**
     * Costrutture della classe ItemImageDialog
     *
     * @param parent Frame di aggancio
     * @param modal impedisce l'utilizzo del frame sottostante
     * @param item l'item da visualizzare
     */
    public ItemImageDialog(Frame parent, boolean modal, Item item) {
        super(parent, modal);
        this.item = item;
        initComponents();

        ImageIcon icon = new ImageIcon(item.getIMAGE_PATH());
        Image image = icon.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);

        img.setIcon(new ImageIcon(image));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Immagine " + item.getName());
        setPreferredSize(new java.awt.Dimension(250, 250));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                imgKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                imgKeyPressed(evt);
            }
        });

        img.setBackground(new java.awt.Color(230, 230, 230));
        img.setForeground(new java.awt.Color(0, 0, 0));
        img.setOpaque(true);
        img.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                imgKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(img, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
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
     * Metodo privato che permette chiudere questo frame alla pressione di un
     * tasto qualsiasi della tastiera.
     *
     * @param evt evento tasto premuto sulla tastiera.
     */
    private void imgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imgKeyPressed
        this.dispose();
    }//GEN-LAST:event_imgKeyPressed
}
