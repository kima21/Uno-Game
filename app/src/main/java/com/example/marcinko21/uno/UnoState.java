package com.example.marcinko21.uno;

import android.graphics.Color;
import android.util.Log;

import com.example.marcinko21.uno.game.infoMsg.GameState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Contains the state of a Uno game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 */
public class UnoState extends GameState
{

    /**
     * Initialize Variables
     */
    int player1Id;
    int player2Id;
    int turn;
    int color;
    int deckSize;
    boolean playerDeclaredUno;
    ArrayList<Card> deck = new ArrayList<Card>(108);
    ArrayList<Card> hand1 = new ArrayList<Card>(7);
    ArrayList<Card> hand2 = new ArrayList<Card>(7);
    ArrayList<Card> discardPile = new ArrayList<Card>(0);
    Random r = new Random();

    /**
     * Constructor for objects of class UnoState
     */
    public UnoState() {
        player1Id = 0;
        player2Id = 1;
        turn = 0;
        makeDeck();
        shuffleDeck();
        playerDeclaredUno = false;
        int i;
        for(i=0; i < 7; i++){
            drawCard(hand1);
            drawCard(hand2);
        }
        discardPile.add(0,deck.get(0));
        deck.remove(deck.get(0));
        color = discardPile.get(discardPile.size()-1).color;
        Log.i("Game state constructor","hand1 size is: " + hand1.size());
    }//ctor

    /**
     * Copy constructor for class UnoState
     *
     * @param state
     * 		the UnoState object that we want to clone
     */
    public UnoState (UnoState state) {

        player1Id = state.getPlayer1Id();
        player2Id = state.getPlayer2Id();
        turn = state.getTurn();
        playerDeclaredUno = state.playerDeclaredUno;

        int i = 0;

        Log.i("Game state copy constructor","deck size is: " + state.deck.size());
        deck = new ArrayList<>();
        for(Card c : state.deck){
            //deck.set(i, c);
            deck.add(c.clone());
            i++;
        }
        Log.i("Game state copy constructor","deck size is: " + deck.size());
        i = 0;
        discardPile = new ArrayList<>();
        for(Card c : state.discardPile)
        {
            //discardPile.set(i, c);
            discardPile.add(c.clone());
            i++;
        }

        Log.i("Game state copy constructor","hand1 size is: " + state.hand1.size());
        hand1 = new ArrayList<Card>();
        // this for loop creates a clone of every value in arraylist hand1
        for (Card c : state.hand1) {
            hand1.add(c.clone());
        }
        Log.i("Game state copy constructor","hand1 size is: " + hand1.size());
        hand2 = new ArrayList<Card>();
        // this for loop creates a clone of every value in arraylist hand2
        for(Card c : state.hand2) {
            hand2.add(c.clone());
        }
        color = state.color;
    }//copyCtor

