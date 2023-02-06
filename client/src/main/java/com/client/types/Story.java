/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types;

import com.client.gameutil.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Questa classe fornisce metodi per la manipolazione dei vari pezzi di storia
 * durante l'esecuzione del gioco.
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
public class Story implements Serializable {

    /**
     * Rappresenta la query di lettura da fare al database per recuperare le
     * informazioni sul pezzo di storia.
     */
    private static final String READ_FROM_DB
            = "SELECT * FROM Story WHERE ID = ?";

    /**
     * Attributo che rappresenta il capitolo della storia.
     */
    private final int chapter;

    /**
     * Attributo che rappresenta il sotto-capitolo della storia.
     */
    private int subChapter = 0;

    /**
     * Attributo che contiene tutti i vari pezzi di storia di quel determinato
     * capitolo.
     */
    private String[] story;

    /**
     * Attributo di tipo lista che contiene gli obbiettivi di questo capitolo di
     * storia.
     */
    private List<Objective> objectives = new LinkedList<>();

    /**
     * Costruttore della classe Story.
     *
     * @param chapter capitolo della storia
     * @param db database dal quale leggere le informazioni del pezzo di storia
     * @param listObjectives lista di tutti gli obbiettivi
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o il database
     */
    public Story(int chapter, DB db, List<Objective> listObjectives)
            throws SQLException {

        this.chapter = chapter;
        this.story = new String[100];
        initStory(db, listObjectives);
    }

    /**
     * Metodo che si occupa di inizializzare tutti gli oggetti di tipo Story.
     *
     * <p>
     * Metodo che viene chiamato dal costruttore della classe, per impostare i
     * vari valori agli attributi del pezzo di storia.
     * </p>
     *
     * @param database database dal quale leggere le informazioni del pezzo di
     * storia
     * @param listObjectives lista di tutti gli obbiettivi
     *
     * @throws SQLException per eventuali problemi con l'SQL e/o il database
     */
    private void initStory(DB database, List<Objective> listObjectives) throws SQLException {
        String[] storyString = database.readStory(READ_FROM_DB, this.chapter);
        String[] pieceOfStory = storyString[1].split("SPLIT");

        for (int i = 0; i < pieceOfStory.length; i++) {
            if (i != pieceOfStory.length - 1) {
                pieceOfStory[i] = pieceOfStory[i]
                        .concat("<br/><br/><i>Scrivi 'leggi' per continuare...</i>");
            }
        }

        this.setStory(pieceOfStory);
        this.searchObjectives(listObjectives, storyString[2]);
    }

    /**
     * Metodo che si occupa della ricerca degli obbiettivi del pezzo di storia.
     *
     * <p>
     * Questo metodo viene chiamato nel metodo <i>initStory</i>, avendo come
     * parametri di input la <b>lista di tutti gli obbiettivi del gioco</b> e la
     * <b>stringa che rappresenta gli obbiettivi del pezzo di storia</b>. La
     * stringa viene divisa sul carattere separatore utilizzato, cerca
     * l'obbiettivo corrispondente e lo inserisce nella lista degli obbiettivi
     * della storia.</p>
     *
     * @param listObjectives la lista di tutti gli obbiettivi del gioco.
     * @param objective la stringa rappresentante tutti gli obbiettivi del pezzo
     * di storia
     */
    private void searchObjectives(List<Objective> listObjectives,
            String objective) {

        String[] objectiveString = objective.split(", ");

        for (String objectiveString1 : objectiveString) {
            for (int i = 0; i < listObjectives.size(); i++) {
                if (!objectiveString1.equals("null")) {
                    if (listObjectives.get(i).getId()
                            == Integer.parseInt(objectiveString1)) {
                        this.setObjective(listObjectives.get(i));
                        break;
                    }
                }
            }
        }
    }

    /**
     * Metodo che restituisce il capitolo del pezzo di storia.
     *
     * @return il capitolo della storia
     */
    public int getChapter() {
        return this.chapter;
    }

    /**
     * Metodo che si occupa di restituire la lunghezza del pezzo di storia.
     *
     * @return la lunghezza dell'array che contiene il pezzo di storia
     */
    public int getLenStory() {
        return this.story.length -1;
    }

    /**
     * Metodo che si occupa di restituire il pezzo di storia tenendo conto del
     * sotto-capitolo.
     *
     * @return il pezzo di storia
     */
    public String getPieceStory() {
        return story[this.subChapter];
    }

    /**
     * Metodo che si occupa di impostare l'array che contiene la storia.
     *
     * @param story la storia
     */
    public void setStory(String[] story) {
        this.story = story;
    }

    /**
     * Metodo che si occupa di restituire la lista degli obbiettivi del pezzo di
     * storia.
     *
     * @return la lista degli obbiettivi
     */
    public List<Objective> getObjectives() {
        return objectives;
    }

    /**
     * Metodo che si occupa di impostare la lista degli obbiettivi del pezzo di
     * storia.
     *
     * @param objective l'obbiettivo da aggiungere alla lista
     */
    public void setObjective(Objective objective) {
        this.objectives.add(objective);
    }

    /**
     * Metodo che si occupa di restituire il sotto-capitolo della storia.
     *
     * @return il sotto-capitolo
     */
    public int getSubChapter() {
        return subChapter;
    }

    /**
     * Metodo che si occupa di impostare il sotto-capitolo della storia.
     *
     * @param subChapter il sotto-capitolo
     */
    public void setSubChapter(int subChapter) {
        this.subChapter = subChapter;
    }

    /**
     * Metodo che crea un codice hash <b>univoco</b> dell'oggetto.
     *
     * @return il codice hash univoco.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.chapter;
        hash = 43 * hash + this.subChapter;
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
        final Story other = (Story) obj;
        if (this.chapter != other.chapter) {
            return false;
        }
        return this.subChapter == other.subChapter;
    }

    /**
     * Metodo che si occupa di convertire l'oggetto in una stringa.
     *
     * @return la stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "story= " + Arrays.toString(story)
                + " objectives= " + objectives;
    }
}
