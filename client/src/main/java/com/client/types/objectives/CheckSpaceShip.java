/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.objectives;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Objective;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe che rappresenta l'obiettivo che riguarda il controllare la Spaceship.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Objective
 */
public class CheckSpaceShip extends Objective {

    /**
     * Costruttore della classe CheckSpaceShip
     *
     * @param id numero obbiettivo.
     * @param db il database dal quale leggere le varie informazioni
     * dell'obiettivo CheckSpaceShip.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public CheckSpaceShip(int id, DB db) throws SQLException {
        super(id, db);
    }

    /**
     * Metodo che si occupa di verificare se l'obiettivo è stato risolto o meno.
     *
     * @param items lista di item utili all'obiettivo
     * @param gf variabile che permette la gestione del gameframe (storyboard,
     * inventario, ...).
     */
    @Override
    public void checkCompleted(List<Item> items, GameFrame gf) {
        Item item;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().toLowerCase().equals("navicella")) {
                item = items.get(i);

                if (item.isLooked()) {
                    this.setCompleted(true);

                }
                break;
            }
        }
    }
}
