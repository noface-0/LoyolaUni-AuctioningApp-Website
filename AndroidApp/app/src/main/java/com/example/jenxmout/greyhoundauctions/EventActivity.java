package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is the Event Activity class that...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
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
        int resID = getResources().getIdentifier("inner_harbor_info_pic", "drawable",
                getPackageName());
        eventImg.setImageResource(resID);

        eventDescription.setText("This is a fundraiser to support the cleanliness of the Inner " +
                "Harbor, all proceeds go towards keeping the Inner Harbor clean!");

        Button homeButton = (Button) findViewById(R.id.homeButton);
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

        Button accountButton = (Button) findViewById(R.id.loginButton);
        accountButton.setOnClickListener(new View.OnClickListener(){

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
    }
}
