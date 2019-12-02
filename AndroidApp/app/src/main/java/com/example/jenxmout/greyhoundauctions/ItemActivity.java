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

        boolean signedIn = false;
        if(MainActivity.you != null)
            signedIn = true;
        Log.w("user signed in",String.valueOf(signedIn));

        //Bid Button functionality
        Button bidButton = (Button) findViewById(R.id.bidButton);
        bidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getIntent().getIntExtra("itemPosition", 0);
                EditText bidET = findViewById(R.id.bidAmount);
                String bid = String.valueOf(bidET.getText());
                double minNextBid = getIntent().getDoubleExtra("itemCHB",
                        0.0) + getIntent().getDoubleExtra("itemMinInc",
                        0.0);

                if(Double.valueOf(bid) >= minNextBid) {

                    if (MainActivity.you != null) {
                        if (MainActivity.you.signedIn) {
                            MainActivity.you.bid(Double.valueOf(bid), MainActivity.ais.items.get(position));
                            Log.w("Sucess?", "bid succeeded");
                            Toast.makeText(ItemActivity.this, "Bid placed!",
                                    Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                        }
                        else{
                            Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                    Toast.LENGTH_LONG).show();
                            Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                            startActivity(loginIntent);
                        }
                    }
                    else {
                        Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                        startActivity(loginIntent);
                    }
                }
                else
                    Toast.makeText(ItemActivity.this, "Bid must be greater than or equal to $" +
                            String.valueOf(minNextBid), Toast.LENGTH_LONG).show();
            }
        });


        //auto-bid button functionality
        Button autoBidButton = (Button) findViewById(R.id.autoBidButton);
        autoBidButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = getIntent().getIntExtra("itemPosition", 0);
                EditText maxBidET = findViewById(R.id.maxBid);
                double maxBid = Double.valueOf(String.valueOf(maxBidET.getText()));
                double minNextBid = getIntent().getDoubleExtra("itemCHB",
                        0.0) + getIntent().getDoubleExtra("itemMinInc",
                        0.0);

                if(maxBid >= minNextBid){
                    if(MainActivity.you.autoBid(maxBid, MainActivity.ais.items.get(position))){
                        Toast.makeText(ItemActivity.this, "Bid placed!",
                                Toast.LENGTH_LONG).show();
                        Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                        startActivity(homeIntent);
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
                            + "equal to $" + String.valueOf(minNextBid), Toast.LENGTH_LONG).show();
            }
        });






















    }
}
