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
 * Classe che estende il tipo Room e crea l'istanza della stanza <b>Zona Medica
 * e Ospedale</b>.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see com.client.types.Room
 */
public class Hospital extends Room {

    /**
     * Costruttore della classe Hospital.
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
    public Hospital(String name, DB db, List<Item> items) throws SQLException {
        super(name, db, items);
    }
}
