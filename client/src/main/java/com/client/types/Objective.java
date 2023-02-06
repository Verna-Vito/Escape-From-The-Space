/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types;

import com.client.form.gameframe.GameFrame;
import com.client.gameutil.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Questa classe fornisce metodi per la manipolazione dei obbiettivi durante
 * l'esecuzione del gioco.
 *
 * <p>
 * Implementa Serializable per permettere il corretto salvataggio e
 * caricamento.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Serializable
 */
public abstract class Objective implements Serializable {

    /**
     * Rappresenta la query di lettura da fare al database per recuperare le
     * informazioni sull'obbiettivo.
     */
    private static final String READ_FROM_DB
            = "SELECT * FROM Objectives WHERE ID = ?";

    /**
     * Attributo che indica se l'obbiettivo è stato completato o meno.
     */
    private boolean completed = false;

    /**
     * Attributo che indica l'id dell'obbiettivo.
     */
    private final int id;

    /**
     * Attributo che contiene la descrizione dell'obbiettivo.
     */
    private String desc;

    /**
     * Costruttore della classe Objective.
     *
     * @param id l'id dell'obbiettivo
     * @param db il database dal quale leggere le informzioni dell'obbiettivo
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o il database
     */
    public Objective(int id, DB db) throws SQLException {
        this.id = id;
        initObjective(db);
    }

    /**
     * Metodo che si occupa di inizializzare tutti gli oggetti di tipo
     * Objective.
     *
     * <p>
     * Metodo che viene chiamato dal costruttore della classe, per impostare i
     * vari valori agli attributi degli obbiettivi.
     * </p>
     *
     * @param database il database dal quale leggere le informzioni
     * dell'obbiettivo
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o il database
     */
    private void initObjective(DB database) throws SQLException {
        String[] objectiveString;
        objectiveString = database.readObjectives(READ_FROM_DB, this.id);

        this.setDesc(objectiveString[1]);
    }

    /**
     * Metodo che restituisce <code>true</code> se l'obbiettivo è stato
     * completato; <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Metodo che imposta a <code>true</code> se l'obbiettivo è stato
     * completato; <code>false</code> altrimenti
     *
     * @param completed il valore da assegnare all'attributo completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Metodo che si occupa di restituire la descrizione dell'obbiettivo.
     *
     * @return la descrizione dell'obbiettivo
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Metodo che imposta la descrizione dell'obbiettivo.
     *
     * @param desc la descrizione dell'obbiettivo
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Metodo che restituisce l'id dell'obbiettivo.
     *
     * @return l'id dell'obbiettivo
     */
    public int getId() {
        return this.id;
    }

    /**
     * Metodo astratto che le classi che estendono questa super classe devono
     * definire.
     *
     * @param items la lista di tutti gli items del gioco
     * @param gf il frame principale di gioco
     */
    public abstract void checkCompleted(List<Item> items, GameFrame gf);

    /**
     * Metodo che crea un codice hash <b>univoco</b> dell'oggetto.
     *
     * @return il codice hash univoco.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    /**
     * Metodo che permette di comparare due oggetti.
     *
     * @param obj l'oggetto da comparare.
     *
     * @return true se gli oggetti sono uguali; false altrimenti.
     */
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
        final Objective other = (Objective) obj;
        return this.id == other.id;
    }

    /**
     * Metodo che si occupa di convertire l'oggetto in una stringa.
     *
     * @return la stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return " - " + desc;
    }
}
