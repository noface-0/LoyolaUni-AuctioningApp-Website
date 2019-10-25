package com.example.jenxmout.greyhoundauctions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is the Item Activity class that
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
 */
public class ItemActivity extends AppCompatActivity {

    /**
     * Sets up the item screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //set up item display
        TextView titleView = findViewById(R.id.itemTitleView);
        titleView.setText(getIntent().getStringExtra("itemTitle"));

        ImageView imgView = findViewById(R.id.itemImage);
        imgView.setImageResource(getIntent().getIntExtra("itemImage", -1));

        TextView infoView = findViewById(R.id.itemDescription);
        infoView.setText(getIntent().getStringExtra("itemDesc") +
                "\nCurrent Highest Bid: " + getIntent().getDoubleExtra("itemCHB",
                0.0) + "\nCurrentHighestBidder: " +
                getIntent().getStringExtra("itemCHBr") + "\n\n" +
                getIntent().getStringExtra("itemTags"));




        /**
         * This method sets a click listener for the button in the UI
         * When clicked, the user is taken to the next view
         * EventActivity
         *
         * @param v the view of the current state
         */

    }
}
