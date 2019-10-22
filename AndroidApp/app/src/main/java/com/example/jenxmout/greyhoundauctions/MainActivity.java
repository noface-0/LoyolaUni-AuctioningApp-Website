package com.example.jenxmout.greyhoundauctions;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.LinkedList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent eventIntent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(eventIntent);
            }
        });

        Button accountButton = (Button) findViewById(R.id.loginButton);
        accountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

        /**
        ImageButton itemButton = (ImageButton) findViewById(R.id.acutionItem1Button);
        itemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent itemIntent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(itemIntent);
            }
        });
         */

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
}
