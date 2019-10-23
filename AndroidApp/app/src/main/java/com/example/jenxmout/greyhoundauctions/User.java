package com.example.jenxmout.greyhoundauctions;

import java.util.LinkedList;

/**
 * This is a User class that
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

    /**
     * The password of the bidder
     */
    private String password;

    /**
     * Whether the bidder has signed in or not
     */
    protected boolean signedIn;

    /**
     * The list of items the bidder has bid on
     */
    protected LinkedList<Item> itemsBidOn;

    /**
     * The list of items the bidder is currently the highest on
     */
    protected LinkedList<Item> itemsCurrentHighestBidderOn;

    /**
     * User Constructor
     */
    public User(){
        this.signedIn = false;
        this.itemsBidOn = new LinkedList<Item>();
        this.itemsCurrentHighestBidderOn = new LinkedList<Item>();
    }

    /**
     * Signs a bidder create an account, allowing them to bid
     *
     * @param name the name of the bidder
     * @param email the email of the bidder
     * @param pwd the password of the bidder's new account
     */
    public void signUp(String name, String email, String pwd){
        this.name = name;
        this.email = email;
        this.password = pwd;
        this.signedIn = true;
    }

    /**
     * Logs a bidder into their account, allowing them to bid
     *
     * @param email the email of the bidder
     * @param pwd the password of the bidder's account
     */
    public void logIn(String email, String pwd){
        //if the email matches email in bd and pwd matches recorded pwd for that email,
        //set name, email, pwd and signed in flag for user
    }

    /**
     * Bidder places a bid on an item
     *
     * @param amountBid the amount the bidder would like to place a bid on an item
     * @param item the item the bidder would like to bid on
     * @return true if the ...
     */
    public boolean bid(double amountBid, Item item) {
        if(signedIn) {
            //if the bid is less than the current highest bid
            if (item.currentHighestBid > amountBid)
                return false;
                //if the bid is not the minimum increment more than the current highest bid
            else if (amountBid - item.currentHighestBid < item.minInc)
                return false;
            else {
                //set current highest bid to user's bid
                item.currentHighestBid = amountBid;
                //user is now the current highest bidder
                item.currentHighestBidder = this.name;
                //add the item to the user's itemsBidOn
                this.itemsBidOn.add(item);
                return true;
            }
        }
        else{
            //navigate to log-in page
            return false;
        }
    }

    /**
     * Bidder places an auto-bid on an item
     *
     * @param inc the amount the bidder would like to increment their bid by on an item
     * @param maxBid the maximum amount the bidder would like to increment their bid to
     * @param item the item the bidder would like to bid on
     * @return true if...
     */
    public boolean autoBid(double inc, double maxBid, Item item) {
        if(signedIn) {
            //if the bid is not the minimum increment more than the current highest bid
            if (inc < item.minInc)
                return false;
            //while the user is not the current highest bidder and the current highest bid is less
            //than the user's max bid, bid the current highest bid + the user's increment
            while (!item.currentHighestBidder.equals(this.name) && item.currentHighestBid < maxBid) {
                this.bid(item.currentHighestBid + inc, item);
            }
            return true;
        }
        else{
            //navigate to log-in page
            return false;
        }
    }

}
