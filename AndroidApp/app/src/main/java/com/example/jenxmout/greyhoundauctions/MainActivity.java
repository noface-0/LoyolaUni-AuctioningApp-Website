package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    // Search Bar
    SearchView searchBar;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

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

        // Search Bar
        searchBar = (SearchView)findViewById(R.id.search_bar);
        listView = (ListView)findViewById(R.id.list_of_items);

        list = new ArrayList<String>();

        list.add("Item 1");
        list.add("Item 2");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {

                adapter.getFilter().filter(search);
                return false;
            }
        });

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
        eventButton.setOnClickListener(new View.OnClickListener(){

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * EventActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                Intent eventIntent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(eventIntent);
            }
        });


        // Account Button
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
                Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

        // Item Buttons
        ImageButton item1Button = (ImageButton) findViewById(R.id.auctionItem1Button);
        item1Button.setOnClickListener(new View.OnClickListener(){

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * ItemActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                Intent itemIntent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(itemIntent);
            }
        });

        ImageButton item2Button = (ImageButton) findViewById(R.id.auctionItem2Button);
        item2Button.setOnClickListener(new View.OnClickListener(){

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * ItemActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                Intent itemIntent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(itemIntent);
            }
        });

        ImageButton item3Button = (ImageButton) findViewById(R.id.auctionItem3Button);
        item3Button.setOnClickListener(new View.OnClickListener(){

            /**
             * This method sets a click listener for the button in the UI
             * When clicked, the user is taken to the next view
             * ItemActivity
             *
             * @param v the view of the current state
             */
            @Override
            public void onClick(View v){
                Intent itemIntent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(itemIntent);
            }
        });


        // Loading up Items
        String auctionItemStr = "auction_item_ipad";
        int itemOneResID = getResources().getIdentifier(auctionItemStr, "drawable", getPackageName());
        LinkedList<String> ipadTags = new LinkedList<>();
        ipadTags.add("iPad");
        ipadTags.add("technology");
        ipadTags.add("Apple");
        ipadTags.add("tablet");
        Item iPad = new Item("iPad", "This is a 2019 iPad Pro with 64GB memory.",
                itemOneResID, 5.00, ipadTags, 150.00);

        ImageButton auctionItemButton = new ImageButton(this);

    }


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

}
