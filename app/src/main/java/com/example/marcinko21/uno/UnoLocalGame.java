package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.LocalGame;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

/**
 * UnoLocalGame Class for Uno Game
 * Returns the new Local Game State
 * Checks if game is Over
 */
public class UnoLocalGame extends LocalGame {

    //the game's state
    protected UnoState state;

    /**
     * Constructor for UnoLocalGame
     */
    public UnoLocalGame() {

        //perform superclass initialization
        super();

        //create a new, unfilled in UnoState object
        state = new UnoState();

    }//ctor


    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //if a player does not have any card left, the
        //game is over

        //if(player 1 hand == 0)
        //return return playerNames[gameWinner]+" is the winner.";
        //else
        //game is not over

        //if(player 2 hand == 0)
        //return playerNames[gameWinner]+" is the winner.";
        //else
        //game is not over
        return null;
    }

    /**
     * sendUpdatedStateTo
     * <p>
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player.
     *
     * @param p
     */
    protected void sendUpdatedStateTo(GamePlayer p) {

        //make a copy of the state, and send it to the player
        p.sendInfo(new UnoState(state));

    }//sendUpdatedStateTo


    @Override
    protected boolean canMove(int playerIdx) {

        //todo implement this method
        return false;

    }//canMove

    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param action The move that the player has sent to the game
     * @return Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {

        //todo for PlayCard action, actually do the action

        GamePlayer p = action.getPlayer();
        int playerNum = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i] == p) {
                playerNum = i;
            }
        }

        //for every action, check that it's my turn
        //true for except for challenges
        if (state.getTurn() != playerNum) {
            return false;
        }

        if (action instanceof UnoDrawAction) {

            state.drawCard(state.getHand(playerNum));
            return true;

        } else if (action instanceof UnoUnoAction) {

            state.setPlayerDeclaredUno();
            return true;

        }

        return false;

    }
}

