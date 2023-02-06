/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Room;
import com.client.types.rooms.Earth;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe che rappresenta il contatore di Geiger, uno degli Item di questo
 * gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class GeigerCounter extends Item {

    /**
     * Attributo boolean che indica: true se il GeigerCounter è stato riparato;
     * false altrimenti.
     */
    private boolean fixed = false;

    /**
     * Attributo boolean che indica se il GeigerCounter è stato combinato con
     * l'item Battery.
     *
     * @see Battery
     */
    private boolean battery = false;

    /**
     * Attributo boolean che indica se il GeigerCounter è stato combinato con
     * l'item Display.
     *
     * @see Display
     */
    private boolean display = false;

    /**
     * Costruttore della classe GeigerCounter.
     *
     * @param name il nome dell'oggetto.
     * @param db il database dal quale leggere le varie informazioni del
     * contatore di Geiger.
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB.
     */
    public GeigerCounter(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto è stato riparato;
     * <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo fixed.
     */
    public boolean isFixed() {
        return fixed;
    }

    /**
     * Metodo che imposta l'attributo fixed a <code>true</code> se l'oggetto è
     * stato combinato con Battery e Display; <code>false</code> altrimenti.
     *
     * @see Battery
     * @see Display
     */
    public void setFixed() {
        if (!battery) {
            this.setImagePath("geiger-counter-no_battery");
        } else if (!display) {
            this.setImagePath("geiger-counter-broken_glass");
        } else {
            this.setImagePath("geiger-counter-fixed");
            this.fixed = true;
        }
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"combina oggetto oggetto"</code>.
     *
     * @param item oggetto guardato.
     * @param gf variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     */
    public void combine(Item item, GameFrame gf) {
        if (item.getName().toLowerCase().equals("batterie")) {
            this.setBattery(true);
            this.setFixed();

            JOptionPane.showMessageDialog(gf,
                    "Oggetti combinati con successo!!", "Successo", 3);
            gf.getInventory().remove(item);
            gf.updateInventory();
        } else if (item.getName().toLowerCase().equals("schermo")) {
            this.setDisplay(true);
            this.setFixed();

            JOptionPane.showMessageDialog(gf,
                    "Oggetti combinati con successo!!", "Successo", 3);
            gf.getInventory().remove(item);
            gf.updateInventory();
        }
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto è stato combinato
     * con l'oggetto Battery; <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo battery.
     */
    public boolean isBatteryFixed() {
        return battery;
    }

    /**
     * Metodo che imposta l'attributo battery a <code>true</code> se l'oggetto è
     * stato combinato con Battery; <code>false</code> altrimenti.
     *
     * @param battery il valore da assegnare all'attributo battery
     *
     * @see Battery
     */
    public void setBattery(boolean battery) {
        this.battery = battery;
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto è stato combinato
     * con l'oggetto Display; <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo display.
     */
    public boolean isDisplayFixed() {
        return display;
    }

    /**
     * Metodo che imposta l'attributo display a <code>true</code> se il valore
     * del parametro è true; <code>false</code> altrimenti.
     *
     * @param display il valore da assegnare all'attributo display.
     *
     * @see Display
     */
    public void setDisplay(boolean display) {
        this.display = display;
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"usa oggetto"</code>.
     *
     * @param actualRoom variabile che indica la stanza attuale.
     * @param gameframe variabile che permette la gestione del gameframe
     * (storyboard, inventario, ...).
     * @param audio variabile che permette la gestione dell'audio di gioco.
     */
    public void use(Room actualRoom, GameFrame gameframe, AudioPlayer audio) {

        if (!this.isUsed()) {
            if (actualRoom.getClass() == Earth.class) {
                JOptionPane.showMessageDialog(
                        gameframe,
                        "Misurazione livello di radiazioni in corso...\n"
                        + "Attendere prego!",
                        "Misurando",
                        3);

                this.setUsed(true);

                gameframe.getInventory().remove(this);
                gameframe.updateInventory();
            }
        }
    }
}
