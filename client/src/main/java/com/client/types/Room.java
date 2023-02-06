/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.types;

import com.client.command.Command;
import com.client.form.gameframe.GameFrame;
import com.client.gameutil.DB;
import com.client.types.rooms.Earth;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Classe astratta che definisce il tipo Room.
 *
 * <p>
 * Implementa le interfacce Serializable per permettere il salvataggio delle
 * varie stanze e Types che definisce alcuni metodi comuni tra Room e Item.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see com.client.types.Item
 * @see com.client.types.Types
 * @see Serializable
 */
public abstract class Room implements Types, Serializable {

    /**
     * Attributo privato che contiene la query da eseguire sul database.
     */
    private static final String READ_FROM_DB = "SELECT "
            + "Description, "
            + "Alias, "
            + "Look, "
            + "Items "
            + "FROM Room WHERE Name = ?";

    /**
     * Attributo di tipo Room che contiene la stanza a Nord a partire da questa
     * stanza.
     */
    private Room north;

    /**
     * Attributo di tipo Room che contiene la stanza a Sud a partire da questa
     * stanza.
     */
    private Room south;

    /**
     * Attributo di tipo Room che contiene la stanza a Est a partire da questa
     * stanza.
     */
    private Room east;

    /**
     * Attributo di tipo Room che contiene la stanza a Ovest a partire da questa
     * stanza.
     */
    private Room west;

    /**
     * Attributo di tipo String che contiene il nome della stanza.
     */
    private final String name;

    /**
     * Attributo di tipo String che contiene la descrizione della stanza.
     */
    private String desc;

    /**
     * Attributo di tipo String che contiene la descrizione dettigliata della
     * stanza.
     */
    private String look;

    /**
     * Attributo List di String che contiene tutti gli alias del
     * nome/orientamento della stanza.
     */
    private List<String> alias = new ArrayList<>();

    /**
     * Attributo List di tipo Item che contiene tutti gli item presenti nella
     * stanza.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Attributo Map di tipo String e integer che indica se il comando è
     * disponibile o meno all'interno della stanza.
     */
    private Map<String, Integer> availableCommands = new LinkedHashMap<>();

    /**
     * Attributo di tipo String che contiene il comando GOTO.
     */
    protected static final String GOTO = " - Vai";

    /**
     * Attributo di tipo String che contiene il comando PICKUP.
     */
    protected static final String PICKUP = " - Prendi (oggetto)";

    /**
     * Attributo di tipo String che contiene il comando LOOK.
     */
    protected static final String LOOK = " - Guarda";

    /**
     * Attributo di tipo String che contiene il comando LOOK_ITEM.
     */
    protected static final String LOOK_ITEM = " - Guarda (oggetto)";

    /**
     * Attributo di tipo String che contiene il comando USE.
     */
    protected static final String USE = " - Usa (oggetto)";

    /**
     * Attributo di tipo String che contiene il comando COMBINE.
     */
    protected static final String COMBINE = " - Combina";

    /**
     * Attributo di tipo String che contiene il comando HELP.
     */
    protected static final String HELP = " - Aiuto";

    /**
     * Attributo di tipo String che contiene il comando HELP.
     */
    protected static final String READ = " - Leggi";

    /**
     * Attributo di tipo String che contiene il comando QUIT.
     */
    protected static final String QUIT = " - Esci";

