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

import java.util.LinkedList;
import java.util.Locale;

public class BidsActivity extends AppCompatActivity{
    SearchView searchBar;
    ListView listView;
    String[] titles;
    
    /**
     * Sets up the bids screen view
     *
     * @param savedInstanceState the reference to a Bundle object that is passed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w("open bids view", "true");
        
        this.titles = new String[MainActivity.you.itemsBidOn.size()];
        //instantiate titles
        for(Item i : MainActivity.you.itemsBidOn){
            for(int j = 0; j < MainActivity.you.itemsBidOn.size(); j++){
                titles[j] = i.title;   
            }
        }
        
        //create list view
        listView = (ListView) findViewById(R.id.list_of_items);
        //adapter for scroll view
        final BidsActivity.MyAdapter adptr = new BidsActivity.MyAdapter(this, titles, MainActivity.you.itemsBidOn);
        listView.setAdapter(adptr);

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.w("items size", MainActivity.you.itemsBidOn.size() + " items");
                Log.w("position", "index " + position);
                if (MainActivity.you.itemsBidOn.size() >= position+1) {
                    Intent itemIntent = new Intent(BidsActivity.this, ItemActivity.class);
                    //information from each item to populate the custom item intent
                    itemIntent.putExtra("itemTitle", MainActivity.you.itemsBidOn.get(position).title);
                    itemIntent.putExtra("itemImage", MainActivity.you.itemsBidOn.get(position).resID);
                    itemIntent.putExtra("itemCHB", MainActivity.you.itemsBidOn.get(position).currentHighestBid);
                    itemIntent.putExtra("itemDesc", MainActivity.you.itemsBidOn.get(position).description);
                    itemIntent.putExtra("itemCHBr", MainActivity.you.itemsBidOn.get(position).currentHighestBidder);
                    itemIntent.putExtra("itemMinInc", MainActivity.you.itemsBidOn.get(position).minInc);
                    itemIntent.putExtra("itemPosition", position);

                    String tagsStr = "";
                    for (String tag : MainActivity.you.itemsBidOn.get(position).tags) {
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
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adptr.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adptr.filter(s);
                }
                return true;
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
                Intent eventIntent = new Intent(BidsActivity.this, EventActivity.class);
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
                Intent accountIntent = new Intent(BidsActivity.this, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

        //Home Button
        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(BidsActivity.this, MainActivity.class);
                startActivity(homeIntent);
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

            if(position < items.size()) {
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
                    for(int i = 0; i < item.tags.length; i++) {
                        //check if the tags contain anything being searched
                        if(item.tags[i].toLowerCase(Locale.getDefault()).contains(charText)) {
                            //dont double add an item
                            if(!items.contains(item))
                                items.add(item);
                        }
                    }
                }
            }
            //refresh ais.items for onClick method
            MainActivity.you.itemsBidOn = items;
            notifyDataSetChanged();
        }

    }

}

