package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView highestBid = findViewById(R.id.highestBid);
        highestBid.setText("Current Highest Bid: $" + getIntent().getDoubleExtra("itemCHB",
                0.0) + "0");

        TextView minBid = findViewById(R.id.minNextBid);
        minBid.setText("Min Next Bid: $" + (getIntent().getDoubleExtra("itemCHB",
                0.0) + getIntent().getDoubleExtra("itemMinInc", 0.0)) + "0");

        ImageView imgView = findViewById(R.id.itemImage);
        imgView.setImageResource(getIntent().getIntExtra("itemImage", -1));

        TextView infoView = findViewById(R.id.itemDescription);
        infoView.setText(getIntent().getStringExtra("itemDesc") +
                 "\nCurrentHighestBidder: " + getIntent().getStringExtra("itemCHBr") +
                "\n\n" + getIntent().getStringExtra("itemTags"));

        // Bid Info Button
        Button bidInfoButton = (Button) findViewById(R.id.helpButton);
        bidInfoButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * EventActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                for (int i=0; i < 2; i++) {
                    Toast.makeText(ItemActivity.this, "Set Auto-Bid amount at a specific " +
                            "increment every time another bidder bids up until a certain maximum " +
                            "amount.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
