/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che si occupa di definire un id univoco ed una lista di alias per
 * tutti i comandi disponibili.
 *
 * <p>
 * Questa classe offre metodi per il recupero dell'id e degli alias, viene usata
 * dalla classe <b>Parser</b> che si occupa di effettuare dei controlli sulla
 * stringa inserita dall'utente per verificarne la correttezza e se un dato
 * comando esiste o meno.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Parser
 */
public enum IDsCommand {

    /**
     * Si riferisce ad un <b>comando non valido</b>.
     * <p>
     * ID = 0, Alias = non ha alias.</p>
     */
    NOT_VALID(-1, new String[]{}),
    /**
     * Si riferisce al comando <b>Inizia</b>.
     * <p>
     * ID = 1, Alias = "vai", "spostati", "cammina".</p>
     */
    START(0, new String[]{"inizia", "comincia", "avvia"}),
    /**
     * Si riferisce al comando <b>Vai</b>.
     * <p>
     * ID = 1, Alias = "vai", "spostati", "cammina".</p>
     */
    GOTO(1, new String[]{"vai", "spostati", "cammina"}),
    /**
     * Si riferisce al comando <b>Prendi</b>.
     * <p>
     * ID = 2, Alias = "prendi", "raccogli", "preleva".</p>
     */
    PICKUP(2, new String[]{"prendi", "raccogli", "preleva"}),
    /**
     * Si riferisce al comando <b>Guarda</b>.
     * <p>
     * ID = 3, Alias = "guarda", "descrivi", "mostra", "osserva", "controlla",
     * "vedi".</p>
     */
    LOOK(3, new String[]{"guarda", "descrivi", "mostra", "osserva", "controlla", "vedi"}),
    /**
     * Si riferisce al comando <b>Aiuto</b>.
     * <p>
     * ID = 4, Alias = "aiuto".</p>
     */
    HELP(4, new String[]{"aiuto"}),
    /**
     * Si riferisce al comando <b>Combina</b>.
     * <p>
     * ID = 5, Alias = "combina", "unisci", "ripara".</p>
     */
    COMBINE(5, new String[]{"combina", "unisci", "ripara"}),
    /**
     * Si riferisce al comando <b>Usa</b>.
     * <p>
     * ID = 6, Alias = "utilizzi", "unisci".</p>
     */
    USE(6, new String[]{"utilizza", "usa"}),
    /**
     * /**
     * Si riferisce al comando <b>Leggi</b>.
     * <p>
     * ID = 7, Alias = "leggi", "continua".</p>
     */
    READ(7, new String[]{"leggi", "continua"}),
    /**
     * Si riferisce al comando <b>Esci</b>.
     * <p>
     * ID = 8, Alias = "esci", "chiudi", "muori", "ammazzati".</p>
     */
    QUIT(8, new String[]{"esci", "chiudi", "muori", "ammazzati"});

    /**
     * Attributo che rappresenta l'ID univoco del comando.
     */
    private final Integer id;

    /**
     * Attributo che contiene i possibili nomi di un determinato comando.
     */
    private final String[] alias;

    /**
     * Costruttore che si occupa di inizializzare gli attributi privati di
     * questa classe.
     *
     * @param Id l'id univoco del comando.
     * @param alias un array contenente diversi alias per uno stesso comando.
     */
    IDsCommand(final Integer Id, final String[] alias) {
        this.id = Id;
        this.alias = alias;
    }

    /**
     * Metodo che si occupa di restituire l'id del comando.
     *
     * @return l'id del comando
     */
    public Integer getId() {
        return id;
    }

    /**
     * Metodo che si occupa di restituire un array contenente gli alias del
     * comando.
     *
     * @return un array di stringhe che contiene gli alias
     */
    public String[] getAlias() {
        return alias;
    }

    /**
     * Metodo che si occupa di calcolare la lunghezza massia degli array nei
     * quali sono contenuti gli alias dei comandi.
     *
     * @return la lunghezza massima
     */
    public static int getMaximumAliasSize() {
        int size = 0;
        List<Integer> sizes = new ArrayList<>();

        sizes.add(IDsCommand.COMBINE.getAlias().length);
        sizes.add(IDsCommand.GOTO.getAlias().length);
        sizes.add(IDsCommand.HELP.getAlias().length);
        sizes.add(IDsCommand.LOOK.getAlias().length);
        sizes.add(IDsCommand.PICKUP.getAlias().length);
        sizes.add(IDsCommand.QUIT.getAlias().length);
        sizes.add(IDsCommand.START.getAlias().length);
        sizes.add(IDsCommand.READ.getAlias().length);

        for (int i = 0; i < sizes.size(); i++) {
            if (sizes.get(i) > size) {
                size = sizes.get(i);
            }
        }

        return size;
    }
}
