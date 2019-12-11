package com.example.jenxmout.greyhoundauctions;

import java.util.LinkedList;

/**
 * This is an Auction Items class that...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class AuctionItems {

    /**
     * The list of items that make up the auction
     */
    protected LinkedList<Item> items;

    /**
     * AuctionItems Constructor
     */
    public AuctionItems() {
        //create AuctionItems object with items created
        this.items = new LinkedList<>();
    }
}
