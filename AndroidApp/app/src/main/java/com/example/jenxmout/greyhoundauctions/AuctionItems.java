package com.example.jenxmout.greyhoundauctions;

import java.util.LinkedList;

/**
 * This is an Auction Items class that
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Javon Kitson
 * @author Ian Leppo
 * @version 1.0 10/15/19
 */
public class AuctionItems {

    /**
     * The list of items that make up the auction
     */
    protected LinkedList<Item> items;

    /**
     * AuctionItems Constructor
     */
    public AuctionItems(){

        //create items (first sprint)
        String[] item1Tags = {"ipad", "apple", "tech"};
        final Item item1 = new Item("iPad",
                "This is a brand new iPad Pro, it has 64GB memory and was donated by Campus " +
                        "Ministry.", R.drawable.auction_item_ipad,
                10, item1Tags, 1000.00);
        String[] item2Tags = {"golf", "sports", "fathers day"};
        Item item2 = new Item("Golf Clubs", "Looking for a Father's Day gift? We've got" +
                " the gift for you! This is a six piece golf club set, bag included!",
                R.drawable.auction_item_golf_clubs, 5, item2Tags, 50.00);
        String[] item3Tags = {"basketball", "sports", "fathers day", "bball"};
        Item item3 = new Item("Basketball Tickets", "Two tickets to the Philadelphia " +
                "76ers vs the New Orleans Pelicans.", R.drawable.auction_item_tickets, 5,
                item3Tags, 30.00);

        //create auctionitems object with items created
        this.items = new LinkedList<>();
        items.add(item1);
        items.add(item3);
        items.add(item2);
    }

    //protected LinkedList<Item> sortByTag(String tag) {

    //}
}
