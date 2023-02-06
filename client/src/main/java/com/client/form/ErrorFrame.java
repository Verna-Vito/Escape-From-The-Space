/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.form;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe che permette la visualizzazione di un frame dedicato alle exception 
 * lanciate durante l'esecuzione del gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public class ErrorFrame extends JFrame {

    //Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextArea error;
    private JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * Costruttore della classe.
     * 
     * @param ex l'exception da visualizzare
     */
    public ErrorFrame(Exception ex) {
        initComponents();
        error.setText(ex.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        error = new JTextArea();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Errore Grave");
        setResizable(false);

        jScrollPane1.setBackground(new Color(230, 230, 230));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new Color(0, 0, 0));

        error.setEditable(false);
        error.setColumns(20);
        error.setFont(new Font("Segoe UI", 0, 16)); // NOI18N
        error.setLineWrap(true);
        error.setRows(2);
        error.setWrapStyleWord(true);
        error.setBorder(null);
        error.setFocusable(false);
        error.setOpaque(false);
        jScrollPane1.setViewportView(error);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 388,
                                                Short.MAX_VALUE)
                                        .addContainerGap())));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 288,
                                                Short.MAX_VALUE)
                                        .addContainerGap())));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
}
