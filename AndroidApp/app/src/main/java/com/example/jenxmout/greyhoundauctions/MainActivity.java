package com.example.jenxmout.greyhoundauctions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.Locale;


/**
 * This is the Main Activity class that
 * displays the items up for auction
 * and allows users to search by tags,
 * select items, and access other menu options
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
 */
public class MainActivity extends AppCompatActivity {

    // Search Bar & List View
    SearchView searchBar;
    ListView listView;
    String[] titles;
    String[] descriptions;
    int[] images;
    double[] currentHighestBids;
    String[] currentHighestBidders;
    String[][] tags;

    // CountDown Timer
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView textViewCountDown;
    private Button buttonStartPause;
    private Button buttonReset;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis;
    private long endTime;

    /**
     * Sets up the main screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create items (first sprint)
        String[] item1Tags = {"ipad", "apple", "tech"};
        final Item item1 = new Item("iPad",
                "This is a brand new iPad Pro, it has 64GB memory and was donated by Campus " +
                        "Ministry.", R.drawable.auction_item_ipad,
                10, item1Tags, 1000);
        String[] item2Tags = {"golf", "sports", "fathers day"};
        Item item2 = new Item("Golf Clubs", "Looking for a Father's Day gift? We've got" +
                " the gift for you! This is a six piece golf club set, bag included!",
                R.drawable.auction_item_golf_clubs, 5, item2Tags, 50);
        String[] item3Tags = {"basketball", "sports", "fathers day", "bball"};
        Item item3 = new Item("Basketball Tickets", "Two tickets to the Philadelphia " +
                "76ers vs the New Orleans Pelicans.", R.drawable.auction_item_tickets, 5,
                item3Tags, 30);

        //create auctionitems object with items created
        final AuctionItems ais = new AuctionItems();
        ais.items.add(item1);
        ais.items.add(item2);
        ais.items.add(item3);

        //instantiate titles, descriptions, images, currentHighestBid, and currentHighestBidder with
        //size of auctionitems
        titles = new String[ais.items.size()];
        descriptions = new String[ais.items.size()];
        images = new int[ais.items.size()];
        currentHighestBids = new double[ais.items.size()];
        currentHighestBidders = new String[ais.items.size()];
        tags = new String[ais.items.size()][];

        //populates all arrays with item info for display in UI
        for (int i = 0; i < titles.length; i++) {
            titles[i] = ais.items.get(i).title;
        }

        for (int i = 0; i < descriptions.length; i++) {
            descriptions[i] = ais.items.get(i).description;
        }

        for (int i = 0; i < images.length; i++) {
            images[i] = ais.items.get(i).resID;
        }

        for (int i = 0; i < currentHighestBids.length; i++) {
            currentHighestBids[i] = ais.items.get(i).currentHighestBid;
        }

        for (int i = 0; i < currentHighestBidders.length; i++) {
            currentHighestBidders[i] = ais.items.get(i).currentHighestBidder;
        }

        //create
        listView = (ListView) findViewById(R.id.list_of_items);

        MyAdapter adptr = new MyAdapter(this, titles, descriptions, images, currentHighestBids,
                currentHighestBidders);
        listView.setAdapter(adptr);

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent item1Intent = new Intent(MainActivity.this, ItemActivity.class);
                    item1Intent.putExtra("itemTitle", titles[0]);
                    item1Intent.putExtra("itemImage", images[0]);
                    item1Intent.putExtra("itemCHB", currentHighestBids[0]);
                    item1Intent.putExtra("itemDesc", descriptions[0]);
                    item1Intent.putExtra("itemCHBr", currentHighestBidders[0]);

                    String tagsStr = "";
                    for(String tag : ais.items.get(0).tags){
                        tagsStr += "#" + tag + " ";
                    }

                    item1Intent.putExtra("itemTags", tagsStr);

                    startActivity(item1Intent);

                }
                if (position == 1) {
                    Intent item2Intent = new Intent(MainActivity.this, ItemActivity.class);

                    item2Intent.putExtra("itemTitle", titles[1]);
                    item2Intent.putExtra("itemImage", images[1]);
                    item2Intent.putExtra("itemCHB", currentHighestBids[1]);
                    item2Intent.putExtra("itemDesc", descriptions[1]);
                    item2Intent.putExtra("itemCHBr", currentHighestBidders[1]);

                    String tagsStr = "";
                    for(String tag : ais.items.get(1).tags){
                        tagsStr += "#" + tag + " ";
                    }

                    item2Intent.putExtra("itemTags", tagsStr);
                    startActivity(item2Intent);
                }
                if (position == 2) {
                    Intent item3Intent = new Intent(MainActivity.this, ItemActivity.class);

                    item3Intent.putExtra("itemTitle", titles[2]);
                    item3Intent.putExtra("itemImage", images[2]);
                    item3Intent.putExtra("itemCHB", currentHighestBids[2]);
                    item3Intent.putExtra("itemDesc", descriptions[2]);
                    item3Intent.putExtra("itemCHBr", currentHighestBidders[2]);

                    String tagsStr = "";
                    for(String tag : ais.items.get(2).tags){
                        tagsStr += "#" + tag + " ";
                    }

                    item3Intent.putExtra("itemTags", tagsStr);
                    startActivity(item3Intent);
                }
            }
        });
        // so item click is done now check list view


        // Search Bar


        // Countdown Clock
        textViewCountDown = findViewById(R.id.countdown);
        buttonStartPause = findViewById(R.id.countdownButton);
        buttonReset = findViewById(R.id.button_reset);

        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        // Event Button
        Button eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * EventActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                Intent eventIntent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(eventIntent);
            }
        });


        // Account Button
        Button accountButton = (Button) findViewById(R.id.loginButton);
        accountButton.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * AccountActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v) {
                Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

    }

    // Countdown Clock
    /**
     * Start countdown clock
     */
    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftInMillis;

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            /**
             * ....
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            /**
             * ....
             */
            @Override
            public void onFinish() {
                timerRunning = false;
                updateButtons();
            }
        }.start();

        timerRunning = true;
        updateButtons();
    }

    /**
     * Pause the countdown clock
     */
    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        updateButtons();
    }

    /**
     * Reset the countdown clock to original time
     */
    private void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        updateButtons();
    }

    /**
     * Update the current time on the countdown clock
     */
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeLeftFormatted);
    }

    /**
     * Update the appearance of the buttons to control the countdown
     */
    private void updateButtons() {
        if (timerRunning) {
            buttonReset.setVisibility(View.INVISIBLE);
            buttonStartPause.setText("Pause");
        } else {
            buttonStartPause.setText("Start");

            if (timeLeftInMillis < 1000) {
                buttonStartPause.setVisibility(View.INVISIBLE);
            } else {
                buttonStartPause.setVisibility(View.VISIBLE);
            }

            if (timeLeftInMillis < START_TIME_IN_MILLIS) {
                buttonReset.setVisibility(View.VISIBLE);
            } else {
                buttonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * When the app is stopped...
     */
    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", timeLeftInMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);

        editor.apply();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    /**
     * When the app is started...
     */
    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        timeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        timerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateButtons();

        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();

            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }
    }

    // For List
    class MyAdapter extends ArrayAdapter<String> {

        protected Context context;
        protected String[] titles;
        protected String[] descriptions;
        protected int[] imgs;
        protected double[] currentHighestBids;
        protected String[] currentHighestBidder;

        public MyAdapter(Context c, String[] titles, String[] descriptions, int[] images,
                         double[] currentHighestBids, String[] currentHighestBidder) {
            super(c, R.layout.row, R.id.item_title, titles);
            this.context = c;
            this.titles = titles;
            this.descriptions = descriptions;
            this.imgs = images;
            this.currentHighestBids = currentHighestBids;
            this.currentHighestBidder = currentHighestBidder;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.item_image);
            TextView myTitle = row.findViewById(R.id.item_title);
            TextView myDescription = row.findViewById(R.id.item_description);
            TextView myCHB = row.findViewById(R.id.item_CHB);
            TextView myCHBr = row.findViewById(R.id.item_CHBr);

            // now set our resources on views
            images.setImageResource(imgs[position]);
            myTitle.setText(titles[position]);
            myDescription.setText(descriptions[position]);
            String cHBStr = "Current Highest Bid: " + String.valueOf(currentHighestBids[position]);
            myCHB.setText(cHBStr);
            myCHBr.setText("Current Highest Bidder: " + currentHighestBidder[position]);

            return row;
        }
    }

}