    /**
     * Costruttore della classe Room
     *
     * @param name nome della stanza
     * @param db database dove sono presenti i valori degli attributi della
     * stanza
     * @param listItems lista oggetti che saranno nella stanza.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public Room(String name, DB db, List<Item> listItems) throws SQLException {
        this.name = name;

        this.availableCommands.put(GOTO, 1);
        this.availableCommands.put(PICKUP, 0);
        this.availableCommands.put(LOOK, 1);
        this.availableCommands.put(LOOK_ITEM, 0);
        this.availableCommands.put(USE, 0);
        this.availableCommands.put(COMBINE, 0);
        this.availableCommands.put(HELP, 1);
        this.availableCommands.put(READ, 1);
        this.availableCommands.put(QUIT, 1);

        initRoom(listItems, db);
    }

    /**
     * Metodo che si occupa di inizializzare tutti gli oggetti di tipo Room.
     *
     * <p>
     * Metodo che viene chiamato dal costruttore della classe, per impostare i
     * vari valori agli attributi della stanza.
     * </p>
     *
     * @param listItems lista oggetti che saranno nella stanza.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    private void initRoom(List<Item> listItems, DB database)
            throws SQLException {

        String[] roomString = database.readRoom(READ_FROM_DB, this.name);

        this.setDesc(roomString[0]);
        this.setAlias(Arrays.asList(roomString[1].split(", ")));
        this.setLook(roomString[2]);

        this.searchItem(listItems, roomString[3]);
    }

    /**
     * Metodo che si occupa della ricerca degli oggetti presenti all'interno
     * della stanza.
     *
     * <p>
     * Questo metodo viene chiamato nel metodo <i>initRoom</i>, avendo come
     * parametri di input la <b>lista di tutti gli oggetti del gioco</b> e la
     * <b>stringa che rappresenta gli oggetti presenti nella stanza</b>. La
     * stringa viene divisa sul carattere separatore utilizzato, cerca l'item
     * corrispondente e lo inserisce nella lista degli oggetti della stanza.</p>
     *
     * @param items la lista di tutti gli oggetti del gioco.
     * @param item la stringa rappresentante tutti gli oggetti presenti nella
     * stanza.
     */
    private void searchItem(List<Item> items, String item) {
        String[] itemString = item.split(", ");

        for (String itemString1 : itemString) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(itemString1)) {
                    this.setItems(items.get(i));
                    break;
                }
            }
        }
    }

    /**
     * Metodo che si occupa di creare la mappa di gioco.
     *
     * <p>
     * Questo metodo crea la mappa di gioco effettuando una ricerca nel
     * Database, infatti, durante l'esecuzione del metodo può essere lanciata un
     * SQLException per eventuali problemi col Database; cerca il nome della
     * stanza nel Database e vede cosa c'è: se uno o più campi delle direzioni
     * <b>NORD</b>, <b>EST</b>, <b>SUD</b> e <b>OVEST</b> sono uguali a
     * <i>null</i> allora significa che la stanza non ha "vicini" e si prosegue
     * con la successiva; altrimenti viene chiamato il metodo privato
     * <i>searchRoom</i> che si occupa di cercare la stanza che ha il nome come
     * quello all'interno della colonna del database corrispondente.</p>
     *
     * <br>
     *
     * <p>
     * <b>ATTENZIONE:</b> chiamare questo metodo dopo aver creato le istanze di
     * tutte le stanze necessarie.</p>
     *
     * @param rooms la lista di tutte le stanze del gioco.
     * @param database il database dal quale leggere il contenuto delle varie
     * direzioni.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public static void createMap(List<Room> rooms, DB database)
            throws SQLException {

        String[] roomDirections;

        for (int i = 0; i < rooms.size(); i++) {
            roomDirections = database.readDirections("SELECT "
                    + "North, "
                    + "East, "
                    + "South, "
                    + "West "
                    + "FROM Room WHERE Name = ?", rooms.get(i).getName());

            if (!roomDirections[1].equals("null")) { //est
                rooms.get(i).setEast(rooms.get(i).searchRoom(
                        roomDirections[1], rooms)
                );
            }
            if (!roomDirections[3].equals("null")) { //ovest
                rooms.get(i).setWest(rooms.get(i).searchRoom(
                        roomDirections[3], rooms)
                );
            }
            if (!roomDirections[2].equals("null")) { //sud
                rooms.get(i).setSouth(rooms.get(i).searchRoom(
                        roomDirections[2], rooms)
                );
            }
            if (!roomDirections[0].equals("null")) { //nord
                rooms.get(i).setNorth(rooms.get(i).searchRoom(
                        roomDirections[0], rooms)
                );
            }
        }
    }

    /**
     * Metodo che si occupa di cercare una determinata stanza all'interno della
     * lista di tutte le stanze del gioco.
     *
     * <p>
     * Questo metodo viene chiamato nel metodo <i>createMap</i>, avendo come
     * parametri di input la <b>lista di tutte le stanze del gioco</b> e il <b>
     * nome della stanza da inserire nella mappa</b> cerca la stanza che ha il
     * nome passato come parametro e la restituisce.</p>
     *
     * @param roomName il nome della stanza da cercare.
     * @param rooms la lista di tutte le stanze del gioco.
     *
     * @return se viene trovata la stanza, allora viene restituita; altrimenti
     * restituisce <code>null</code>;
     */
    private Room searchRoom(String roomName, List<Room> rooms) {
        return rooms.stream()
                .filter(
                        room -> room.getName().toLowerCase()
                                .equals(roomName.toLowerCase())
                ).findFirst().orElse(null);
    }

    /**
     * Metodo che si occupa di restituire la stanza che si trova a Nord.
     *
     * @return la stanza a Nord.
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Metodo che si occupa di impostare la stanza a Nord.
     *
     * @param north la stanza da inserire a Nord.
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * Metodo che si occupa di restituire la stanza che si trova a Sud.
     *
     * @return la stanza a Sud.
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Metodo che si occupa di impostare la stanza a Sud.
     *
     * @param south la stanza da inserire a Sud.
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * Metodo che si occupa di restituire la stanza che si trova a Est.
     *
     * @return la stanza a Est.
     */
    public Room getEast() {
        return east;
    }

    /**
     * Metodo che si occupa di impostare la stanza che si trova a Est
     *
     * @param east la stanza da inserire a Est
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * Metodo che si occupa di restituire la stanza che si trova a Ovest.
     *
     * @return la stanza a Ovest.
     */
    public Room getWest() {
        return west;
    }

    /**
     * Metodo che si occupa di impostare la stanza che si trova a Ovest
     *
     * @param west la stanza da inserire a Ovest
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * Metodo che si occupa di restituire la lista degli oggetti presenti nella
     * stanza.
     *
     * @return la lista degli oggetti.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Metodo che si occupa di impostare gli oggetti della stanza.
     *
     * <p>
     * Questo metodo viene chiamato nel metodo <i>searchItem</i> e si occupa di
     * inserire un oggetto alla volta all'interno della stanza.</p>
     *
     * @param item l'oggetto della stanza.
     */
    public void setItems(Item item) {
        this.items.add(item);
    }

    /**
     * Metodo che si occupa di restituire quello che l'utente vede all'interno
     * della stanza.
     *
     * @return la stringa che descrive l'interno della stanza
     */
    public String getLook() {
        return look;
    }

    /**
     * Metodo che si occupa di impostre quello che l'utente vede all'interno
     * della stanza.
     *
     * @param look la stringa che descrive l'interno della stanza
     */
    public void setLook(String look) {
        this.look = look;
    }

    /**
     * Metodo che si occupa di restituire la variabile che contiene la coppia
     * chiave-valore (String-Integer) che rappresenta i vari comandi
     * disponibili.
     *
     * @return la variabile che contiene la coppia chiave-valore
     */
    public Map<String, Integer> getAvailableCommands() {
        return availableCommands;
    }

    /**
     * Metodo che si occupa di aggiornare la lista dei comandi disponibili
     * all'interno del gameframe.
     *
     * <p>
     * Questo metodo imposta i comandi non comuni tra le stanze a 0 e compie dei
     * controlli per impostare i correttamente i comandi disponibili per quella
     * stanza.</p>
     *
     * @param gf il frame principale di gioco.
     * @param cmd la classe contente i comandi.
     */
    public void updateAvailableCommands(GameFrame gf, Command cmd) {
        int combinableItems = 0;

        if (cmd.getCMDblocked()) {
            for (Map.Entry<String, Integer> availableCmd : this.getAvailableCommands().entrySet()) {
                if (!availableCmd.getKey().equals(Room.HELP) && !availableCmd.getKey().equals(Room.QUIT) && !availableCmd.getKey().equals(Room.READ)) {
                    this.getAvailableCommands().put(availableCmd.getKey(), 0);
                }
            }
        } else {

            for (Map.Entry<String, Integer> availableCmd : this.getAvailableCommands().entrySet()) {
                if (!availableCmd.getKey().equals(Room.HELP) && !availableCmd.getKey().equals(Room.QUIT)) {
                    this.getAvailableCommands().put(availableCmd.getKey(), 0);
                }
            }

            if (this.getEast() != null || this.getNorth() != null || this.getWest() != null || this.getSouth() != null) {
                this.getAvailableCommands().put(GOTO, 1);
            }

            if (this.getClass() == Earth.class) {
                this.availableCommands.put(GOTO, 0);
                this.availableCommands.put(LOOK, 0);
                this.availableCommands.put(HELP, 0);
            }

            if (!gf.getInventory().isEmpty()) {
                for (int i = 0; i < gf.getInventory().size(); i++) {
                    if (gf.getInventory().get(i).isCombinable()) {
                        combinableItems++;
                    }

                    if (gf.getInventory().get(i).isUsable()) {
                        this.getAvailableCommands().put(USE, 1);
                    }
                }
            }

            if (combinableItems > 1) {
                this.getAvailableCommands().put(COMBINE, 1);
            }

            if (!this.getItems().isEmpty()) {
                for (int i = 0; i < this.getItems().size(); i++) {
                    if (this.getItems().get(i).isPickupable()) {
                        this.getAvailableCommands().put(PICKUP, 1);
                    }

                    if (this.getItems().get(i).isLookable()) {
                        this.getAvailableCommands().put(LOOK_ITEM, 1);
                    }

                    if (this.getItems().get(i).isUsable() && !this.getItems()
                            .get(i).isPickupable()) {
                        this.getAvailableCommands().put(USE, 1);
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return this.name;
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
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.name);
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
        final Room other = (Room) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return name + " items= " + items;
    }
}
