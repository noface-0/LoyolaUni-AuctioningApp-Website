package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the Item Activity class that...
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
        int position = getIntent().getIntExtra("itemPosition", -1);
        MainActivity.ais.items.get(position).updateAutoBid();

        //set up item display
        TextView titleView = findViewById(R.id.itemTitleView);
        titleView.setText(getIntent().getStringExtra("itemTitle"));

        TextView highestBid = findViewById(R.id.highestBid);
        highestBid.setText("Current Highest Bid: $"
                + MainActivity.ais.items.get(position).currentHighestBid);

        TextView minBid = findViewById(R.id.minNextBid);
        minBid.setText("Min Next Bid: $" + (MainActivity.ais.items.get(position).currentHighestBid
                + MainActivity.ais.items.get(position).minInc + "0"));

        ImageView imgView = findViewById(R.id.itemImage);
        imgView.setImageBitmap(MainActivity.ais.items.get(position).resID);

        TextView infoView = findViewById(R.id.itemDescription);
        infoView.setText(MainActivity.ais.items.get(position).description +
                 "\nCurrentHighestBidder: " + MainActivity.ais.items.get(position).currentHighestBidder +
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

        // ....
        boolean signedIn = false;
        if(MainActivity.you != null)
            signedIn = true;
        Log.w("user signed in",String.valueOf(signedIn));

        //Bid Button functionality
        Button bidButton = (Button) findViewById(R.id.bidButton);
        bidButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user places a bid on the current item
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                int position = getIntent().getIntExtra("itemPosition", 0);
                EditText bidET = findViewById(R.id.bidAmount);
                String bid = String.valueOf(bidET.getText());

                double minNextBid = MainActivity.ais.items.get(position).currentHighestBid
                        + MainActivity.ais.items.get(position).minInc;

                if (Double.valueOf(bid) >= minNextBid) {
                    if (MainActivity.you != null) {
                        if (MainActivity.you.signedIn) {
                            MainActivity.you.bid(Double.valueOf(bid), MainActivity.ais.items.get(position));
                            Log.w("Sucess?", "bid succeeded");
                            Toast.makeText(ItemActivity.this, "Bid placed!",
                                    Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                        } else {
                            Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                    Toast.LENGTH_LONG).show();
                            Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                            startActivity(loginIntent);
                        }
                    } else {
                        Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                        startActivity(loginIntent);
                    }
                } else
                    Toast.makeText(ItemActivity.this, "Bid must be greater than or equal to $" +
                            String.valueOf(minNextBid), Toast.LENGTH_LONG).show();
            }
        });


        //auto-bid button functionality
        Button autoBidButton = (Button) findViewById(R.id.autoBidButton);
        autoBidButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user places an auto-bid on the current item
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                int position = getIntent().getIntExtra("itemPosition", 0);
                EditText maxBidET = findViewById(R.id.maxBid);
                double maxBid = Double.valueOf(String.valueOf(maxBidET.getText()));
                double minNextBid = MainActivity.ais.items.get(position).currentHighestBid
                        + MainActivity.ais.items.get(position).minInc;

                if(maxBid >= minNextBid && maxBid != MainActivity.ais.items.get(position).autoBidMax[0]
                        && maxBid != MainActivity.ais.items.get(position).autoBidMax[1]){
                    if(MainActivity.you != null) {
                        if(MainActivity.you.signedIn) {
                            if (MainActivity.ais.items.get(position).addAutoBid(MainActivity.you, maxBid)) {
                                Toast.makeText(ItemActivity.this, "Bid placed!",
                                        Toast.LENGTH_LONG).show();
                                MainActivity.you.itemsBidOn.add(MainActivity.ais.items.get(position));
                                Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                                startActivity(homeIntent);
                            }
                            else{
                                Toast.makeText(ItemActivity.this, "You've been outbid!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                        startActivity(loginIntent);
                    }
                }
                else
                    Toast.makeText(ItemActivity.this, "Max bid must be greater than or "
                            + "equal to $" + String.valueOf(minNextBid) , Toast.LENGTH_LONG).show();
            }
        });
    }
}
