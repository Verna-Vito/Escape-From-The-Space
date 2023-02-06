/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types.items;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.DB;
import com.client.types.Item;
import com.client.types.Room;
import java.awt.Image;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 * Classe che rappresenta la mappa dell'Arca presente nella stanza, uno degli
 * Item di questo gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Item
 */
public class RoomMap extends Item {

    /**
     * Costruttore della classe RoomMap.
     *
     * @param name il nome dell'oggetto.
     * @param db il database dal quale leggere le varie informazioni della
     * mappa.
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o con il DB.
     */
    public RoomMap(String name, DB db) throws SQLException {
        super(name, db);
    }

    /**
     * Metodo pubblico che definisce il comportamento dell'oggetto dopo il
     * comando <code>"guarda oggetto"</code>.
     *
     * @param gameframe variabile che permette la gestione del gameframe
     * (storyboard, inventario, ...).
     * @param i posizione dell'item nella lista di item della stanza attuale.
     * @param actualRoom variabile che indica la stanza attuale.
     */
    public static void look(GameFrame gameframe, int i, Room actualRoom) {
        ImageIcon icon;
        icon = new ImageIcon(actualRoom.getItems().get(i).getIMAGE_PATH());

        Image image = icon.getImage();
        gameframe.getMapLabel().setIcon(new ImageIcon(
                image.getScaledInstance(
                        gameframe.getMapLabel().getWidth(),
                        gameframe.getMapLabel().getHeight(),
                        Image.SCALE_SMOOTH)));

        gameframe.showMap();
    }
}
