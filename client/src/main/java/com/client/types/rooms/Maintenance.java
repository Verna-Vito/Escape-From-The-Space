/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.rooms;

import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Room;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe che estende il tipo Room e crea l'istanza della stanza <b>Sala
 * Manutenzione e Elettrica</b>.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see com.client.types.Room
 */
public class Maintenance extends Room {

    /**
     * Attributo che indica se il giocatore è già entrato o meno nella Sala
     * Manutenzione e Elettrica.
     */
    private boolean firstTimeEntered = false;

    /**
     * Costruttore della classe Maintenance.
     *
     * @param name nome della stanza
     * @param db database dove sono presenti i valori degli attributi della
     * stanza.
     * @param items la lista di tutti gli item.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @see Room#Room(java.lang.String, com.client.gameutil.DB, java.util.List)
     */
    public Maintenance(String name, DB db, List<Item> items)
            throws SQLException {
        super(name, db, items);
    }

    /**
     * Metodo che restituisce <code>true</code> se è la prima volta che il
     * giocatore entra in questa stanza; <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo firstTimeEntered
     */
    public boolean isFirstTimeEntered() {
        return firstTimeEntered;
    }

    /**
     * Metodo che imposta a <code>true</code> se è la prima volta che il
     * giocatore entra in questa stanza; <code>false</code> altrimenti.
     *
     * @param firstTimeEntered il valore da assegnare all'attributo
     * firstTimeEntered
     */
    public void setFirstTimeEntered(boolean firstTimeEntered) {
        this.firstTimeEntered = firstTimeEntered;
    }
}
