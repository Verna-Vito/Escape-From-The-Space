/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types;

import com.client.gameutil.DB;
import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Classe astratta che definisce il tipo Item.
 *
 * <p>
 * Implementa le interfacce Serializable per permettere il salvataggio dei vari
 * item e Types che definisce alcuni metodi comuni tra Room e Item.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see com.client.types.Room
 * @see com.client.types.Types
 * @see Serializable
 */
public abstract class Item implements Types, Serializable {

    /**
     * Rappresenta la query di lettura da fare al database per recuperare le
     * informazioni sull'oggetto.
     */
    private static final String READ_FROM_DB = 
            "SELECT * FROM Item WHERE Name = ?";

    /**
     * Rappresenta la cartella in cui si trova l'immagine dell'oggetto.
     */
    private final String IMAGE_FOLDER = 
            "resources" + File.separator + "imgs" + File.separator;

    /**
     * Rappresenta il PATH di dove è conservata l'immagine dell'oggetto.
     */
    private String IMAGE_PATH;

    /**
     * Rappresenta il nome dell'oggetto.
     */
    private final String name;

    /**
     * Rappresenta una breve descrizione dell'oggetto.
     */
    private String desc;

    /**
     * Rappresena la lista degli alias dell'oggetto.
     */
    private List<String> alias = new ArrayList<>();

    /**
     * Indica se l'oggetto può essere preso o meno.
     */
    private boolean pickupable;

    /**
     * Indica se l'oggetto può essere usato o meno.
     */
    private boolean usable;

    /**
     * Indica se l'oggetto può essere guardato o meno.
     */
    private boolean lookable;

    /**
     * Indica se l'oggetto è stato usato o meno.
     */
    private boolean used;

    /**
     * Indica se l'oggetto è stato usato o meno.
     */
    private boolean looked;

    /**
     * Indica se l'oggetto può essere combinato con altri o meno.
     */
    private boolean combinable;

    /**
     * Costruttore della classe Item.
     *
     * @param name nome dell'item
     * @param db database dove sono presenti i valori degli attributi dell'item
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public Item(String name, DB db) throws SQLException {
        this.name = name;
        initItem(db);
    }

    /**
     * Metodo che si occupa di inizializzare tutti gli attributi dell'oggetto in
     * questione.
     *
     * <p>
     * Questo metodo chiama un metodo di lettura dal database, nel quale, sono
     * conservati tutti gli Item di questo gioco; restituisce un array di
     * stringhe contenenti tutti i valori degli attributi di uno specifico Item;
     * successivamente con i rispettivi metodi di impostazione, vengono
     * impostati i vari valori.</p>
     *
     * @param database il database dal quale leggere le informazioni dell'item
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @see DB#readItem(java.lang.String, java.lang.String)
     * @see Item#setDesc(java.lang.String)
     * @see Item#setAlias(java.util.List)
     * @see Item#setCombinable(boolean)
     * @see Item#setImagePath(java.lang.String)
     * @see Item#setOpenable(boolean)
     * @see Item#setPickupable(boolean)
     * @see Item#setUsable(boolean)
     */
    private void initItem(DB database) throws SQLException {
        String[] itemString = database.readItem(READ_FROM_DB, this.name);

        //System.out.println(Arrays.toString(itemString));
        this.setDesc(itemString[1]);
        this.setAlias(Arrays.asList(itemString[2].split(", ")));
        this.setLookable(Boolean.parseBoolean(itemString[3]));
        this.setPickupable(Boolean.parseBoolean(itemString[4]));
        this.setUsable(Boolean.parseBoolean(itemString[5]));
        this.setCombinable(Boolean.parseBoolean(itemString[6]));
        this.setImagePath(itemString[7]);
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto può essere preso;
     * <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo pickupable
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'oggetto può essere preso;
     * <code>false</code> altrimenti.
     *
     * @param pickupable il valore da assegnare all'attributo pickupable
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto può essere usato;
     * <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo usable
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'oggetto può essere usato;
     * <code>false</code> altrimenti.
     *
     * @param usable il valore da assegnare all'attributo usable
     */
    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto può essere
     * combinato; <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo combinable
     */
    public boolean isCombinable() {
        return combinable;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'oggetto può essere combinato;
     * <code>false</code> altrimenti.
     *
     * @param combinable il valore da assegnare all'attributo combinable
     */
    public void setCombinable(boolean combinable) {
        this.combinable = combinable;
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto è guardabile;
     * <code>false</code> altrimenti.
     *
     * @return il valore da assegnare all'attributo lookable
     */
    public boolean isLookable() {
        return lookable;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'oggetto è guardabile;
     * <code>false</code> altrimenti.
     *
     * @param lookable il valore da assegnare all'attributo lookable
     */
    public void setLookable(boolean lookable) {
        this.lookable = lookable;
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto è stato guardato;
     * <code>false</code> altrimenti.
     *
     * @return il valore da assegnare all'attributo looked
     */
    public boolean isLooked() {
        return looked;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'oggetto è stato guardato;
     * <code>false</code> altrimenti.
     *
     * @param looked il valore da assegnare all'attributo looked
     */
    public void setLooked(boolean looked) {
        this.looked = looked;
    }

    /**
     * Recupera il Path relativo dell'immagine dell'oggetto.
     *
     * @return il path dell'immagine.
     */
    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    /**
     * Imposta il path relativo dell'immagine dell'oggetto.
     *
     * @param IMAGE_PATH il nome dell'immagine.
     */
    public void setImagePath(String IMAGE_PATH) {
        this.IMAGE_PATH = IMAGE_FOLDER + IMAGE_PATH + ".png";
    }

    /**
     * Metodo che restituisce <code>true</code> se l'oggetto è stato usato;
     * <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'oggetto è stato usato;
     * <code>false</code> altrimenti.
     *
     * @param used il valore da assegnare all'attributo used
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Metodo che converte in stringa una serie di attributi dell'oggetto in
     * questione.
     *
     * @return attributi che sono avvalorati a <i>true</i>.
     */
    public String itemToString() {
        String toString = "";

        if (this.isCombinable()) {
            toString = toString + "Combinabile; ";
        }

        if (this.isPickupable()) {
            toString = toString + "Prendibile; ";
        }

        if (this.isUsable()) {
            toString = toString + "Usabile; ";
        }

        if (toString.equals("")) {
            toString = "Niente di interessante da dire su questo oggetto";
        }

        toString = toString.trim();

        return toString;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getAlias() {
        return alias;
    }

    @Override
    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
