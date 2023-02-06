/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

/**
 * Classe server dell'applicazione.
 *
 * <p>
 * Questa classe crea un'istanza della classe <b>Parser</b> per permettere al
 * parser di analizzare la stringa ricevuta in input dal <b>Client</b>;
 * un'istanza della classe <b>ServerSocket</b> che consente di eseguire questa
 * classe su una porta del computer in modo che possa essere usata come server;
 * un'istanza delle classi <b>BufferedReader</b> e <b>PrintWriter</b> per
 * permettere la comunicazione client-server.</p>
 *
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see Parser
 * @see ServerSocket
 * @see BufferedReader
 * @see PrintWriter
 */
public class Server {

    public static void main(String[] args) throws IOException {
        List<String> response;
        String str;

        Parser parser = new Parser();
        ServerSocket s = new ServerSocket(6666);
        System.out.println("Server avviato");

        try {
            Socket socket = s.accept();

            try {
                System.out.println("Connessione accettata");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                do {
                    str = in.readLine();
                    response = parser.parse(str);

                    str = "";

                    for (int i = 0; i < response.size(); i++) {
                        str = str + response.get(i) + " ";
                    }

                    System.out.println(response); //sys di debug

                    out.println(str);
                } while (!response.get(0).equals(IDsCommand.QUIT.getId().toString()));

            } catch (SocketException ex) {
                System.err.println("Connessione chiusa dal client");
            } finally {
                System.out.println("Chiudo...");
                socket.close();
            }
        } finally {
            s.close();
        }
    }
}
