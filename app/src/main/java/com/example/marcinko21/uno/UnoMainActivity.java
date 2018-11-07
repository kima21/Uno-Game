package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GameConfig;
import com.example.marcinko21.uno.game.GameMainActivity;
import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.config.GamePlayerType;
import com.example.marcinko21.uno.game.LocalGame;

import java.util.ArrayList;

public class UnoMainActivity extends GameMainActivity {

    public static final int PORT_NUMBER = 5213;

    /**
     * an uno game for two players. The default is human vs. computer
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Uno has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new UnoHumanPlayer(name, 0);
            }});


        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new UnoComputerPlayer(name);
            }
        });


        // Add the default players
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Pig", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player


        return defaultConfig;

    }//createDefaultConfig


    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet,
     *
     * @return a new, game-specific instance of a sub-class of the LocalGame
     *         class.
     */
    @Override
    public LocalGame createLocalGame() {
        return new LocalGame();
    }
    //todo make a uno local game class and have it extend local game class. Then make this method -
    //- return the new uno local game state

}
