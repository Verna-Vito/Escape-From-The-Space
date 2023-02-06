/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.gameutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Classe che offre metodi utili per comunicare con il database, incluso lo
 * stabilimento della connessione.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public class DB {

    /**
     * Attributo di tipo Connection che permette la corretta connessione al
     * database.
     */
    private final Connection con;

    /**
     * Attributo di tipo String che indica il path per raggiungere i file dai
     * quali verranno lette le informazioni da inserire nel database.
     */
    private static final String FILE_PATH
            = "resources" + File.separator + "util" + File.separator;

    /**
     * Costruttore del DB che permette di creare un'istanza del database e
     * stabilire la connessione.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public DB() throws SQLException {
        Properties dbprops = new Properties();
        dbprops.setProperty("user", "user");
        dbprops.setProperty("password", "1234");

        con = DriverManager.getConnection("jdbc:h2:./resources/db/adventure",
                dbprops);
    }

    /**
     * Metodo che si occupa di inizializzare le tabelle del database con i
     * corretti valori.
     *
     * <p>
     * Metodo che si occupa di creare prima la tabella degli Items con i
     * relativi campi, dopodiché chiama il metodo checkItem(ResultSet) che
     * controlla se gli item sono stati già inseriti o meno, nel caso non siano
     * stati ancora inseriti provvede ad inserirli; compie le stesse azioni con
     * le stanze, chiamando il metodo checkRoom(ResultSet), gli obbiettivi
     * chiamando il metodo checkObjective(ResultSet) ed infine con la storia
     * chiamando il metodo checkStory(ResultSet).
     * </p>
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws FileNotFoundException nel caso in cui i file dove sono conservate
     * le informazioni di tutti gli elementi del gioco siano inesistenti.
     *
     * @see DB#checkItem(java.sql.ResultSet)
     * @see DB#checkRoom(java.sql.ResultSet)
     * @see DB#checkObjective(java.sql.ResultSet)
     * @see DB#checkStory(java.sql.ResultSet)
     */
    public void init() throws SQLException, FileNotFoundException {
        String READ_FROM_DB = "SELECT Name FROM Item";

        // Item
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Item ("
                + "Name VARCHAR(1024), "
                + "Description VARCHAR(10000), "
                + "Alias VARCHAR(1024),"
                + "Lookable BOOLEAN,"
                + "Pickupable BOOLEAN,"
                + "Usable BOOLEAN,"
                + "Combinable BOOLEAN,"
                + "FileName VARCHAR(50),"
                + "PRIMARY KEY(Name))";

        Statement stm = con.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();

        PreparedStatement pstm = con.prepareStatement(
                READ_FROM_DB,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = pstm.executeQuery();

        checkItem(rs);
        rs.close();
        pstm.close();

        // Room
        CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Room ("
                + "Name VARCHAR(1024),"
                + "Description VARCHAR(10000),"
                + "Alias VARCHAR(1024),"
                + "Look VARCHAR(1024),"
                + "North VARCHAR(100),"
                + "East VARCHAR(100),"
                + "South VARCHAR(100),"
                + "West VARCHAR(100),"
                + "Items VARCHAR(1024),"
                + "PRIMARY KEY(Name))";

        stm = con.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();

        READ_FROM_DB = "SELECT Name FROM Room";

        pstm = con.prepareStatement(
                READ_FROM_DB,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = pstm.executeQuery();

        checkRoom(rs);
        rs.close();
        pstm.close();

        // Obbiettivi
        CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Objectives ("
                + "ID Integer,"
                + "Name VARCHAR(1024),"
                + "PRIMARY KEY (ID))";

        stm = con.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();

        READ_FROM_DB = "SELECT ID FROM Objectives";

        pstm = con.prepareStatement(
                READ_FROM_DB,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = pstm.executeQuery();

        checkObjective(rs);
        rs.close();
        pstm.close();

        // Pezzi di storia
        CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Story ("
                + "ID Integer,"
                + "Name VARCHAR(2048),"
                + "Id_Objectives VARCHAR(1024),"
                + "PRIMARY KEY (ID))";

        stm = con.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();

        READ_FROM_DB = "SELECT ID FROM Story";

        pstm = con.prepareStatement(
                READ_FROM_DB,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        rs = pstm.executeQuery();

        checkStory(rs);
        rs.close();
        pstm.close();
    }

    /**
     * Metodo che si occupa di verificare l'esistenza o meno dell'oggetto
     * all'interno del database.
     *
     * <p>
     * Metodo che si occupa di verificare se l'oggetto con un determinato nome
     * esiste o meno nel database: se l'oggetto esiste, passa al prossimo;
     * altrimenti chiama il metodo insertItems che si occupa di inserire tutte
     * le informazioni relative all'oggetto in questione, all'interno della
     * tabella corrispondente nel database.
     * </p>
     *
     * @param rs per permettere di spostare il cursore all'interno del database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws FileNotFoundException nel caso in cui il file dove sono
     * conservati i vari item del gioco sia inesistente.
     *
     * @see DB#insertItems(java.lang.String)
     */
    private void checkItem(ResultSet rs)
            throws SQLException, FileNotFoundException {

        List<String> itemsFromFileList = new ArrayList<>();
        boolean flag;
        int listSize;
        Scanner reader = new Scanner(
                new BufferedReader(
                        new FileReader(
                                new File(FILE_PATH + "items.txt"))));

        while (reader.hasNextLine()) {
            itemsFromFileList.add(reader.nextLine());

        }

        listSize = itemsFromFileList.size();

        if (rs.next()) {
            for (int i = 0; i < listSize; i++) {
                flag = false;
                rs.absolute(0);
                while (rs.next()) {
                    if (rs.getString(1)
                            .equals(itemsFromFileList.get(i)
                                    .substring(0,
                                            itemsFromFileList.get(i)
                                                    .indexOf("*")))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    insertItems(itemsFromFileList.get(i));
                }
            }
        } else {
            for (int i = 0; i < listSize; i++) {
                insertItems(itemsFromFileList.get(i));
            }
        }

        reader.close();
    }

    /**
     * Metodo che si occupa di inserire nel database le informazioni relative ad
     * un determinato oggetto.
     *
     * @param name il nome dell'oggetto da inserire nel database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    private void insertItems(String itemsFromFile) throws SQLException {
        String INSERT_INTO_TABLE = "INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(INSERT_INTO_TABLE);
        String[] itemStrings = itemsFromFile.split("\\*");

        // Svuota la lista dai campi non impostati
        for (int i = 0; i < itemStrings.length; i++) {
            if (itemStrings[i].equals("--")) {
                itemStrings[i] = "";
            }
        }

        pstm.setString(1, itemStrings[0]);  //name
        pstm.setString(2, itemStrings[1]);  //desc
        pstm.setString(3, itemStrings[2]);  //alias
        pstm.setBoolean(4, Boolean.parseBoolean(itemStrings[3])); // lookable
        pstm.setBoolean(5, Boolean.parseBoolean(itemStrings[4])); // pickupable
        pstm.setBoolean(6, Boolean.parseBoolean(itemStrings[5])); // usable
        pstm.setBoolean(7, Boolean.parseBoolean(itemStrings[6])); // combinable
        pstm.setString(8, itemStrings[7]); // File_name
        pstm.executeUpdate();
        pstm.close();
    }

    /**
     * Metodo che si occupa di verificare l'esistenza o meno della stanza
     * all'interno del database.
     *
     * <p>
     * Metodo che si occupa di verificare se la stanza con un determinato nome
     * esiste o meno nel database: se la stanza esiste, passa alla prossima;
     * altrimenti chiama il metodo insertRooms che si occupa di inserire tutte
     * le informazioni relative alla stanza in questione, all'interno della
     * tabella corrispondente nel database.
     * </p>
     *
     * @param rs per permettere di spostare il cursore all'interno del database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws FileNotFoundException nel caso in cui il file dove sono
     * conservate le varie stanze del gioco sia inesistente.
     *
     * @see DB#insertRooms(java.lang.String)
     */
    private void checkRoom(ResultSet rs)
            throws SQLException, FileNotFoundException {

        List<String> roomsFromFileList = new ArrayList<>();
        boolean flag;
        int listSize;
        Scanner reader = new Scanner(
                new BufferedReader(
                        new FileReader(
                                new File(FILE_PATH + "rooms.txt"))));

        while (reader.hasNextLine()) {
            roomsFromFileList.add(reader.nextLine());
        }

        listSize = roomsFromFileList.size();

        if (rs.next()) {
            for (int i = 0; i < listSize; i++) {
                flag = false;
                rs.absolute(0);
                while (rs.next()) {
                    if (rs.getString(1)
                            .equals(roomsFromFileList.get(i)
                                    .substring(0,
                                            roomsFromFileList.get(i)
                                                    .indexOf("*")))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    insertRooms(roomsFromFileList.get(i));
                }
            }
        } else {
            for (int i = 0; i < listSize; i++) {
                insertRooms(roomsFromFileList.get(i));
            }
        }
    }

    /**
     * Metodo che si occupa di inserire nel database le informazioni relative ad
     * una determinata stanza.
     *
     * @param name il nome della stanza da inserire nel database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    private void insertRooms(String roomsFromFile) throws SQLException {
        String INSERT_INTO_TABLE
                = "INSERT INTO Room VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = con.prepareStatement(INSERT_INTO_TABLE);
        String[] roomStrings = roomsFromFile.split("\\*");

        // Svuota la lista dai campi non impostati
        for (int i = 0; i < roomStrings.length; i++) {
            if (roomStrings[i].equals("--")) {
                roomStrings[i] = "";
            }
        }

        pstm.setString(1, roomStrings[0]); // nome
        pstm.setString(2, roomStrings[1]); // desc
        pstm.setString(3, roomStrings[2]); // alias
        pstm.setString(4, roomStrings[3]); // look
        pstm.setString(5, roomStrings[4]); // nord
        pstm.setString(6, roomStrings[5]); // est
        pstm.setString(7, roomStrings[6]); // sud
        pstm.setString(8, roomStrings[7]); // ovest
        pstm.setString(9, roomStrings[8]); // items
        pstm.executeUpdate();
        pstm.close();
    }

    /**
     * Metodo che si occupa di verificare l'esistenza o meno dell'obbiettivo
     * all'interno del database.
     *
     * <p>
     * Metodo che si occupa di verificare se l'obbiettivo con un determinato id
     * esiste o meno nel database: se l'obbiettivo esiste, passa al prossimo;
     * altrimenti chiama il metodo insertObjectives che si occupa di inserire le
     * informazioni relative all'obbiettivo in questione, all'interno della
     * tabella corrispondente nel database.
     * </p>
     *
     * @param rs per permettere di spostare il cursore all'interno del database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws FileNotFoundException nel caso in cui il file dove sono
     * conservati i vari obbiettivi del gioco sia inesistente.
     *
     * @see DB#insertObjectives(java.lang.String)
     */
    private void checkObjective(ResultSet rs)
            throws SQLException, FileNotFoundException {

        List<String> objectivesFromFileList = new ArrayList<>();
        boolean flag;
        int listSize;
        Scanner reader = new Scanner(
                new BufferedReader(
                        new FileReader(
                                new File(FILE_PATH + "objectives.txt"))));

        while (reader.hasNextLine()) {
            objectivesFromFileList.add(reader.nextLine());
        }

        listSize = objectivesFromFileList.size();

        if (rs.next()) {
            for (int i = 0; i < listSize; i++) {
                flag = false;
                rs.absolute(0);
                while (rs.next()) {
                    if (rs.getInt(1) == Integer
                            .parseInt(objectivesFromFileList.get(i)
                                    .substring(0,
                                            objectivesFromFileList.get(i)
                                                    .indexOf("*")))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    insertObjectives(objectivesFromFileList.get(i));
                }
            }
        } else {
            for (int i = 0; i < listSize; i++) {
                insertObjectives(objectivesFromFileList.get(i));
            }
        }
    }

    /**
     * Metodo che si occupa di inserire nel database le informazioni relative ad
     * una determinata stanza.
     *
     * @param id l'id dell'obbiettivo da inserire nel database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @since v 1.0
     */
    private void insertObjectives(String objectivesFromFile)
            throws SQLException {

        String INSERT_INTO_TABLE = "INSERT INTO Objectives VALUES (?,?)";
        PreparedStatement pstm = con.prepareStatement(INSERT_INTO_TABLE);
        String[] objectiveStrings = objectivesFromFile.split("\\*");

        // Svuota la lista dai campi non impostati
        for (int i = 0; i < objectiveStrings.length; i++) {
            if (objectiveStrings[i].equals("--")) {
                objectiveStrings[i] = "";
            }
        }

        pstm.setInt(1, Integer.parseInt(objectiveStrings[0])); // id
        pstm.setString(2, objectiveStrings[1]); // desc
        pstm.executeUpdate();
        pstm.close();
    }

    /**
     * Metodo che si occupa di verificare l'esistenza o meno del pezzo di storia
     * da raccontare all'interno del database.
     *
     * <p>
     * Metodo che si occupa di verificare se il pezzo di storia con un
     * determinato id esiste o meno nel database: se esiste, passa al prossimo;
     * altrimenti chiama il metodo insertStory che si occupa di inserire le
     * informazioni relative al pezzo di storia in questione, all'interno della
     * tabella corrispondente nel database.
     * </p>
     *
     * @param rs per permettere di spostare il cursore all'interno del database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     * @throws FileNotFoundException nel caso in cui il file dove sono
     * conservati i vari pezzi di storia sia inesistente.
     *
     * @see DB#insertStory(java.lang.String)
     */
    private void checkStory(ResultSet rs)
            throws SQLException, FileNotFoundException {

        List<String> storyFromFileList = new ArrayList<>();
        boolean flag;
        int listSize;
        Scanner reader = new Scanner(
                new BufferedReader(
                        new FileReader(
                                new File(FILE_PATH + "story.txt"))));

        while (reader.hasNextLine()) {
            storyFromFileList.add(reader.nextLine());
        }

        listSize = storyFromFileList.size();

        if (rs.next()) {
            for (int i = 0; i < listSize; i++) {
                flag = false;
                rs.absolute(0);
                while (rs.next()) {
                    if (rs.getInt(1) == Integer
                            .parseInt(storyFromFileList.get(i)
                                    .substring(0,
                                            storyFromFileList.get(i)
                                                    .indexOf("*")))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    insertStory(storyFromFileList.get(i));
                }
            }
        } else {
            for (int i = 0; i < listSize; i++) {
                insertStory(storyFromFileList.get(i));
            }
        }
    }

    /**
     * Metodo che si occupa di inserire nel database le informazioni relative ad
     * un determinato pezzo di storia.
     *
     * @param id l'id del pezzo di storia da inserire nel database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @since v 1.0
     */
    private void insertStory(String storyFromFile) throws SQLException {
        String INSERT_INTO_TABLE = "INSERT INTO Story VALUES (?,?,?)";
        PreparedStatement pstm = con.prepareStatement(INSERT_INTO_TABLE);
        String[] storyStrings = storyFromFile.split("\\*");

        // Svuota la lista dai campi non impostati
        for (int i = 0; i < storyStrings.length; i++) {
            if (storyStrings[i].equals("--")) {
                storyStrings[i] = "";
            }
        }

        pstm.setInt(1, Integer.parseInt(storyStrings[0])); // id
        pstm.setString(2, storyStrings[1]); // desc
        pstm.setString(3, storyStrings[2]); // id objective
        pstm.executeUpdate();
        pstm.close();
    }

    /**
     * Metodo che si occupa di leggere e restituire l'oggetto con il nome
     * inserito.
     *
     * @param query la query di lettura dal database.
     * @param param il nome dell'oggetto da leggere.
     *
     * @return la stringa che contiene le informazioni riguardanti l'oggetto
     * appena letto.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public String[] readItem(String query, String param) throws SQLException {
        String[] itemString = {"", "", "", "", "", "", "", ""};
        Boolean pickupableFromDB;
        Boolean usableFromDB;
        Boolean combinableFromDB;
        Boolean lookableFromDB;

        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, param);

        ResultSet read = pstm.executeQuery();

        while (read.next()) {
            lookableFromDB = read.getBoolean(4);
            pickupableFromDB = read.getBoolean(5);
            usableFromDB = read.getBoolean(6);
            combinableFromDB = read.getBoolean(7);

            itemString[0] = param;
            itemString[1] = read.getString(2);
            itemString[2] = read.getString(3);
            itemString[3] = lookableFromDB.toString();
            itemString[4] = pickupableFromDB.toString();
            itemString[5] = usableFromDB.toString();
            itemString[6] = combinableFromDB.toString();
            itemString[7] = read.getString(8);

        }
        read.close();
        pstm.close();

        return itemString;
    }

    /**
     * Metodo che si occupa di leggere e restituire la stanza con il nome
     * inserito.
     *
     * @param query la query di lettura dal database.
     * @param param il nome della stanza da leggere.
     *
     * @return la stringa che contiene le informazioni riguardanti la stanza
     * appena letta.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public String[] readRoom(String query, String param) throws SQLException {
        String[] roomString = {"", "", "", ""};

        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, param);

        ResultSet read = pstm.executeQuery();

        while (read.next()) {
            roomString[0] = read.getString(1);
            roomString[1] = read.getString(2);
            roomString[2] = read.getString(3);
            roomString[3] = read.getString("Items");

        }
        read.close();
        pstm.close();

        return roomString;
    }

    /**
     * Metodo che si occupa di leggere e restituire le quattro direzioni delle
     * stanze.
     *
     * @param query la query di lettura dal database.
     * @param param il nome della stanza da cui leggere le direzioni.
     *
     * @return la stringa che contiene le informazioni riguardanti le direzioni.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public String[] readDirections(String query, String param)
            throws SQLException {

        String[] directions = {"", "", "", ""};

        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, param);

        ResultSet read = pstm.executeQuery();

        while (read.next()) {
            directions[0] = read.getString(1);
            directions[1] = read.getString(2);
            directions[2] = read.getString(3);
            directions[3] = read.getString(4);

        }
        read.close();
        pstm.close();

        return directions;
    }

    /**
     * Metodo che si occupa di leggere e restituire l'obbiettivo.
     *
     * @param query la query di lettura dal database.
     * @param id l'id dell'obbiettivo da leggere.
     *
     * @return la stringa che contiene le informazioni riguardanti l'obbiettivo.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public String[] readObjectives(String query, int id) throws SQLException {
        String[] objectivesString = {"", ""};

        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, id);

        ResultSet read = pstm.executeQuery();

        while (read.next()) {
            objectivesString[0] = read.getString(1);
            objectivesString[1] = read.getString(2);

        }
        read.close();
        pstm.close();

        return objectivesString;
    }

    /**
     * Metodo che si occupa di leggere e restituire il pezzo di storia.
     *
     * @param query la query di lettura dal database.
     * @param id l'id del pezzo di storia da leggere.
     *
     * @return la stringa che contiene le informazioni riguardanti pezzo di
     * storia.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public String[] readStory(String query, int id) throws SQLException {
        String[] storyString = {"", "", ""};

        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, id);

        ResultSet read = pstm.executeQuery();

        while (read.next()) {
            storyString[0] = read.getString(1);
            storyString[1] = read.getString(2);
            storyString[2] = read.getString("Id_Objectives");
        }

        read.close();
        pstm.close();

        return storyString;
    }

    /**
     * Metodo che si occupa di chiudere la connessione con il database.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     */
    public void close() throws SQLException {
        con.close();
    }
}