    /**
     * Method to make the deck for a new game
     */
    public void makeDeck() {
       /* int i, n, k, j;



        //number cards with value 0
        for (i = 0; i < 4; i++) {
            Card c = new Card(i, 0, 'n',"card"+i);
            deck.add(c);
        }

        //number cards with values 1-9
        while (i < 76) {
            for (n = 0; n < 2; n++) {
                for (k = 0; k < 10; k++) {
                    for (j = 0; j < 4; j++) {
                        Card c = new Card(j, k, 'n',"card"+i);
                        deck.add(c);
                        i++;
                    }
                }
            }

        }

        //skip cards
        while (i < 84) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    Card c = new Card(j, 0, 's',"card"+i);
                    deck.add(c);
                    i++;
                }
            }
        }

        //draw 2 cards
        while (i < 92) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    Card c = new Card(j, 0, 'd',"card"+i);
                    deck.add(c);
                    i++;
                }
            }
        }

        //reverse cards
        while (i < 100) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    Card c = new Card(j, 0, 'r',"card"+i);
                    deck.add(c);
                    i++;
                }
            }
        }

        //wild cards
        while (i < 104) {
            for (j = 0; j < 4; j++) {
                Card c = new Card(4, 0, 'w',"card"+i);
                deck.add(c);
                i++;
            }
        }

        //wild draw 4 cards
        while (i < 108) {
            for (j = 0; j < 4; j++) {
                Card c = new Card(4, 0, 'd',"card"+i);
                deck.add(c);
                i++;
            }
        }

*/
        //todo temporarily comment out above code and add just green cards 0-9 manually
        deck.add(new Card(0,0,'g','g',R.drawable.green0));//1
        deck.add(new Card(0,1,'g','g',R.drawable.green1));//2
        deck.add(new Card(0,0,'g','g',R.drawable.green1_copy));//3
        deck.add(new Card(0,1,'g','g',R.drawable.green2));//4
        deck.add(new Card(0,1,'g','g',R.drawable.green2_copy));//5
        deck.add(new Card(0,1,'g','g',R.drawable.green3));//6
        deck.add(new Card(0,1,'g','g',R.drawable.green3_copy));//7
        deck.add(new Card(0,1,'g','g',R.drawable.green4));//8
        deck.add(new Card(0,1,'g','g',R.drawable.green4_copy));//9
        deck.add(new Card(0,1,'g','g',R.drawable.green5));//10
        deck.add(new Card(0,1,'g','g',R.drawable.green5_copy));//11
        deck.add(new Card(0,1,'g','g',R.drawable.green6));//12
        deck.add(new Card(0,1,'g','g',R.drawable.green6_copy));//13
        deck.add(new Card(0,1,'g','g',R.drawable.green7));//14
        deck.add(new Card(0,1,'g','g',R.drawable.green7_copy));//15
        deck.add(new Card(0,1,'g','g',R.drawable.green8));//16
        deck.add(new Card(0,1,'g','g',R.drawable.green8_copy));//17
        deck.add(new Card(0,1,'g','g',R.drawable.green9));//18
        deck.add(new Card(0,1,'g','g',R.drawable.green9_copy));//19
        deck.add(new Card(0,1,'g','g',R.drawable.green_draw2));//20
        deck.add(new Card(0,0,'g','g',R.drawable.green_draw2_copy));//21
        deck.add(new Card(0,1,'g','g',R.drawable.green_wild));//22
        deck.add(new Card(0,1,'g','g',R.drawable.green_reverse));//23
        deck.add(new Card(0,1,'g','g',R.drawable.green_reverse_copy));//24
        deck.add(new Card(0,1,'g','g',R.drawable.green_draw4));//25
        deck.add(new Card(0,1,'g','g',R.drawable.green_skip));//26
        deck.add(new Card(0,1,'g','g',R.drawable.green_skip_copy));//27
        deck.add(new Card(0,1,'g','g',R.drawable.blue0));//28
        deck.add(new Card(0,1,'g','g',R.drawable.blue1));//29
        deck.add(new Card(0,1,'g','g',R.drawable.blue1_copy));//30
        deck.add(new Card(0,1,'g','g',R.drawable.blue2));//31
        deck.add(new Card(0,1,'g','g',R.drawable.blue2_copy));//32
        deck.add(new Card(0,1,'g','g',R.drawable.blue3));//33
        deck.add(new Card(0,1,'g','g',R.drawable.blue3_copy));//34
        deck.add(new Card(0,1,'g','g',R.drawable.blue4));//35
        deck.add(new Card(0,1,'g','g',R.drawable.blue4_copy));//36
        deck.add(new Card(0,1,'g','g',R.drawable.blue5));//37
        deck.add(new Card(0,1,'g','g',R.drawable.blue5_copy));//38
        deck.add(new Card(0,1,'g','g',R.drawable.blue6));//39
        deck.add(new Card(0,1,'g','g',R.drawable.blue6_copy));//40
        deck.add(new Card(0,1,'g','g',R.drawable.blue7));//41
        deck.add(new Card(0,1,'g','g',R.drawable.blue7_copy));//42
        deck.add(new Card(0,1,'g','g',R.drawable.blue8));//43
        deck.add(new Card(0,1,'g','g',R.drawable.blue8_copy));//44
        deck.add(new Card(0,1,'g','g',R.drawable.blue9));//45
        deck.add(new Card(0,1,'g','g',R.drawable.blue9_copy));//46
        deck.add(new Card(0,1,'g','g',R.drawable.blue_draw2));//47
        deck.add(new Card(0,1,'g','g',R.drawable.blue_draw2_copy));//48
        deck.add(new Card(0,1,'g','g',R.drawable.blue_draw4));//49
        deck.add(new Card(0,1,'g','g',R.drawable.blue_reverse));//50
        deck.add(new Card(0,1,'g','g',R.drawable.blue_reverse_copy));//51
        deck.add(new Card(0,1,'g','g',R.drawable.blue_skip));//52
        deck.add(new Card(0,1,'g','g',R.drawable.blue_skip_copy));//53
        deck.add(new Card(0,1,'g','g',R.drawable.blue_wild));//54
        deck.add(new Card(0,1,'g','g',R.drawable.red0));//55
        deck.add(new Card(0,1,'g','g',R.drawable.red1));//56
        deck.add(new Card(0,1,'g','g',R.drawable.red1_copy));//57
        deck.add(new Card(0,1,'g','g',R.drawable.red2));//58
        deck.add(new Card(0,1,'g','g',R.drawable.red2_copy));//59
        deck.add(new Card(0,1,'g','g',R.drawable.red3));//60
        deck.add(new Card(0,1,'g','g',R.drawable.red3_copy));//61
        deck.add(new Card(0,1,'g','g',R.drawable.red4));//62
        deck.add(new Card(0,1,'g','g',R.drawable.red4_copy));//63
        deck.add(new Card(0,1,'g','g',R.drawable.red5));//64
        deck.add(new Card(0,1,'g','g',R.drawable.red5_copy));//65
        deck.add(new Card(0,1,'g','g',R.drawable.red6));//66
        deck.add(new Card(0,1,'g','g',R.drawable.red6_copy));//67
        deck.add(new Card(0,1,'g','g',R.drawable.red7));//68
        deck.add(new Card(0,1,'g','g',R.drawable.red7_copy));//69
        deck.add(new Card(0,1,'g','g',R.drawable.red8));//70
        deck.add(new Card(0,1,'g','g',R.drawable.red8_copy));//71
        deck.add(new Card(0,1,'g','g',R.drawable.red9));//72
        deck.add(new Card(0,1,'g','g',R.drawable.red9_copy));//73
        deck.add(new Card(0,1,'g','g',R.drawable.red_draw2));//74
        deck.add(new Card(0,1,'g','g',R.drawable.red_draw2_copy));//75
        deck.add(new Card(0,1,'g','g',R.drawable.red_draw4));//76
        deck.add(new Card(0,1,'g','g',R.drawable.red_reverse));//77
        deck.add(new Card(0,1,'g','g',R.drawable.red_reverse_copy));//78
        deck.add(new Card(0,1,'g','g',R.drawable.red_skip));//79
        deck.add(new Card(0,1,'g','g',R.drawable.red_skip_copy));//80
        deck.add(new Card(0,1,'g','g',R.drawable.red_wild));//81
        deck.add(new Card(0,1,'g','g',R.drawable.yellow0));//82
        deck.add(new Card(0,1,'g','g',R.drawable.yellow1));//83
        deck.add(new Card(0,1,'g','g',R.drawable.yellow1_copy));//84
        deck.add(new Card(0,1,'g','g',R.drawable.yellow2));//85
        deck.add(new Card(0,1,'g','g',R.drawable.yellow2_copy));//86
        deck.add(new Card(0,1,'g','g',R.drawable.yellow3));//87
        deck.add(new Card(0,1,'g','g',R.drawable.yellow3_copy));//88
        deck.add(new Card(0,1,'g','g',R.drawable.yellow4));//89
        deck.add(new Card(0,1,'g','g',R.drawable.yellow4_copy));//90
        deck.add(new Card(0,1,'g','g',R.drawable.yellow5));//91
        deck.add(new Card(0,1,'g','g',R.drawable.yellow5_copy));//92
        deck.add(new Card(0,1,'g','g',R.drawable.yellow6));//93
        deck.add(new Card(0,1,'g','g',R.drawable.yellow6_copy));//94
        deck.add(new Card(0,1,'g','g',R.drawable.yellow7));//95
        deck.add(new Card(0,1,'g','g',R.drawable.yellow7_copy));//96
        deck.add(new Card(0,1,'g','g',R.drawable.yellow8));//97
        deck.add(new Card(0,1,'g','g',R.drawable.yellow8_copy));//98
        deck.add(new Card(0,1,'g','g',R.drawable.yellow9));//99
        deck.add(new Card(0,1,'g','g',R.drawable.yellow9_copy));//100
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_draw2));//101
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_draw2_copy));//102
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_draw4));//103
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_reverse));//104
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_reverse_copy));//105
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_skip));//106
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_skip_copy));//107
        deck.add(new Card(0,1,'g','g',R.drawable.yellow_wild));//108


    } //makeDeck()

    /**
     * Method to Shuffle the Deck
     */
    public void shuffleDeck(){
        //how do you shuffle an arrayList
        Collections.shuffle(deck);//Collections.shuffle - shuffles an arrayList

        /*Card temp[] = new Card[108];
        int i;
        int n;
        int index = r.nextInt(108);
        temp[0] = deck.get(index);
        for(i = 1;  i < 108; i++){
            index = r.nextInt(108);
            temp[i] = deck.get(index);
            //detects if the value from the random index is equal to another one already in temp
            for(n = 0; n < 108; n++){
                while(temp[i] == temp[n] && temp[n] != null){
                    index = r.nextInt(108);
                    temp[i] = deck.get(index);
                }
            }
        }

        //sets the deck in use to the randomly ordered one
        for(i = 0; i < 108; i++){
            deck.set(i, temp[i]);
        }*/
    }//shuffleDeck

    /**
     * Method to add a card to a specific hand
     *
     * @param hand
     */
    //todo check if they're allowed to draw

    public void drawCard(ArrayList<Card> hand){

        checkIsEmpty();
        hand.add(deck.get(0));
        deck.remove(0);

    }//drawCard

    /**
     * Method to play a card from the hand
     *
     * @param hand
     * @param c
     */
    public void playCard(ArrayList<Card> hand, Card c){

        hand.remove(c);
        discardPile.add(c);
        color = discardPile.get(discardPile.size()).color;

    }//playCard

    /**
     * //Method to see if UNO can be called for a hand.
     *
     * @param hand
     * @param playerId
     * @return true or false
     */
    public boolean isUno(ArrayList<Card> hand, int playerId){

        if(hand.size() == 1) {

            return true;

        }
        else
        {
            return false;
        }

    }//isUno

    /**
     * get method for the size of a hand
     *
     *
     * @param hand
     * @return hand size
     */
    public int getHandSize(ArrayList<Card> hand){
        return hand.size();

    }


    /**
     * Method to get a formatted String describing the basic game state
     * @return player 1 and 2's id, with the turn
     */
    public String getGameState() {
        return "Player 1 ID: " + player1Id + "Player 2 ID: " + player2Id + ", Turn: " + turn;
    } //getGameState

    /**
     * Method to check if the deck is empty and makes and shuffles
     * a new one from the pile if it is
     *
     */
    public void checkIsEmpty(){
        int count = discardPile.size();
        if(deck.size() == 0){
            for(int i = 0; i < count-1; i++){
                deck.add(discardPile.get(i));
                discardPile.remove(i);
            }
            shuffleDeck();
        }
        Log.i("checkIsEmpty","Deck Size Is : " + deck.size());
    } //checkIsEmpty

    /**
     * selectCard action
     *
     * @return true if legal move
     */
    public boolean selectCard(int player1Id, int player2Id) {
        if(player1Id != turn) {
            return false;
        }
        else {
            return true;
        }
    }//selectCard


    /**
     * playCard action
     *
     * @return true if legal move
     */
    public boolean playCard(int playerId, Card cardToPlay)
    {
        if(playerId != turn) {
            return false;
        }
        else {
            if(playerId == player1Id) {
                playCard(hand1, cardToPlay);
            }
            else{
                playCard(hand2, cardToPlay);
            }
            return true;
        }
    }//playCard

    /**
     * getHand action
     *
     * @return true if legal move
     */
    public ArrayList<Card> getHand(int playerId)
    {
        if(playerId == player1Id) {
            return hand1;
        }
        else {
            return hand2;
        }
    }//getHand

    /**
     * drawCard action
     *
     * @return true if legal move
     */
    public boolean drawCard(int player1Id, int player2Id) {
        if (deckSize == 0) {
            return false;
        }
        else
        {
            return true;
        }
    }//drawCard


    /**
     * declareUno action
     *
     * @return true if legal move
     */
    public void declareUno(int playerId)
    {
        if((isUno(hand1, playerId) || isUno(hand2, playerId)) && (playerDeclaredUno == false)){
            if(playerId == player1Id && hand2.size() == 1 ){
                    for(int i = 0; i < 2; i++){
                    drawCard(hand2);
                    }
                    playerDeclaredUno = true;
            }
            else if(playerId == player2Id && hand1.size() == 1 ){
                for(int i = 0; i < 2; i++){
                    drawCard(hand1);
                }
                playerDeclaredUno = true;
            }
            else if(playerId == player1Id && hand1.size() == 1 ){
                playerDeclaredUno = true;
            }
            else if(playerId == player2Id && hand2.size() == 1 ){
                playerDeclaredUno = true;
            }
        }
    }


    /**
     * toString
     *
     * @return Game State
     */
    @Override
    public String toString() {
        int i = 0;
        /*
        updateDeckSize();
        String out = "Deck Cards: ";
        while(deck.get(i) != null) {
            out = out + "ID: "+ deck.get(i).getId() + " Value: " + deck.get(i).getValue() + " Type: " +
                    deck.get(i).getType() + " Color: " + deck.get(i).getColor() + ", ";
            i++;
        }
        i = 0;
        out = out + " Discard Pile: ";
        while(discardPile.get(i) != null) {
            out = out + "ID: "+ discardPile.get(i).getId() + " Value: " + discardPile.get(i).getValue() +
                    " Type: " + discardPile.get(i).getType() + " Color: " + discardPile.get(i).getColor();
            i++;
        }
        out = out + "Deck size: " + getDeckSize() + " Discard Pile size: "+ getDiscardPile() + " Turns: " + turn + " ";
        for (i = 0; i < hand1.size(); i++) {
            out = out + "hand1 card"+i+": " + "Color: " + hand1.get(i).getColor() + " Value: " +
                    hand1.get(i).getValue() + " Type: " + hand1.get(i).getType() + " " + "ID: " +
                    hand1.get(i).getId();
        }
        for (i = 0; i < hand2.size(); i++) {
            out = out + "hand2 card"+i+": " + "Color: " + hand2.get(i).getColor() + " Value: " +
                    hand2.get(i).getValue() + " Type: " + hand2.get(i).getType() + " " + "ID: " + hand2.get(i).getId();
        }
        out = out + "Player 1 ID: "+player1Id+" Player 2 ID: "+player2Id;
        return out;
        */
        return "";
    }//toString

    /**
     *  Set and Get Methods
     *
     */
    public void setPlayer1Id(int pid){ player1Id = pid; }

    public int getPlayer1Id(){ return player1Id; }

    public void setPlayer2Id(int pid){ player2Id = pid; }

    public int getPlayer2Id(){ return player2Id; }

    public int getTurn(){ return turn; }

    public void setTurn(int newTurn) {
        turn = newTurn;
        playerDeclaredUno = false;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public int getDeckSize() {return deckSize;}

    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    public void setPlayerDeclaredUno() { playerDeclaredUno = true; }

    public boolean getPlayerDeclaredUno() { return playerDeclaredUno; }

}
