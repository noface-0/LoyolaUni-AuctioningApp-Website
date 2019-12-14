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
 * @version 1.0 12/15/19
 */
public class ItemActivity extends AppCompatActivity {

    /**
     * ...
     */
    boolean fromMain = false;

    /**
     * ...
     */
    boolean fromBids = false;

    /**
     * ...
     */
    boolean fromHighest = false;

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


        TextView infoView = findViewById(R.id.itemDescription);
        TextView titleView = findViewById(R.id.itemTitleView);
        TextView highestBid = findViewById(R.id.highestBid);
        TextView minBid = findViewById(R.id.minNextBid);
        ImageView imgView = findViewById(R.id.itemImage);


        if(getIntent().getStringExtra("from").equals("main")){
            fromMain = true;
        }
        else if(getIntent().getStringExtra("from").equals("bids")){
            fromBids = true;
        }
        else if(getIntent().getStringExtra("from").equals("highest")){
            fromHighest = true;
        }

        if(fromMain) {
            MainActivity.ais.items.get(position).updateAutoBid();


            titleView.setText(MainActivity.ais.items.get(position).title);

            highestBid.setText("Current Highest Bid: $"
                    + MainActivity.ais.items.get(position).currentHighestBid);

            minBid.setText("Min Next Bid: $" + (MainActivity.ais.items.get(position).currentHighestBid
                    + MainActivity.ais.items.get(position).minInc) + "0");

            imgView.setImageBitmap(MainActivity.ais.items.get(position).resID);

            infoView.setText(MainActivity.ais.items.get(position).description +
                    "\nCurrentHighestBidder: " + MainActivity.ais.items.get(position).currentHighestBidder +
                    "\n\n" + getIntent().getStringExtra("itemTags"));
        }
        else if(fromBids){
            MainActivity.you.itemsBidOn.get(position).updateAutoBid();

            titleView.setText(MainActivity.you.itemsBidOn.get(position).title);

            highestBid.setText("Current Highest Bid: $"
                    + MainActivity.you.itemsBidOn.get(position).currentHighestBid);

            minBid.setText("Min Next Bid: $" + (MainActivity.you.itemsBidOn.get(position).currentHighestBid
                    + MainActivity.you.itemsBidOn.get(position).minInc) + "0");

            imgView.setImageBitmap(MainActivity.you.itemsBidOn.get(position).resID);

            infoView.setText(MainActivity.you.itemsBidOn.get(position).description +
                    "\nCurrentHighestBidder: " + MainActivity.you.itemsBidOn.get(position).currentHighestBidder +
                    "\n\n" + getIntent().getStringExtra("itemTags"));
        }
        else if(fromHighest){
            MainActivity.you.itemsCurrentHighestBidderOn.get(position).updateAutoBid();
            titleView.setText(MainActivity.you.itemsCurrentHighestBidderOn.get(position).title);

            highestBid.setText("Current Highest Bid: $"
                    + MainActivity.you.itemsCurrentHighestBidderOn.get(position).currentHighestBid);

            minBid.setText("Min Next Bid: $" + (MainActivity.you.itemsCurrentHighestBidderOn.get(position).currentHighestBid
                    + MainActivity.you.itemsCurrentHighestBidderOn.get(position).minInc) + "0");

            imgView.setImageBitmap(MainActivity.you.itemsCurrentHighestBidderOn.get(position).resID);

            infoView.setText(MainActivity.you.itemsCurrentHighestBidderOn.get(position).description +
                    "\nCurrentHighestBidder: " + MainActivity.you.itemsCurrentHighestBidderOn.get(position).currentHighestBidder +
                    "\n\n" + getIntent().getStringExtra("itemTags"));
        }

        // Bid Info Button
        Button bidInfoButton = (Button) findViewById(R.id.helpButton);

        /**
         * This methods displays a toast about auto-bid when button is clicked
         */
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


        //Bid Button functionality
        final Button bidButton = (Button) findViewById(R.id.bidButton);

        if(MainActivity.auctionOver)
            bidButton.setEnabled(false);
        else
            bidButton.setEnabled(true);
        /**
         * This methods allows a user to bid when button is clicked
         */
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

