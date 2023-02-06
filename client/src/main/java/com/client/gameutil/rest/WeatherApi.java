/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client.gameutil.rest;

import com.google.gson.Gson;
import java.io.Serializable;
import java.net.ConnectException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Classe che si occupa dell'implementazione dei servizi REST nel programma.
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 */
public class WeatherApi implements Serializable {

    /**
     * Stringa che indica la chiave per il servizio REST di
     * "https://api.openweathermap.org".
     */
    private static final String KEY = "6ce565f78672c020a9b47b5b7d3af77c";

    /**
     * Attributo String che indica l'indirizzo ip del giocatore.
     */
    private String ip;

    /**
     * Attributo String che indica la latitudine della posizione del giocatore.
     */
    private String lat;

    /**
     * Attributo String che indica la longitudine della posizione del giocatore.
     */
    private String lon;

    /**
     * Attributo String che indica la descrizione del meteo.
     */
    private String desc;

    /**
     * Attributo int che indica il numero di tentativi di connessione al server
     * REST fatti.
     */
    private int counter = 0;

    /**
     * Costante int che indica il numero massimo di tentativi di connessione al
     * server REST.
     */
    private static final int MAX_TRY = 3;

    /**
     * Inner class protetta per la conversione del JSON restituito da
     * "https://ipinfo.io".
     */
    protected class IPInfo {

        /**
         * Attributo String che indica la posizione IRL del giocatore.
         */
        private String loc;

        /**
         * Metodo pubblico che restituisce il valore dell'attributo loc.
         *
         * @return valore dell'attributo loc.
         */
        public String getLoc() {
            return this.loc;
        }
    }

    /**
     * Inner class protetta per la conversione del JSON restituito da
     * "https://api.openweathermap.org".
     */
    protected class WeatherDesc {

        /**
         * Array di oggetti di tipo Weather.
         */
        private Weather[] weather;

        /**
         * Inner class protetta per il prelievo dei valori negli oggetti di tipo
         * weather ricavati dal JSON di "https://api.openweathermap.org".
         */
        private class Weather {

            /**
             * Attributo privato di tipo String che indica la descrizione del
             * meteo.
             */
            private String description;

            /**
             * Metodo pubblico che restituisce il valore dell'attributo
             * description.
             *
             * @return valore dell'attributo description.
             */
            public String getDescription() {
                return this.description;
            }
        }

        /**
         * Metodo pubblico che restituisce il valore dell'attributo description
         * dell'oggetto weather nell'attributo weather.
         *
         * @return valore dell'attributo description dell'oggetto weather
         * dell'attributo weather.
         */
        public String getWeatherDesc() {
            return this.weather[0].getDescription();
        }
    }

    /**
     * Metodo pubblico che restituisce la stringa contenuta dall'attributo desc.
     *
     * @return valore dell'attributo desc.
     */
    public String getDesc() {
        System.out.println(this.desc);
        return this.desc;
    }

    /**
     * Metodo privato che si occupa della richiesta al serivizo REST di
     * "https://api64.ipify.org". Servizio che restituisce l'ip dell'utente.
     *
     * @return stringa risultato della richiesta.
     *
     * @throws ConnectException nel caso di problemi di connessioni con i
     * servizi REST.
     */
    private String getIP() throws ConnectException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api64.ipify.org");
        Response resp = target.request(MediaType.TEXT_PLAIN).get();

        return resp.readEntity(String.class);
    }

    /**
     * Metodo che si occupa della richiesta al servizio REST
     * "https://ipinfo.io". Servizio che restituisce diverse informazioni
     * sull'ip dell'utente.
     *
     * @param ip da cui ricercare le informazioni.
     *
     * @return stringa risultato della richiesta.
     *
     * @throws ConnectException nel caso di problemi di connessioni con i
     * servizi REST.
     */
    private String getIPinfo(String ip) throws ConnectException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://ipinfo.io");
        Response resp = target.path(ip)
                .request(MediaType.APPLICATION_JSON).get();

        return resp.readEntity(String.class);
    }

    /**
     * Metodo che si occupa della richiesta al servizio REST
     * "https://api.openweathermap.org". Servizio che restituisce diverse
     * informazioni riguardanti il meteo in una certa posizione.
     *
     * @param lat la latitudine di dove si trova l'utente
     * @param lon la longitudine di dove si trova l'utente
     *
     * @return stringa risultato della richiesta.
     *
     * @throws ConnectException nel caso di problemi di connessioni con i
     * servizi REST.
     */
    private String getWeather(String lat, String lon) throws ConnectException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://api.openweathermap.org/data/2.5");

        Response resp = target.path("weather")
                .queryParam("lat", Double.parseDouble(lat))
                .queryParam("lon", Double.parseDouble(lon))
                .queryParam("appid", KEY)
                .queryParam("lang", "it")
                .request(MediaType.APPLICATION_JSON).get();

        return resp.readEntity(String.class);
    }

    /**
     * Metodo privato che si occupa della conversione dei JSON risultato dei
     * servizi in oggetti JAVA, così da ricavare solo le informazioni utili.
     *
     * @throws ConnectException nel caso di problemi di connessioni con i
     * servizi REST.
     */
    private void setWeatherInfo() throws ConnectException {
        this.ip = this.getIP();

        Gson jsonLoc = new Gson();
        IPInfo ipinfo = jsonLoc.fromJson(getIPinfo(ip), IPInfo.class);

        String pos[] = ipinfo.getLoc().split(",");
        this.lat = pos[0];
        this.lon = pos[1];

        Gson jsonWeather = new Gson();
        WeatherDesc wDesc = jsonWeather.fromJson(
                getWeather(lat, lon), WeatherDesc.class);

        this.desc = wDesc.getWeatherDesc();

    }

    /**
     * Metodo che si occupa di restituire la descrizione delle condizioni meteo
     * in quel momento.
     *
     * @return la descrizione del meteo in quel momento
     */
    public String getWeatherDesc() {
        if (counter < MAX_TRY) {
            try {
                counter++;
                this.setWeatherInfo();
            } catch (ConnectException | ProcessingException e) {
                this.getWeatherDesc();
            }
        } else {
            this.desc = "cielo sereno";
        }

        return "Il meteo di oggi prevede " + this.desc;
    }
}
