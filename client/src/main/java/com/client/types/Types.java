/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types;

import java.util.List;

/**
 * Questa interfaccia offre metodi comuni ai nuovi tipi di oggetti, <b>Item</b>,
 * <b>Room</b>.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see com.client.types.Room
 * @see com.client.types.Item
 */
public interface Types {

    /**
     * Metodo che si occupa di recuperare il nome dell'oggetto da cui viene
     * chiamato.
     *
     * @return il nome dell'oggetto.
     */
    public String getName();

    /**
     * Metodo che si occupa di recuperare la lista degli alias dell'oggetto da
     * cui viene chiamato.
     *
     * @return la lista degli alias dell'oggetto.
     */
    public List<String> getAlias();

    /**
     * Metodo che si occupa di impostare la lista degli alias dell'oggetto.
     *
     * @param alias la lista degli alias dell'oggetto.
     */
    public void setAlias(List<String> alias);

    /**
     * Metodo che si occupa di recuperare la descrizione dell'oggetto da cui
     * viene chiamato.
     *
     * @return la descrizione dell'oggetto.
     */
    public String getDesc();

    /**
     * Metodo che si occupa di impostare la descrizione dell'oggetto.
     *
     * @param desc la descrizione dell'oggetto.
     */
    public void setDesc(String desc);

    /**
     * Metodo che crea un codice hash <b>univoco</b> dell'oggetto.
     *
     * @return il codice hash univoco.
     */
    @Override
    public int hashCode();

    /**
     * Metodo che permette di comparare due oggetti.
     *
     * @param obj l'oggetto da comparare.
     *
     * @return true se gli oggetti sono uguali; false altrimenti.
     */
    @Override
    public boolean equals(Object obj);

    /**
     * Metodo che si occupa di convertire l'oggetto in una stringa.
     *
     * @return la stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString();
}
