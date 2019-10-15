package com.example.jenxmout.greyhoundauctions;

import java.util.LinkedList;

/**
 * This is a User class
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Javon Kitson
 * @author Ian Leppo
 * @version 1.0 10/15/19
 */
public class User {

    /**
     * The name of the bidder
     */
    protected String name;

    /**
     * The email of the bidder
     */
    protected String email;
    private String password;
    protected boolean signedIn;

    protected LinkedList<Item> itemsBidOn;

    protected LinkedList<Item> itemsCurrentHighestBidderOn;

    public boolean bid(int amountBid) {
        return true;
    }

    public boolean autoBid() {
        return true;
    }

}