                if (fromMain) {

                    double minNextBid = MainActivity.ais.items.get(position).currentHighestBid
                            + MainActivity.ais.items.get(position).minInc;

                    if (Double.valueOf(bid) >= minNextBid) {
                        if (MainActivity.you != null) {
                            if (MainActivity.you.signedIn) {
                                MainActivity.you.bid(Double.valueOf(bid), MainActivity.ais.items.get(position));
                                Log.w("Sucess?", "bid succeeded");
                                Toast.makeText(ItemActivity.this, "Bid placed!",
                                        Toast.LENGTH_LONG).show();

                                String itemsBidOn = "";
                                int cnt = 0;
                                for (Item i : MainActivity.you.itemsBidOn) {
                                    Log.w("item title", i.title);
                                    if (cnt > 0)
                                        itemsBidOn += ",";
                                    itemsBidOn += i.title;
                                    cnt++;
                                }

                                Log.w("items bid on", itemsBidOn);

                                String name = MainActivity.you.firstName + " " + MainActivity.you.lastName;

                                BackgroundWorker bw = new BackgroundWorker(ItemActivity.this);
                                bw.execute("update user data", itemsBidOn, MainActivity.you.firstName, MainActivity.you.lastName);

                                BackgroundWorker bw2 = new BackgroundWorker(ItemActivity.this);
                                bw2.execute("update item data", bid, name, MainActivity.ais.items.get(position).title);

                                //MainActivity.ais  = null;

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
                else if (fromBids) {
                    double minNextBid = MainActivity.you.itemsBidOn.get(position).currentHighestBid
                            + MainActivity.you.itemsBidOn.get(position).minInc;

                    if (Double.valueOf(bid) >= minNextBid) {
                        if (MainActivity.you != null) {
                            if (MainActivity.you.signedIn) {
                                MainActivity.you.bid(Double.valueOf(bid), MainActivity.you.itemsBidOn.get(position));
                                Log.w("Sucess?", "bid succeeded");
                                Toast.makeText(ItemActivity.this, "Bid placed!",
                                        Toast.LENGTH_LONG).show();

                                String itemsBidOn = "";
                                int cnt = 0;
                                for (Item i : MainActivity.you.itemsBidOn) {
                                    Log.w("item title", i.title);
                                    if (cnt > 0)
                                        itemsBidOn += ",";
                                    itemsBidOn += i.title;
                                    cnt++;
                                }

                                Log.w("items bid on", itemsBidOn);

                                String name = MainActivity.you.firstName + " " + MainActivity.you.lastName;

                                BackgroundWorker bw = new BackgroundWorker(ItemActivity.this);
                                bw.execute("update user data", itemsBidOn, MainActivity.you.firstName, MainActivity.you.lastName);

                                BackgroundWorker bw2 = new BackgroundWorker(ItemActivity.this);
                                bw2.execute("update item data", bid, name, MainActivity.you.itemsBidOn.get(position).title);

                                //MainActivity.ais  = null;

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
                else if (fromHighest) {
                    double minNextBid = MainActivity.you.itemsCurrentHighestBidderOn.get(position).currentHighestBid
                            + MainActivity.you.itemsCurrentHighestBidderOn.get(position).minInc;

                    if (Double.valueOf(bid) >= minNextBid) {
                        if (MainActivity.you != null) {
                            if (MainActivity.you.signedIn) {
                                MainActivity.you.bid(Double.valueOf(bid), MainActivity.you.itemsCurrentHighestBidderOn.get(position));
                                Log.w("Sucess?", "bid succeeded");
                                Toast.makeText(ItemActivity.this, "Bid placed!",
                                        Toast.LENGTH_LONG).show();

                                String itemsBidOn = "";
                                int cnt = 0;
                                for (Item i : MainActivity.you.itemsBidOn) {
                                    Log.w("item title", i.title);
                                    if (cnt > 0)
                                        itemsBidOn += ",";
                                    itemsBidOn += i.title;
                                    cnt++;
                                }

                                Log.w("items bid on", itemsBidOn);

                                String name = MainActivity.you.firstName + " " + MainActivity.you.lastName;

                                BackgroundWorker bw = new BackgroundWorker(ItemActivity.this);
                                bw.execute("update user data", itemsBidOn, MainActivity.you.firstName, MainActivity.you.lastName);

                                BackgroundWorker bw2 = new BackgroundWorker(ItemActivity.this);
                                bw2.execute("update item data", bid, name, MainActivity.you.itemsCurrentHighestBidderOn.get(position).title);

                                //MainActivity.ais  = null;

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

            }
        });


        //auto-bid button functionality
        Button autoBidButton = (Button) findViewById(R.id.autoBidButton);

        if(MainActivity.auctionOver)
            autoBidButton.setEnabled(false);
        else
            autoBidButton.setEnabled(true);

        /**
         * This methods allows a user to auto-bid when button is clicked
         */
        autoBidButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user places an auto-bid on the current item
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                int position = getIntent().getIntExtra("itemPosition", 0);
                EditText maxBidET = findViewById(R.id.maxBid);
                double maxBid = Double.valueOf(String.valueOf(maxBidET.getText()));

                if (fromMain) {
                    double minNextBid = MainActivity.ais.items.get(position).currentHighestBid
                            + MainActivity.ais.items.get(position).minInc;

                    if (maxBid >= minNextBid && maxBid != MainActivity.ais.items.get(position).autoBidMax[0]
                            && maxBid != MainActivity.ais.items.get(position).autoBidMax[1]) {
                        if (MainActivity.you != null) {
                            if (MainActivity.you.signedIn) {
                                if (MainActivity.ais.items.get(position).addAutoBid(MainActivity.you, maxBid)) {
                                    Toast.makeText(ItemActivity.this, "Bid placed!",
                                            Toast.LENGTH_LONG).show();

                                    for(Item i : MainActivity.you.itemsBidOn){
                                        if(i.title.equals(MainActivity.ais.items.get(position).title)){
                                            MainActivity.you.itemsBidOn.remove(i);
                                        }
                                    }
                                    MainActivity.you.itemsBidOn.add(MainActivity.ais.items.get(position));

                                    String itemsBidOn = "";
                                    int cnt = 0;
                                    for (Item i : MainActivity.you.itemsBidOn) {
                                        Log.w("item title", i.title);
                                        if (cnt > 0)
                                            itemsBidOn += ",";
                                        itemsBidOn += i.title;
                                        cnt++;
                                    }

                                    Log.w("items bid on", itemsBidOn);
                                    String name = MainActivity.you.firstName + " " + MainActivity.you.lastName;

                                    BackgroundWorker bw = new BackgroundWorker(ItemActivity.this);
                                    bw.execute("update user data", itemsBidOn, MainActivity.you.firstName, MainActivity.you.lastName);

                                    Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                                    startActivity(homeIntent);
                                } else {
                                    Toast.makeText(ItemActivity.this, "You've been outbid!",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                    Toast.LENGTH_LONG).show();
                            Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                            startActivity(loginIntent);
                        }
                    } else
                        Toast.makeText(ItemActivity.this, "Max bid must be greater than or "
                                + "equal to $" + String.valueOf(minNextBid), Toast.LENGTH_LONG).show();
                }
            else if(fromBids) {
                double minNextBid = MainActivity.you.itemsBidOn.get(position).currentHighestBid
                        + MainActivity.you.itemsBidOn.get(position).minInc;

                if (maxBid >= minNextBid && maxBid != MainActivity.you.itemsBidOn.get(position).autoBidMax[0]
                        && maxBid != MainActivity.you.itemsBidOn.get(position).autoBidMax[1]) {
                    if (MainActivity.you != null) {
                        if (MainActivity.you.signedIn) {
                            if (MainActivity.you.itemsBidOn.get(position).addAutoBid(MainActivity.you, maxBid)) {
                                Toast.makeText(ItemActivity.this, "Bid placed!",
                                        Toast.LENGTH_LONG).show();

                                String itemsBidOn = "";
                                int cnt = 0;
                                for (Item i : MainActivity.you.itemsBidOn) {
                                    Log.w("item title", i.title);
                                    if (cnt > 0)
                                        itemsBidOn += ",";
                                    itemsBidOn += i.title;
                                    cnt++;
                                }

                                Log.w("items bid on", itemsBidOn);

                                BackgroundWorker bw = new BackgroundWorker(ItemActivity.this);
                                bw.execute("update user data", itemsBidOn, MainActivity.you.firstName, MainActivity.you.lastName);

                                Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                                startActivity(homeIntent);
                            } else {
                                Toast.makeText(ItemActivity.this, "You've been outbid!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(ItemActivity.this, "Log in or sign up to bid!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(ItemActivity.this, AccountActivity.class);
                        startActivity(loginIntent);
                    }
                } else
                    Toast.makeText(ItemActivity.this, "Max bid must be greater than or "
                            + "equal to $" + String.valueOf(minNextBid), Toast.LENGTH_LONG).show();
            }
                else if (fromHighest) {
                    double minNextBid = MainActivity.you.itemsCurrentHighestBidderOn.get(position).currentHighestBid
                            + MainActivity.you.itemsCurrentHighestBidderOn.get(position).minInc;

                    if (maxBid >= minNextBid && maxBid != MainActivity.ais.items.get(position).autoBidMax[0]
                            && maxBid != MainActivity.ais.items.get(position).autoBidMax[1]) {
                        if (MainActivity.you != null) {
                            if (MainActivity.you.signedIn) {
                                if (MainActivity.you.itemsCurrentHighestBidderOn.get(position).addAutoBid(MainActivity.you, maxBid)) {
                                    Toast.makeText(ItemActivity.this, "Bid placed!",
                                            Toast.LENGTH_LONG).show();

                                    String itemsBidOn = "";
                                    int cnt = 0;
                                    for (Item i : MainActivity.you.itemsBidOn) {
                                        Log.w("item title", i.title);
                                        if (cnt > 0)
                                            itemsBidOn += ",";
                                        itemsBidOn += i.title;
                                        cnt++;
                                    }

                                    Log.w("items bid on", itemsBidOn);

                                    BackgroundWorker bw = new BackgroundWorker(ItemActivity.this);
                                    bw.execute("update user data", itemsBidOn, MainActivity.you.firstName, MainActivity.you.lastName);

                                    Intent homeIntent = new Intent(ItemActivity.this, MainActivity.class);
                                    startActivity(homeIntent);
                                } else {
                                    Toast.makeText(ItemActivity.this, "You've been outbid!",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
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
            }
        });
    }
}
