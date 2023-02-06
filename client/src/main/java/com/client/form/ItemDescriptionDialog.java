/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form;

import com.client.form.gameframe.GameFrame;
import com.client.types.Item;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 * Classe che permette la visualizzazione di una descrizione dell'item cliccato
 * dall'ItemFrame.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 * @see GameFrame
 */
public class ItemDescriptionDialog extends JDialog {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alias;
    private javax.swing.JTextArea attributes;
    private javax.swing.JTextArea desc;
    private javax.swing.JLabel img;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel name;
    private javax.swing.JButton okBtn;
    // End of variables declaration//GEN-END:variables

    /**
     * Attributo che memorizza l'item di cui avere la descrizione.
     */
    private Item item;

    /**
     * Attributo che consente la corretta visualizzazione del frame contenente
     * solo l'immagine.
     */
    private GameFrame gf;

    /**
     * Costrutture della classe ItemImageDialog
     *
     * @param parent Frame di aggancio
     * @param modal impedisce l'utilizzo del frame sottostante
     * @param item l'item da visualizzare
     * @param gf il gameframe di aggancio per consentire la visualizzazione del
     * frame contenente solo l'immagine dell'item
     *
     * @see ItemImageDialog
     */
    public ItemDescriptionDialog(Frame parent, boolean modal, Item item, GameFrame gf) {
        super(parent, modal);
        this.item = item;
        this.gf = gf;
        initComponents();

        ImageIcon icon = new ImageIcon(item.getIMAGE_PATH());
        Image image = icon.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);

        img.setIcon(new ImageIcon(image));
        name.setText("Nome: " + item.getName());
        desc.setText("Desc: " + item.getDesc());

        if (item.getAlias().isEmpty()) {
            alias.setText("Alias: nessun alias per questo oggetto");
        } else {
            alias.setText("Alias: " + item.getAlias());
        }

        if (item.toString().equals("")) {
            attributes.setText("Attr.: nessun attributo di particolare interesse");
        } else {
            attributes.setText("Attr.: " + item.itemToString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        img = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        okBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        alias = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        desc = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        attributes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Descrizione " + item.getName());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        img.setBackground(new java.awt.Color(230, 230, 230));
        img.setForeground(new java.awt.Color(0, 0, 0));
        img.setText("img");
        img.setOpaque(true);
        img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgMouseClicked(evt);
            }
        });

        name.setBackground(new java.awt.Color(230, 230, 230));
        name.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setText("name");
        name.setOpaque(true);

        okBtn.setText("OK");
        okBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okBtnMouseClicked(evt);
            }
        });
        okBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                okBtnKeyPressed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        alias.setEditable(false);
        alias.setBackground(new java.awt.Color(230, 230, 230));
        alias.setColumns(20);
        alias.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        alias.setForeground(new java.awt.Color(0, 0, 0));
        alias.setLineWrap(true);
        alias.setRows(2);
        alias.setWrapStyleWord(true);
        alias.setBorder(null);
        alias.setFocusable(false);
        jScrollPane1.setViewportView(alias);

        jScrollPane2.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(0, 0, 0));

        desc.setEditable(false);
        desc.setBackground(new java.awt.Color(230, 230, 230));
        desc.setColumns(20);
        desc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        desc.setForeground(new java.awt.Color(0, 0, 0));
        desc.setLineWrap(true);
        desc.setRows(2);
        desc.setWrapStyleWord(true);
        desc.setBorder(null);
        desc.setFocusable(false);
        jScrollPane2.setViewportView(desc);

        jScrollPane3.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(0, 0, 0));

        attributes.setEditable(false);
        attributes.setBackground(new java.awt.Color(230, 230, 230));
        attributes.setColumns(20);
        attributes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        attributes.setForeground(new java.awt.Color(0, 0, 0));
        attributes.setLineWrap(true);
        attributes.setRows(2);
        attributes.setWrapStyleWord(true);
        attributes.setBorder(null);
        attributes.setFocusable(false);
        jScrollPane3.setViewportView(attributes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(469, Short.MAX_VALUE)
                .addComponent(okBtn)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
                        .addComponent(jScrollPane3))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addComponent(okBtn)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(name)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
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
     * Metodo che consente di aprire un altro frame di visualizzazione quando
     * viene fatto click sull'immagine dell'item.
     *
     * @param evt evento click del mouse
     */
    private void imgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMouseClicked
        ItemImageDialog dialog = new ItemImageDialog(gf, true, item);
        dialog.setVisible(true);
    }//GEN-LAST:event_imgMouseClicked

    /**
     * Metodo che consente di chiudere questo frame di visualizzazione quando
     * viene fatto click sul bottone "ok".
     *
     * @param evt evento click del mouse
     */
    private void okBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okBtnMouseClicked
        this.dispose();
    }//GEN-LAST:event_okBtnMouseClicked

    /**
     * Metodo che consente di chiudere questo frame di visualizzazione alla
     * pressione dei tasti della tastiera <b>ENTER o ESC</b>.
     *
     * @param evt evento click dei tasti della tastiera
     */
    private void okBtnKeyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.dispose();
                break;
            case KeyEvent.VK_ESCAPE:
                this.dispose();
                break;
        }
    }
}
