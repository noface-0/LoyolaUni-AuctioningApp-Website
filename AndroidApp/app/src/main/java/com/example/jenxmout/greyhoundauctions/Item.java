package com.example.jenxmout.greyhoundauctions;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;

import java.util.LinkedList;

/**
 * This is a Item class...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class Item {

    /**
     * The title of the item
     */
    protected String title;

    /**
     * The description of the item
     */
    protected String description;

    /**
     * The resID that represents the image of the item
     */
    protected Bitmap resID;

    /**
     * The minimum increment you can set on an item as a bidder
     */
    protected double minInc;

    /**
     * The list of tags that pertain to an item
     */
    protected String [] tags;

    /**
     * The value of the current highest bid on an item
     */
    protected double currentHighestBid;

    /**
     * The name of the current highest bidder on an item
     */
    protected String currentHighestBidder;

    /**
     * The list of the top two auto-bid users for a particular item.
     * The top two have the highest max bid
     */
    protected User [] autoBidUsers;

    /**
     * The list of the top two auto-bid maxs for a particular item.
     * The top two have the highest max bid
     */
    protected double [] autoBidMax;

    /**
     * Item Constructor
     * @param title the title of the item
     * @param desc the description of the item
     * @param resID the image bitmap of the item
     * @param minInc the minimum increment the item can be auto-bidded
     * @param tags the list of tags that pertain to an item
     * @param currentHighestBid the current highest bid of an item
     */
    public Item(String title, String desc, double minInc, String [] tags,
                double currentHighestBid, Bitmap resID, String currentHighestBidder){
        this.title = title;
        this.description = desc;
        this.resID = resID;
        this.minInc = minInc;
        this.tags = tags;
        this.currentHighestBid = currentHighestBid;
        this.currentHighestBidder = currentHighestBidder;

        this.autoBidUsers = new User[] {null, null};
        this.autoBidMax = new double[] {0.0, 0.0};

    }

    /**
     * This method ...
     *
     * @param user
     * @param max
     * @return
     */
    protected boolean addAutoBid(User user, double max){
        if(autoBidUsers[0] == null){
            autoBidUsers[0] = user;
            autoBidMax[0] = max;

            this.currentHighestBidder = user.firstName + " " + user.lastName;
            this.currentHighestBid = this.currentHighestBid + this.minInc;
            return true;
        }
        else if(autoBidUsers[1] == null){
            autoBidUsers[1] = user;
            autoBidMax[1] = max;
            return true;
        }
        else{
            int i = this.leastMaxIndex();
            if(max > autoBidMax[i]){
                autoBidUsers[i] = user;
                autoBidMax[i] = max;
                return true;
            }
            else
                return false;
        }
    }

    /**
     * This method...
     */
    protected void updateAutoBid(){
        Log.w("IN AUTOBID", "top of method");
        if(autoBidUsers[0] == null){
            Log.w("IN AUTOBID", "no users in list");
            return;
        }
        else if(autoBidUsers[1] == null){
            Log.w("IN AUTOBID", "1 user in list");
            if(!this.currentHighestBidder.equals(autoBidUsers[0].firstName + " " + autoBidUsers[0].lastName)) {
                Log.w("IN AUTOBID", "1 user in list is already CHBr");
                this.currentHighestBid += minInc;
                this.currentHighestBidder = autoBidUsers[0].firstName + " " + autoBidUsers[0].lastName;
            }
            else
                return;
        }
        else {
            Log.w("IN AUTOBID", "2 users in list");
            double sum = this.autoBidMax[this.leastMaxIndex()] + this.minInc;
            if (sum <= this.autoBidMax[this.greatestMaxIndex()]) {
                this.currentHighestBid = sum;
                this.currentHighestBidder = autoBidUsers[this.greatestMaxIndex()].firstName + " "
                        + autoBidUsers[this.greatestMaxIndex()].lastName;
            } else {
                this.currentHighestBid = autoBidMax[this.greatestMaxIndex()];
                this.currentHighestBidder = autoBidUsers[this.greatestMaxIndex()].firstName + " "
                        + autoBidUsers[this.greatestMaxIndex()].lastName;
            }
        }
    }

    /**
     * This method...
     *
     * @return 1 or 0 if...
     */
    protected int leastMaxIndex(){
        if(autoBidMax[0] > autoBidMax[1])
            return 1;
        else
            return 0;
    }

    /**
     * This method...
     *
     * @return 1 or 0 if...
     */
    protected int greatestMaxIndex(){
        if(autoBidMax[0] > autoBidMax[1])
            return 0;
        else
            return 1;
    }
}
