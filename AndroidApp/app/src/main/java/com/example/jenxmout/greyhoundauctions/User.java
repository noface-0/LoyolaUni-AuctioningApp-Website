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

    public User(){
        this.signedIn = false;
        this.itemsBidOn = new LinkedList<Item>();
        this.itemsCurrentHighestBidderOn = new LinkedList<Item>();
    }

    public void signUp(String name, String email, String pwd){
        this.name = name;
        this.email = email;
        this.password = pwd;
        this.signedIn = true;
    }

    public void logIn(String email, String pwd){
        //if the email matches email in bd and pwd matches recorded pwd for that email,
        //set name, email, pwd and signed in flag for user
    }

    public boolean bid(int amountBid, Item item) {
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

    public boolean autoBid(int inc, int maxBid, Item item) {
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
