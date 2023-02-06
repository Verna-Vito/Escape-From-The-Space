/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.gameutil;

import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.Room;
import com.client.types.items.*;
import com.client.types.objectives.*;
import com.client.types.rooms.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Questa classe viene usata per l'inizializzazione dei vari oggetti, stanze e
 * obbiettivi del gioco.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public final class Creator {

    /**
     * Metodo che si occupa di inizializzare tutti gli item del gioco.
     *
     * @param db il database dal quale leggere gli item.
     * @param objectives la lista di tutti gli obbiettivi
     *
     * @return la lista di tutti gli item.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @see Item
     */
    public static List<Item> createItems(DB db, List<Objective> objectives)
            throws SQLException {
        
        List<Item> items = new LinkedList<>();

        SpaceShip spaceShip = new SpaceShip("Navicella", db, objectives);
        Battery battery = new Battery("Batterie", db);
        Badge badge = new Badge("Badge", db);
        Bandage bandage = new Bandage("Bende", db);
        WaterTank water = new WaterTank("Botte di acqua", db);
        Key key1 = new Key("Chiave 1", db);
        OxygenTank oxygen = new OxygenTank("Bombola di ossigeno", db);
        Pills pills = new Pills("Pillole", db);
        ProtectiveWear protectiveWear = 
                new ProtectiveWear("Tuta protettiva", db);
        
        GeigerCounter geigerCounter = 
                new GeigerCounter("Contatore di Geiger", db);
        
        Key key2 = new Key("Chiave 2", db);
        Display display = new Display("Schermo", db);
        Fuel fuel = new Fuel("Carburante", db);
        Computer computer = new Computer("Computer di Bordo", db);
        RoomMap dormsMap = new RoomMap("Mappa dormitori", db);
        RoomMap cmmMap = new RoomMap("Mappa zona comune", db);
        RoomMap goSciMap = new RoomMap("Mappa go sci", db);
        RoomMap hospMap = new RoomMap("Mappa ospedale", db);
        RoomMap kitchenMap = new RoomMap("Mappa cucina", db);
        ElectricalPanel electric = new ElectricalPanel("Quadro elettrico", db);

        items.add(battery);
        items.add(key1);
        items.add(key2);
        items.add(badge);
        items.add(bandage);
        items.add(water);
        items.add(oxygen);
        items.add(pills);
        items.add(protectiveWear);
        items.add(geigerCounter);
        items.add(display);
        items.add(fuel);
        items.add(computer);
        items.add(dormsMap);
        items.add(cmmMap);
        items.add(goSciMap);
        items.add(hospMap);
        items.add(kitchenMap);
        items.add(electric);
        items.add(spaceShip);

        return items;
    }

    /**
     * Metodo che si occupa di inizializzare tutti gli obbiettivi del gioco.
     *
     * @param db il database dal quale leggere gli obbiettivi.
     *
     * @return la lista di tutti gli obbiettivi.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @see Objective
     */
    public static List<Objective> createObjectives(DB db) throws SQLException {
        List<Objective> objectives = new LinkedList<>();

        LightObjective light = new LightObjective(1, db);
        UseComputer computer = new UseComputer(2, db);
        OxygenLevel oxygenLvl = new OxygenLevel(3, db);
        UseBandage bandage = new UseBandage(4, db);
        CheckSpaceShip check = new CheckSpaceShip(5, db);
        LookForFuel fuel = new LookForFuel(6, db);
        LookForKeys keys = new LookForKeys(7, db);
        LookForPills pills = new LookForPills(8, db);
        LookForWater water = new LookForWater(9, db);
        LookForCounter counter = new LookForCounter(10, db);
        LookForProtective protective = new LookForProtective(11, db);
        LookForDispBatt displayAndBattery = new LookForDispBatt(12, db);
        FixGeigerCounter fixCounter = new FixGeigerCounter(13, db);
        UseSpaceShip useSpaceShip = new UseSpaceShip(14, db);
        UseGeigerCounter useCounter = new UseGeigerCounter(15, db);

        objectives.add(light);
        objectives.add(computer);
        objectives.add(oxygenLvl);
        objectives.add(bandage);
        objectives.add(check);
        objectives.add(fuel);
        objectives.add(keys);
        objectives.add(pills);
        objectives.add(water);
        objectives.add(counter);
        objectives.add(protective);
        objectives.add(displayAndBattery);
        objectives.add(fixCounter);
        objectives.add(useSpaceShip);
        objectives.add(useCounter);

        return objectives;
    }

    /**
     * Metodo che si occupa di inizializzare tutte le stanze del gioco.
     *
     * @param db il database dal quale leggere le stanze.
     * @param items la lista degli oggetti in modo da poter assegnare alle
     * stanze i propri oggetti.
     *
     * @return la lista di tutte le stanze.
     *
     * @throws SQLException per eventuali problemi con il database o l'SQL.
     *
     * @see Room
     */
    public static List<Room> createRooms(DB db, List<Item> items) 
            throws SQLException {
        List<Room> roomList = new LinkedList<>();

        Dorms dorms = 
                new Dorms("Dormitori", db, items);
        
        CommonZone commonZone =
                new CommonZone("Zona Comune", db, items);
        
        Kitchen kitchen = 
                new Kitchen("Cucina e sala mensa", db, items);
        
        Maintenance maintenance = 
                new Maintenance("Sala Manutenzione e Elettrica", db, items);
        
        GoSciSector goSciSector = 
                new GoSciSector("Settore GO SCI", db, items);
        
        Hospital hospital = 
                new Hospital("Zona Medica e Ospedale", db, items);
        
        Monitoring monitoring = 
                new Monitoring("Sala mon. Terra e Zona di lancio", db, items);
        
        GeneralDeposit generalDep = 
                new GeneralDeposit("Deposito Generale", db, items);
        
        Earth earth = new Earth("Pianeta Terra", db, items);

        roomList.add(dorms);
        roomList.add(commonZone);
        roomList.add(kitchen);
        roomList.add(maintenance);
        roomList.add(goSciSector);
        roomList.add(hospital);
        roomList.add(monitoring);
        roomList.add(generalDep);
        roomList.add(earth);

        return roomList;
    }
}
