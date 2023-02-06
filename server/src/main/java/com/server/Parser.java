/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe che si occupa di riconoscere l'input inserito dall'utente.
 *
 * <p>
 * Questa classe è istanziata dal server per riconoscere l'input inserito
 * dall'utente.</p>
 *
 * Fornisce metodi di base per il riconoscimento:
 * <ul>
 * <li>metodo pubblico parse(String);</li>
 * <li>metodo privato tokenizer(String).</li>
 * </ul>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Parser#parse(java.lang.String)
 * @see Parser#tokenizer(java.lang.String)
 */
public class Parser {

    /**
     * Questo metodo si occupa di riconoscere il comando inserito dall'utente.
     *
     * <p>
     * Se il token selezionato è diverso da <i>null</i> allora controlla se il
     * comando è uguale ad uno dei comandi esistenti, se lo è, rimuove il
     * comando e inserisce il suo codice operativo corrispondente. </p>
     *
     * @param input la stringa inserita dall'utente.
     *
     * @return id del comando se è corretto, altrimenti 0.
     *
     * @throws java.io.FileNotFoundException nel caso in cui il file dal quale
     * leggere le stopwords sia inesistente.
     *
     * @see IDsCommand#getId()
     * @see Parser#tokenizer(java.lang.String)
     */
    public List<String> parse(String input) throws FileNotFoundException, IOException {
        List<String> tokens = tokenizer(input);
        String token = "";
        Integer commandId = IDsCommand.NOT_VALID.getId();

        if (!tokens.isEmpty()) {
            token = tokens.get(0);
        }

        if (token != null) {
            for (int i = 0; i < IDsCommand.getMaximumAliasSize(); i++) {
                if (i < IDsCommand.GOTO.getAlias().length && token.equals(IDsCommand.GOTO.getAlias()[i])) {
                    commandId = IDsCommand.GOTO.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.PICKUP.getAlias().length && token.equals(IDsCommand.PICKUP.getAlias()[i])) {
                    commandId = IDsCommand.PICKUP.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.LOOK.getAlias().length && token.equals(IDsCommand.LOOK.getAlias()[i])) {
                    commandId = IDsCommand.LOOK.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.USE.getAlias().length && token.equals(IDsCommand.USE.getAlias()[i])) {
                    commandId = IDsCommand.USE.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                } else if (i < IDsCommand.QUIT.getAlias().length && token.equals(IDsCommand.QUIT.getAlias()[i])) {
                    commandId = IDsCommand.QUIT.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.COMBINE.getAlias().length && token.equals(IDsCommand.COMBINE.getAlias()[i])) {
                    commandId = IDsCommand.COMBINE.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.HELP.getAlias().length && token.equals(IDsCommand.HELP.getAlias()[i])) {
                    commandId = IDsCommand.HELP.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.START.getAlias().length && token.equals(IDsCommand.START.getAlias()[i])) {
                    commandId = IDsCommand.START.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                } else if (i < IDsCommand.READ.getAlias().length && token.equals(IDsCommand.READ.getAlias()[i])) {
                    commandId = IDsCommand.READ.getId();
                    tokens.remove(0);
                    tokens.add(0, commandId.toString());
                    break;
                }
            }

            if (Objects.equals(commandId, IDsCommand.NOT_VALID.getId())) {
                tokens.clear();
                tokens.add(0, commandId.toString());
            }

            if (token.equals(" ")) {
                commandId = IDsCommand.NOT_VALID.getId();
                tokens.remove(0);
                tokens.add(0, commandId.toString());
            }
        }

        return tokens;
    }

    /**
     * Questo metodo pulisce e tokenizza la stringa data in input dall'utente.
     *
     * <p>
     * Controlla se la stringa non è vuota e se non uguale a <i>null</i>, a
     * questo punto rimuove tutti i caratteri non alfanumerici con l'utilizzo di
     * una espressione regolare; dopodiché spezza la stringa sugli spazi
     * inserendo ogni stringa in una <b>Lista di stringhe</b>; quindi, elimina
     * dalla lista di stringhe tutte le parole contenute nel file
     * resources/stopWord.txt.</p>
     *
     * @param input la stringa inserita dall'utente.
     *
     * @return la lista di token.
     *
     * @throws java.io.FileNotFoundException nel caso in cui il file dal quale
     * leggere le stopwords sia inesistente.
     */
    private List<String> tokenizer(String input) throws FileNotFoundException, IOException {
        String CLEAR_REGEX = "[^a-zA-Z0-9]";
        List<String> listWords = new ArrayList<>();

        if (input != null && !input.isEmpty()) {
            input = input.replaceAll(CLEAR_REGEX, " ");

            listWords.addAll(Arrays.asList(input.split(" ")));

            Scanner reader = new Scanner(new BufferedReader(new FileReader(new File("resources"+ File.separator +"stopwords.txt").getAbsoluteFile())));
            while (reader.hasNext()) {
                String stopWord = reader.nextLine().trim().toLowerCase();

                for (int i = 0; i < listWords.size(); i++) {
                    if (listWords.get(i).equals(stopWord)
                            || listWords.get(i).equals("")) {

                        listWords.remove(i);
                    }
                }
            }
            reader.close();
        }

        return listWords;
    }
}
