package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the Event Activity class that...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class EventActivity extends AppCompatActivity {

    /**
     * Sets up the event info screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundraiser_info);

        /**
         * The fundraiser event's image
         */
        final ImageView eventImg = (ImageView) findViewById(R.id.fundraiserImage);

        /**
         * The fundraiser event's description
         */
        final TextView eventDescription = (TextView) findViewById(R.id.fundraiserDescription);

        // Grabs the resId for the event image and sets the ImageView to that image
        eventImg.setImageBitmap(MainActivity.fInfo.fundraiserImage);

        eventDescription.setText(MainActivity.fInfo.description);

        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);

        /**
         * This methods takes the user to the MainActivity screen when button is clicked
         */
        homeButton.setOnClickListener(new View.OnClickListener(){

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * MainActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                Intent homeIntent = new Intent(EventActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        ImageButton accountButton = (ImageButton) findViewById(R.id.loginButton);

        /**
         * This methods takes the user to the AccountActivity screen when button is clicked
         */
        accountButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * AccountActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                Intent accountIntent = new Intent(EventActivity.this, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

        ImageButton userBidsButton = (ImageButton) findViewById(R.id.whatIBidOnButton);

        /**
         * This methods takes the user to the BidsActivity screen when button is clicked
         */
        userBidsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (MainActivity.you != null) {
                    if (MainActivity.you.signedIn) {
                        Intent userBidIntent = new Intent(EventActivity.this, BidsActivity.class);
                        startActivity(userBidIntent);
                    } else {
                        Toast.makeText(EventActivity.this, "Log in or sign up to view!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(EventActivity.this, AccountActivity.class);
                        startActivity(loginIntent);
                    }
                } else {
                    Toast.makeText(EventActivity.this, "Log in or sign up to view!",
                            Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(EventActivity.this, AccountActivity.class);
                    startActivity(loginIntent);
                }
            }
        });

        ImageButton userHighestButton = (ImageButton) findViewById(R.id.itemsHighestButton);

        /**
         * This methods takes the user to the HighestActivity screen when button is clicked
         */
        userHighestButton.setOnClickListener(new View.OnClickListener(){

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * AccountActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                if (MainActivity.you != null) {
                    if (MainActivity.you.signedIn) {
                        Intent userBidIntent = new Intent(EventActivity.this, HighestActivity.class);
                        startActivity(userBidIntent);
                    } else {
                        Toast.makeText(EventActivity.this, "Log in or sign up to view!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(EventActivity.this, AccountActivity.class);
                        startActivity(loginIntent);
                    }
                } else {
                    Toast.makeText(EventActivity.this, "Log in or sign up to view!",
                            Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(EventActivity.this, AccountActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
    }
}
