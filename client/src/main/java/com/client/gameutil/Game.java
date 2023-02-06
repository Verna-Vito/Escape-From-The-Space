/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.gameutil;

import com.client.types.Item;
import com.client.types.Objective;
import com.client.types.Room;
import com.client.types.Story;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Classe wrapper che definisce il comportamento di metodi che vengono
 * utilizzati dalla classe <code>GameUtil</code>.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see GameUtil
 */
public abstract class Game {

    /**
     * Attributo di tipo Room che permette la memorizzazione della stanza
     * attuale.
     */
    private Room actualRoom;

    /**
     * Attributo di tipo Socket che permette la connessione con il server
     * remoto.
     */
    private Socket socket;

    /**
     * Attributo di tipo BufferedReader che consente di ricevere una risposta
     * dal server.
     */
    private BufferedReader inputFromServer;

    /**
     * Attributo di tipo PrintWriter che consente di inviare stringhe/frasi al
     * server in modo che le elabori.
     */
    private PrintWriter outputToServer;

    /**
     * Attributo che indica se il gioco è stato startato o meno.
     */
    private boolean gameStarted = false;

    /**
     * Attributo che memorizza il capitolo del pezzo di storia corrente e
     * permette il passaggio al prossimo capitolo.
     */
    private int storyChapter;

    /**
     * Attributo che memorizza il pezzo di storia corrente.
     */
    private Story story;
    
    /**
     * Attributo che memorizza la lista di tutti gli item del gioco.
     */
    private List<Item> itemList;
    
    /**
     * Attributo che memorizza la lista di tutte le stanze del gioco.
     */
    private List<Room> roomList;
    
    /**
     * Attributo che memorizza la lista di tutti gli obbiettivi del gioco.
     */
    private List<Objective> objectiveList;

    /**
     * Metodo che restituisce <code>true</code> se il gioco è stato iniziato;
     * <code>false</code> altrimenti.
     *
     * @return il valore dell'attributo gameStarted.
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Metodo che imposta a <code>true</code> se il gioco è stato iniziato;
     * <code>false</code> altrimenti.
     *
     * @param gameStarted il valore dell'attributo gameStarted.
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Metodo che si occupa di restituire il capitolo del pezzo di storia.
     *
     * @return il capitolo del pezzo di storia
     */
    public int getStoryChapter() {
        return storyChapter;
    }

    /**
     * Metodo che imposta il capitolo del pezzo di storia.
     *
     * @param storyChapter il capitolo del pezzo di storia da impostare
     */
    public void setStoryChapter(int storyChapter) {
        this.storyChapter = storyChapter;
    }

    /**
     * Metodo che si occupa di restituire l'intera lista degli item di gioco.
     *
     * @return la lista di tutti gli item
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Metodo che imposta la lista degli item di gioco.
     *
     * @param itemList la lista di tutti gli item
     */
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * Metodo che si occupa di restituire l'intera lista delle stanze di gioco.
     *
     * @return la lista di tutte le stanze
     */
    public List<Room> getRoomList() {
        return roomList;
    }

    /**
     * Metodo che imposta la lista delle stanze di gioco.
     *
     * @param roomList la lista di tutti gli item
     */
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    /**
     * Metodo che si occupa di restituire l'intera lista degli obbiettivi di
     * gioco.
     *
     * @return la lista di tutti gli obbiettivi
     */
    public List<Objective> getObjectiveList() {
        return objectiveList;
    }

    /**
     * Metodo che imposta la lista degli obbiettivi di gioco.
     *
     * @param objectiveList la lista di tutti gli item
     */
    public void setObjectiveList(List<Objective> objectiveList) {
        this.objectiveList = objectiveList;
    }

    /**
     * Metodo che si occupa di restituire la stanza in cui si è in quel momento.
     *
     * @return la stanza attuale
     */
    public Room getActualRoom() {
        return actualRoom;
    }

    /**
     * Metodo che imposta la stanza in cui si è in quel momento.
     *
     * @param actualRoom la stanza attuale
     */
    public void setActualRoom(Room actualRoom) {
        this.actualRoom = actualRoom;
    }

    /**
     * Metodo che si occupa di restituire il socket di connessione con il
     * Server.
     *
     * @return il socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Metodo che imposta il socket di connessione con il Server.
     *
     * <p>
     * <b>Questo metodo viene chiamato solo in fase di connessione con il
     * server</b>.</p>
     *
     * @param socket il socket
     *
     * @see GameUtil#initConnectionWithServer()
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Metodo che si occupa di restituire il pezzo di storia.
     *
     * @return il pezzo di storia
     */
    public Story getStory() {
        return story;
    }

    /**
     * Metodo che imposta il pezzo di storia.
     *
     * @param story il pezzo di storia
     */
    public void setStory(Story story) {
        this.story = story;
    }

    /**
     * Metodo che si occupa di restituire il BufferedReader che contiene la
     * risposta mandata dal serever.
     *
     * @return la risposta mandata dal server
     */
    public BufferedReader getInputFromServer() {
        return inputFromServer;
    }

    /**
     * Metodo che imposta il BufferedReader che contiene la risposta mandata dal
     * serever.
     *
     * <p>
     * <b>Questo metodo viene chiamato solo in fase di connessione con il
     * server</b>.</p>
     *
     * @param inputFromServer la risposta mandata dal server
     *
     * @see GameUtil#initConnectionWithServer()
     */
    public void setInputFromServer(BufferedReader inputFromServer) {
        this.inputFromServer = inputFromServer;
    }

    /**
     * Metodo che si occupa di restituire il PrintWriter che contiene la stringa
     * inserita dall'utente da mandare al server.
     *
     * @return stringa inserita dall'utente da mandare al server
     */
    public PrintWriter getOutputToServer() {
        return outputToServer;
    }

    /**
     * Metodo che imposta il PrintWriter che contiene la stringa inserita
     * dall'utente da mandare al server.
     *
     * <p>
     * <b>Questo metodo viene chiamato solo in fase di connessione con il
     * server</b>.</p>
     *
     * @param outputToServer stringa inserita dall'utente da mandare al server
     *
     * @see GameUtil#initConnectionWithServer()
     */
    public void setOutputToServer(PrintWriter outputToServer) {
        this.outputToServer = outputToServer;
    }
}
