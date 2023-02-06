/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.command;

/**
 * Classe che permette un certo grado di programmazione generica, in quanto il
 * tipo da impostare e/o da recuperare viene stabilito dall'utente, offrendo tre
 * metodi generici per l'inserimento e il recupero degli elementi.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @param <K> elemento di tipo K, primo da mettere nelle parentesi angolari.
 * @param <V> elemento di tipo V, secondo da mettere nelle parentesi angolari.
 *
 * @see Generics#put(java.lang.Object, java.lang.Object)
 * @see Generics#getK()
 * @see Generics#getV()
 */
@SuppressWarnings("unchecked")
public class Generics<K, V> {

    //array di tipo Object di dimensione 2
    private final Object array[] = new Object[2];

    /**
     * Metodo che si occupa di restituire la variabile di tipo K.
     *
     * @return la variabile di tipo K.
     */
    public K getK() {
        return (K) array[0];
    }

    /**
     * Metodo che si occupa di restituire la variabile di tipo V.
     *
     * @return la variabile di tipo V.
     */
    public V getV() {
        return (V) array[1];
    }

    /**
     * Metodo che si occupa di inserire le variabili di tipo K e V nelle
     * rispettive posizioni dell'array;
     *
     * @param k elemento di tipo K.
     * @param v elemento di tipo V.
     */
    public void put(K k, V v) {
        array[0] = k;
        array[1] = v;
    }
}
