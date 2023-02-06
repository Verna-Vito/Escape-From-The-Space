/*
 * Copyright 2022 
 * Autori: Verna Vito e Alessandro Mazzotta
 */
package com.client;

import com.client.command.Command;
import com.client.form.ErrorFrame;
import com.client.form.HomeFrame;
import com.client.form.gameframe.GameFrame;
import com.client.gameutil.AudioPlayer;
import com.client.gameutil.DB;
import com.client.gameutil.GameUtil;

/**
 * Classe client del gioco.
 * 
 * @author Verna Vito - 746463
 * @author Mazzotta Alessandro - 766414
 *
 * @see GameUtil
 */
@SuppressWarnings("")
public class App extends GameUtil {

    public static void main(String[] args) {
        App app = new App();

        AudioPlayer audio;

        GameFrame game;
        HomeFrame home = null;

        DB db;

        Command cmd;

        boolean play = false;
        boolean load = false;

        try {
            audio = AudioPlayer.initAudioPlayer();
            game = new GameFrame(audio);
            home = new HomeFrame(game, audio);
            cmd = new Command(audio, game);

            home.setVisible(true);
            app.initConnectionWithServer();

            do {
                Thread.sleep(1);
                if (home.isPlayPressed()) {
                    play = true;
                } else if (home.isLoadPressed()) {
                    load = true;
                }
            } while (!home.isPlayPressed() && !home.isLoadPressed());

            db = new DB();
            db.init();

            if (play) {
                app.init(db);

                app.playGame(game, audio, cmd, db);

                cmd.save(app.roomListCleaner(app.getRoomList()), app.getObjectiveList(), app.isGameStarted(), game, app.getStory());
                cmd.quit(app.getSocket(), db);
            } else if (load) {
                app.loadGame(cmd, game);

                app.playGame(game, audio, cmd, db);

                cmd.save(app.roomListCleaner(app.getRoomList()), app.getObjectiveList(), app.isGameStarted(), game, app.getStory());
                cmd.quit(app.getSocket(), db);
            }

        } catch (Exception ex) {
            home.setVisible(false);
            ErrorFrame errorFrame = new ErrorFrame(ex);
            errorFrame.setVisible(true);
        }
    }
}
