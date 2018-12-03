package com.example.marcinko21.uno;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.marcinko21.uno.game.GameHumanPlayer;
import com.example.marcinko21.uno.game.GameMainActivity;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;
import com.example.marcinko21.uno.game.infoMsg.IllegalMoveInfo;
import com.example.marcinko21.uno.game.infoMsg.NotYourTurnInfo;

/**
 * UnoHumanPlayer Class for Uno Game
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 */
public class UnoHumanPlayer extends GameHumanPlayer implements View.OnClickListener
{

    /**
     * Initialize Variables
     */
    private Activity myActivity; //the current activity
    private UnoSurfaceView surfaceView; //the surfaceview
    private int layoutId; //the ID for the layout to use
    private Button drawButton;
    private Button unoButton;

    /**
     * Constructor for UnoHumanPlayer Class
     *
     * @param name
     * 		the player's name
     * @param layoutId
     *      the id of the layout to use
     */
    public UnoHumanPlayer(String name, int layoutId)
    {

        super(name);
        this.layoutId = layoutId;



    }//ctor

    protected void initAfterReady()
    {
        drawButton = (Button) myActivity.findViewById(R.id.drawButton);
        drawButton.setOnClickListener(this);
        unoButton = (Button) myActivity.findViewById(R.id.unoButton);
        unoButton.setOnClickListener(this);

        surfaceView.setGame(game);

        if(game == null)
        {
            Log.i("setAsGUI", "GAME IS NULL");
        }
    }

    /**
     * getTopView
     *
     * @return null
     */
    @Override
    public View getTopView()
    {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * ReceiveInfo Method
     * Callback method, called when player gets a message
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info)
    {

        if (surfaceView == null)
        {
            return;
        }

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo)
        {
            // if the move was out of turn or otherwise illegal, flash the screen
            //surfaceView.flash(Color.RED, 50);
        }
        else if (!(info instanceof UnoState))
        {
            // if we do not have a UnoState, ignore
            return;
        }
        else
        {
            surfaceView.setState((UnoState)info);
            surfaceView.invalidate();
            Log.i("human player", "receiving");
        }
    }//receiveInfo

    /**
     * sets the current player as the activity's GUI
     */
    public void setAsGui(GameMainActivity activity)
    {

        // remember our activity
        myActivity = activity;

        // Load the layout resource for the new configuration
        activity.setContentView(layoutId);

        // set the surfaceView instance variable
        surfaceView = (UnoSurfaceView)myActivity.findViewById(R.id.unoSurfaceView);
        Log.i("set listener","OnTouch");
        surfaceView.setOnTouchListener(surfaceView);

        surfaceView.setHumanPlayer(this);

    }//setAsGui




    /**
     * this method gets called when the user clicks the uno or draw button. It
     * creates a new UnoUnoAction or UnoDrawAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {


        if(button.getId() == R.id.unoButton)
        {

            UnoUnoAction declareUno = new UnoUnoAction(this);
            game.sendAction(declareUno);

        }
        else if (button.getId() == R.id.drawButton)
        {

            UnoDrawAction draw = new UnoDrawAction(this);
            game.sendAction(draw);

        }


    }// onClick

}
