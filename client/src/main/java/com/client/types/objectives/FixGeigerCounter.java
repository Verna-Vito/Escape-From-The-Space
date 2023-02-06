/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.objectives;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.items.GeigerCounter;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe che rappresenta l'obiettivo che riguarda il riparare il Geiger
 * Counter.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Objective
 */
public class FixGeigerCounter extends Objective {

    /**
     * Costruttore della classe FixGeigerCounter.
     *
     * @param id numero obiettivo.
     * @param db il database dal quale leggere le varie informazioni
     * dell'obiettivo FixGeigerCounter.
     * 
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public FixGeigerCounter(int id, DB db) throws SQLException {
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
        GeigerCounter geiger;

        if (!gf.getInventory().isEmpty()) {
            for (Item item : gf.getInventory()) {
                if (item.getName().toLowerCase().equals("contatore di geiger")) {
                    geiger = (GeigerCounter) item;

                    if (geiger.isFixed()) {
                        geiger.setUsable(true);
                        this.setCompleted(true);
                    }
                    break;
                }
            }
        }
    }
}
