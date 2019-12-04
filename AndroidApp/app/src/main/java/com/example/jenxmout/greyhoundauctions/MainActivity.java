package com.example.jenxmout.greyhoundauctions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


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
    MyAdapter adapter;
    ListView listView;
    String[] titles;
    protected static AuctionItems ais = new AuctionItems();

    // CountDown Timer
    private static long START_TIME_IN_MILLIS;
    private TextView textViewCountDown;
    private CountDownTimer countDownTimer;
    private boolean timerRunning = false;
    private long timeLeftInMillis;
    private long endTime;
    private long interval = 1000;

    protected static User you;

    protected User getUser() {
        return you;
    }

    protected AuctionItems getAis() {
        return ais;
    }


    /**
     * Sets up the main screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // grab existing static user and items
        you = getUser();
        ais = getAis();

        if (you != null) {
            if (you.itemsBidOn.size() > 0) {
                you.itemsCurrentHighestBidderOn = new LinkedList<>();
                for (Item i : you.itemsBidOn) {
                    if (i.currentHighestBidder.equals(you.firstName + " " + you.lastName))
                        you.itemsCurrentHighestBidderOn.add(i);
                }
            }
        }

        //instantiate titles
        titles = new String[ais.items.size()];
        //create list view
        listView = (ListView) findViewById(R.id.list_of_items);
        //adapter for scroll view
        final MyAdapter adptr = new MyAdapter(this, titles, ais.items);
        listView.setAdapter(adptr);

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.w("items size", ais.items.size() + " items");
                Log.w("position", "index " + position);
                if (ais.items.size() >= position + 1) {
                    Intent itemIntent = new Intent(MainActivity.this, ItemActivity.class);
                    //information from each item to populate the custom item intent
                    itemIntent.putExtra("itemTitle", ais.items.get(position).title);
                    itemIntent.putExtra("itemImage", ais.items.get(position).resID);
                    itemIntent.putExtra("itemCHB", ais.items.get(position).currentHighestBid);
                    itemIntent.putExtra("itemDesc", ais.items.get(position).description);
                    itemIntent.putExtra("itemCHBr", ais.items.get(position).currentHighestBidder);
                    itemIntent.putExtra("itemMinInc", ais.items.get(position).minInc);
                    itemIntent.putExtra("itemPosition", position);

                    String tagsStr = "";
                    for (String tag : ais.items.get(position).tags) {
                        tagsStr += "#" + tag + " ";
                    }

                    itemIntent.putExtra("itemTags", tagsStr);
                    startActivity(itemIntent);
                }
            }
        });
        // so item click is done now check list view


        // Search Bar
        searchBar = (SearchView) findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adptr.filter("");
                    listView.clearTextFilter();
                } else {
                    adptr.filter(s);
                }
                return true;
            }
        });

        // Creating Countdown Clock
        FundraiserInfo fInfo = new FundraiserInfo(R.drawable.inner_harbor_info_pic, "desc",
                "2019/12/03 03:00", "2019/12/03 03:30");
        START_TIME_IN_MILLIS = fInfo.getStartTimeMillis();
        endTime = fInfo.getEndTimeMillis();
        Log.w("number of milis", "start:" + START_TIME_IN_MILLIS);

        timeLeftInMillis = endTime - START_TIME_IN_MILLIS;

        textViewCountDown = findViewById(R.id.countdown);

        MyCount counter = new MyCount(timeLeftInMillis, 1000);
        counter.start();

        /*
        if (!timerRunning) {
            startTimer();
            timerRunning = true;
        } else {
            countDownTimer.cancel();
            timerRunning = false;
        }
        */

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

        // Display User Bids Button
        Button userBidButton = (Button) findViewById(R.id.whatIBidOnButton);
        userBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (you != null) {
                    if (you.signedIn) {
                        Intent userBidIntent = new Intent(MainActivity.this, BidsActivity.class);
                        startActivity(userBidIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Log in or sign up to view!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(loginIntent);

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Log in or sign up to view!",
                            Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(loginIntent);

                }

            }
        });

        // Display User Highest Bids Button
        Button userHighestBidButton = (Button) findViewById(R.id.itemsHighestButton);
        userHighestBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (you != null) {
                    if (you.signedIn) {
                        Intent userHighestBidIntent = new Intent(MainActivity.this, HighestActivity.class);
                        startActivity(userHighestBidIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Log in or sign up to view!",
                                Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(loginIntent);

                    }
                } else {
                    Toast.makeText(MainActivity.this, "Log in or sign up to view!",
                            Toast.LENGTH_LONG).show();
                    Intent loginIntent = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(loginIntent);

                }

            }
        });
    }


    // For List
    class MyAdapter extends ArrayAdapter<String> {

        protected Context context;
        protected LinkedList<Item> items;
        protected LinkedList<Item> itemsList;

        public MyAdapter(Context c, String[] titles, LinkedList<Item> items) {
            super(c, R.layout.row, R.id.item_title, titles);
            this.context = c;
            this.items = items;
            this.itemsList = new LinkedList<>();
            this.itemsList.addAll(items);
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

            if (position < items.size()) {
                images.setImageResource(items.get(position).resID);
                myTitle.setText(items.get(position).title);
                myDescription.setText(items.get(position).description);
                myCHB.setText("$" + items.get(position).currentHighestBid + "0");
                myCHBr.setText(items.get(position).currentHighestBidder);
            }
            return row;
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            items.clear();
            if (charText.length() == 0) {
                items.addAll(itemsList);
            } else {
                for (Item item : itemsList) {
                    for (int i = 0; i < item.tags.length; i++) {
                        //check if the tags contain anything being searched
                        if (item.tags[i].toLowerCase(Locale.getDefault()).contains(charText)) {
                            //dont double add an item
                            if (!items.contains(item))
                                items.add(item);
                        }
                    }
                }
            }
            //refresh ais.items for onClick method
            ais.items = items;
            notifyDataSetChanged();
        }

    }


    // countdowntimer is an abstract class, so extend it and fill in methods
    public class MyCount extends CountDownTimer {
        MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            textViewCountDown.setText("done!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = (TimeUnit.MILLISECONDS.toDays(millis)) + " Days "
                    + (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)) + ":")
                    + (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)) + ":"
                    + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));

            textViewCountDown.setText(/*context.getString(R.string.ends_in) + " " +*/ hms);
        }
    }


    // this is not doing anything
    private void updateCountDownText() {

        Log.w("state", "in update countdown text");

        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeLeftFormatted);
    }

    //not sure if this is doing anything

    @Override
    protected void onStop() {
        super.onStop();

        Log.w("state", "in on stop");

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

    @Override
    protected void onStart() {
        super.onStart();
         Log.w("state", "in on start");

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        timeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        timerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();


        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftInMillis = endTime - System.currentTimeMillis();

            if (timeLeftInMillis < 0) {
                timeLeftInMillis = 0;
                timerRunning = false;
                updateCountDownText();
            } else {
                timerRunning = true; //
                // NEED TO FIX THIS ELSE STATEMENT!
                // counter.start();
            }
        }
    }

}
















