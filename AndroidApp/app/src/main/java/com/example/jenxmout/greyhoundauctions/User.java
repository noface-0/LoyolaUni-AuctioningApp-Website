package com.example.jenxmout.greyhoundauctions;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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
public class User{

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
    public void logIn(String email, String pwd){
        this.email = email;
        this.password = pwd;
        this.signedIn = true;
    }

    public void logOut(){

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
            Log.w("signedIn", "true");
                //set current highest bid to user's bid
            item.currentHighestBid = amountBid;
                //user is now the current highest bidder
            item.currentHighestBidder = this.firstName + " " + this.lastName;
                //add the item to the user's itemsBidOn
            this.itemsBidOn.add(item);
            return true;
        }
        else{
            Log.w("signedIn", "false");
            return false;
        }
    }

    /**
     * Bidder places an auto-bid on an item
     *
     * @param maxBid the maximum amount the bidder would like to increment their bid to
     * @param item the item the bidder would like to bid on
     * @return true if...
     */
    public boolean autoBid(double maxBid, Item item) {
        if(signedIn) {
            //while the user is not the current highest bidder and the current highest bid is less
            //than the user's max bid, bid the current highest bid + the user's increment
            while (!item.currentHighestBidder.equals(this.firstName + " " + this.lastName) && item.currentHighestBid < maxBid) {
                this.bid(item.currentHighestBid + item.minInc, item);
            }
            return true;
        }
        else{
            //navigate to log-in page
            return false;
        }
    }

}
