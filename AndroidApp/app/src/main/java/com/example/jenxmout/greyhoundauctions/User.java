package com.example.jenxmout.greyhoundauctions;

import android.util.Log;

import java.util.LinkedList;

/**
 * This is a User class that defines a GreyhoundsAuctions
 * user and includes methods for that user's behavior
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class User {

    /**
     * The first name of the bidder
     */
    protected String firstName;

    /**
     * The last name of the bidder
     */
    protected String lastName;

    /**
     * The email of the bidder
     */
    private String email;

    /**
     * The password of the bidder
     */
    private String password;

    /**
     * The flag for whether or not the user
     * is signed in
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
     * Signs a bidder up to create an account, allowing them to bid
     *
     * @param fName the first name of the bidder
     * @param lName the last name of the bidder
     * @param email the email of the bidder
     * @param pwd the password of the bidder's new account
     */
    public void signUp(String fName, String lName, String email, String pwd){
        this.firstName = fName;
        this.lastName = lName;
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
    public void logIn(String email, String pwd, String fName, String lName){
        this.email = email;
        this.password = pwd;
        this.firstName = fName;
        this.lastName = lName;
        this.signedIn = true;
    }

    /**
     * Bidder places a bid on an item
     *
     * @param amountBid the amount the bidder would like to place a bid on an item
     * @param item the item the bidder would like to bid on
     * @return true if the user is signed in, false if they are not signed in
     */
    public boolean bid(double amountBid, Item item) {
        if(signedIn) {
                //set current highest bid to user's bid
            item.currentHighestBid = amountBid;
                //user is now the current highest bidder
            item.currentHighestBidder = this.firstName + " " + this.lastName;
                //add the item to the user's itemsBidOn
            if(!this.itemsBidOn.contains(item)) {
                this.itemsBidOn.add(item);
            }
            return true;
        }
        else{
            return false;
        }
    }
}
