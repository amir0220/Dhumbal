package com.ulm.shrestha.dhumbal;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private static int screenWidth;
    private static int screenHeight;
    private static ImageView lastCard;
    private static ImageView topCard;
    private static Stack<PlayCard> cardsInDeck;
    private static Stack<PlayCard> cardsPlayed;
    private static Stack<PlayCard> cardsWithUserPlayer;
    private static Stack<PlayCard> cardsWithPlayer1;
    private static Stack<PlayCard> cardsWithPlayer2;
    private static Stack<PlayCard> cardsWithPlayer3;
    private static int userPlayerCardX, userPlayerCardY, player1CardX, player1CardY;
    private static int player2CardX, player2CardY, player3CardX, player3CardY;
    private static int playedCardX, playedCardY;
    // set rotation values for user player cards
    private static int[] uPoneCardRotation = {0};
    private static int[] uPtwoCardsRotation = {-6, 6};
    private static int[] uPthreeCardsRotation = {-12, 0, 12};
    private static int[] uPfourCardsRotation = {-18, -6, 6, 18};
    private static int[] uPfiveCardsRotation = {-24, -12, 0, 12, 24};
    // set rotation values for player 1
    private static int[] p1oneCardRotation = {-90};
    private static int[] p1twoCardsRotation = {-95, -85};
    private static int[] p1threeCardsRotation = {-100, -90, -80};
    private static int[] p1fourCardsRotation = {-105, -95, -85, -75};
    private static int[] p1fiveCardsRotation = {-110, -100, -90, -80, -70};
    // set rotation values for player 2
    private static int[] p2oneCardRotation = {-180};
    private static int[] p2twoCardsRotation = {-185, -175};
    private static int[] p2threeCardsRotation = {-190, -180, -170};
    private static int[] p2fourCardsRotation = {-195, -185, -175, -165};
    private static int[] p2fiveCardsRotation = {-200, -190, -180, -170, -160};
    // set rotation values for player 3
    private static int[] p3oneCardRotation = {90};
    private static int[] p3twoCardsRotation = {85, 95};
    private static int[] p3threeCardsRotation = {80, 90, 100};
    private static int[] p3fourCardsRotation = {75, 85, 95, 105};
    private static int[] p3fiveCardsRotation = {70, 80, 90, 100, 110};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get screen width and height
        Display display = getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        // set coordinates for each user's first card location
        userPlayerCardX = screenWidth / 2 - 100;
        userPlayerCardY = screenHeight - 300;
        player1CardX = screenWidth - 20;
        player1CardY = screenHeight / 2 - 150;
        player2CardX = screenWidth / 2 + 150;
        player2CardY = -380;
        player3CardX = 20;
        player3CardY = screenHeight / 2 - 400;
        playedCardX = screenWidth / 2 - 450;
        playedCardY = screenHeight / 2 - 300;

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);

        displayDummyDeck();

        shuffleDeckDistributeCards();
    }

    private void displayDummyDeck() {
        // Get the existing RelativeLayout from xml file to add the ImageView.
        RelativeLayout mRelativeLayout = (RelativeLayout) findViewById(R.id.main_relative_layout);

        // Instantiate the lastCard ImageView and define it's properties.
        lastCard = new ImageView(this);
        lastCard.setImageResource(R.drawable.fbv_b);
        lastCard.setX(200);
        lastCard.setY(100);
        // Add the ImageView to the layout.
        mRelativeLayout.addView(lastCard);

        // Instantiate the topCard ImageView and define it's properties.
        topCard = new ImageView(this);
        topCard.setImageResource(R.drawable.c1);
        topCard.setX(210);
        topCard.setY(110);
        // Add the ImageView to the layout.
        mRelativeLayout.addView(topCard);

        // Instantiate an ImageView and define it's properties.
        ImageView dummyCard = new ImageView(this);
        dummyCard.setImageResource(R.drawable.h3);
        dummyCard.setX(player3CardX);
        dummyCard.setY(player3CardY);
        // Add the ImageView to the layout.
        mRelativeLayout.addView(dummyCard);
        // rotate test
        dummyCard.setPivotX(0);
        dummyCard.setPivotY(340);
        dummyCard.setRotation(90);

        dummyCard = new ImageView(this);
        dummyCard.setImageResource(R.drawable.c13);
        dummyCard.setX(player2CardX);
        dummyCard.setY(player2CardY);
        // Add the ImageView to the layout.
        mRelativeLayout.addView(dummyCard);
        // rotate test
        dummyCard.setPivotX(0);
        dummyCard.setPivotY(340);
        dummyCard.setRotation(180);

        dummyCard = new ImageView(this);
        dummyCard.setImageResource(R.drawable.c9);
        dummyCard.setX(player1CardX);
        dummyCard.setY(player1CardY);
        // Add the ImageView to the layout.
        mRelativeLayout.addView(dummyCard);
        // rotate test
        dummyCard.setPivotX(0);
        dummyCard.setPivotY(340);
        dummyCard.setRotation(-90);

        dummyCard = new ImageView(this);
        dummyCard.setImageResource(R.drawable.d6);
        dummyCard.setX(userPlayerCardX);
        dummyCard.setY(userPlayerCardY);
        // Add the ImageView to the layout.
        mRelativeLayout.addView(dummyCard);
        // rotate test
        dummyCard.setPivotX(0);
        dummyCard.setPivotY(340);
        dummyCard.setRotation(0);

        Random randomGen = new Random();
        for (int i = 0; i < 10; i++) {
            int randomX = randomGen.nextInt(600);
            int randomY = randomGen.nextInt(250);
            int randomRotation = randomGen.nextInt(360);
            dummyCard = new ImageView(this);
            dummyCard.setImageResource(R.drawable.d10);
            dummyCard.setX(playedCardX + randomX);
            dummyCard.setY(playedCardY + randomY);
            // Add the ImageView to the layout.
            mRelativeLayout.addView(dummyCard);
            // rotate test
            dummyCard.setRotation(randomRotation);
        }
    }

    private void shuffleDeckDistributeCards() {
        // add cards to the cardsInDeck stack.
        cardsInDeck = new Stack<PlayCard>();
        cardsInDeck.push(new PlayCard("c1"));
        cardsInDeck.push(new PlayCard("c2"));
        cardsInDeck.push(new PlayCard("c3"));
        cardsInDeck.push(new PlayCard("c4"));
        cardsInDeck.push(new PlayCard("c5"));
        cardsInDeck.push(new PlayCard("c6"));
        cardsInDeck.push(new PlayCard("c7"));
        cardsInDeck.push(new PlayCard("c8"));
        cardsInDeck.push(new PlayCard("c9"));
        cardsInDeck.push(new PlayCard("c10"));
        cardsInDeck.push(new PlayCard("c11"));
        cardsInDeck.push(new PlayCard("c12"));
        cardsInDeck.push(new PlayCard("c13"));
        cardsInDeck.push(new PlayCard("d1"));
        cardsInDeck.push(new PlayCard("d2"));
        cardsInDeck.push(new PlayCard("d3"));
        cardsInDeck.push(new PlayCard("d4"));
        cardsInDeck.push(new PlayCard("d5"));
        cardsInDeck.push(new PlayCard("d6"));
        cardsInDeck.push(new PlayCard("d7"));
        cardsInDeck.push(new PlayCard("d8"));
        cardsInDeck.push(new PlayCard("d9"));
        cardsInDeck.push(new PlayCard("d10"));
        cardsInDeck.push(new PlayCard("d11"));
        cardsInDeck.push(new PlayCard("d12"));
        cardsInDeck.push(new PlayCard("d13"));
        cardsInDeck.push(new PlayCard("h1"));
        cardsInDeck.push(new PlayCard("h2"));
        cardsInDeck.push(new PlayCard("h3"));
        cardsInDeck.push(new PlayCard("h4"));
        cardsInDeck.push(new PlayCard("h5"));
        cardsInDeck.push(new PlayCard("h6"));
        cardsInDeck.push(new PlayCard("h7"));
        cardsInDeck.push(new PlayCard("h8"));
        cardsInDeck.push(new PlayCard("h9"));
        cardsInDeck.push(new PlayCard("h10"));
        cardsInDeck.push(new PlayCard("h11"));
        cardsInDeck.push(new PlayCard("h12"));
        cardsInDeck.push(new PlayCard("h13"));
        cardsInDeck.push(new PlayCard("s1"));
        cardsInDeck.push(new PlayCard("s2"));
        cardsInDeck.push(new PlayCard("s3"));
        cardsInDeck.push(new PlayCard("s4"));
        cardsInDeck.push(new PlayCard("s5"));
        cardsInDeck.push(new PlayCard("s6"));
        cardsInDeck.push(new PlayCard("s7"));
        cardsInDeck.push(new PlayCard("s8"));
        cardsInDeck.push(new PlayCard("s9"));
        cardsInDeck.push(new PlayCard("s10"));
        cardsInDeck.push(new PlayCard("s11"));
        cardsInDeck.push(new PlayCard("s12"));
        cardsInDeck.push(new PlayCard("s13"));

        // shuffle the deck.
        Collections.shuffle(cardsInDeck);

        // initialize players' stack
        cardsWithUserPlayer = new Stack<PlayCard>();
        cardsWithPlayer1 = new Stack<PlayCard>();
        cardsWithPlayer2 = new Stack<PlayCard>();
        cardsWithPlayer3 = new Stack<PlayCard>();

        // distribute the cards to each player.
        int cardNum = 1;
        for (int i = 0; i < 20; i++) {
            if (cardNum == 4) {
                cardsWithPlayer3.push(cardsInDeck.pop());
                cardNum = 1;
            } else if (cardNum == 3) {
                cardsWithPlayer2.push(cardsInDeck.pop());
            } else if (cardNum == 2) {
                cardsWithPlayer1.push(cardsInDeck.pop());
            } else {
                cardsWithUserPlayer.push(cardsInDeck.pop());
            }
            cardNum++;
        }
    }


    // method to restart app when "New Game" button is pressed
    public void restartApp(View view) {
        final ProgressDialog progress = ProgressDialog.show(this, "Starting New Game", "Setting up board for new game...", true);

        new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                // dismiss the progressdialog
                progress.dismiss();
            }
        }.start();

        //progress.dismiss();
        this.recreate();
    }

    private class PlayCard {

        private String cardNum;
        private int xStart;
        private int yStart;

        public PlayCard(String cardName) {
            xStart = 0;
            yStart = 0;
            cardNum = cardName;
        }

        public void setXStart(int xVal) {
            this.xStart = xVal;
        }

        public void setYStart(int yVal) {
            this.yStart = yVal;
        }

        public String getCardNum() {
            return this.cardNum;
        }

        public int getCardXStart() {
            return this.xStart;
        }

        public int getCardYStart() {
            return this.yStart;
        }
    }

}
