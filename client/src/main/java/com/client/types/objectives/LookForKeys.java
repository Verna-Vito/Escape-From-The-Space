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
 * Classe che rappresenta l'obiettivo che riguarda il trovare i due item Key
 * prensenti nel gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Objective
 */
public class LookForKeys extends Objective {

    /**
     * Costruttore della classe LookForKeys.
     *
     * @param id numero obiettivo.
     * @param db il database dal quale leggere le varie informazioni
     * dell'obiettivo LookForKeys.
     * 
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public LookForKeys(int id, DB db) throws SQLException {
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
        Item key1 = null;
        Item key2 = null;

        if (!gf.getInventory().isEmpty()) {
            for (int i = 0; i < gf.getInventory().size(); i++) {
                if (gf.getInventory().get(i).getName().toLowerCase().equals("chiave 1")) {
                    key1 = gf.getInventory().get(i);
                } else if (gf.getInventory().get(i).getName().toLowerCase().equals("chiave 2")) {
                    key2 = gf.getInventory().get(i);
                }

                if (key1 != null && key2 != null) {
                    this.setCompleted(true);

                    break;
                }
            }
        }
    }
}
