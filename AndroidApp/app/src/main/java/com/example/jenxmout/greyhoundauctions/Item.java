package com.example.jenxmout.greyhoundauctions;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.LinkedList;

/**
 * This is a Item class...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Javon Kitson
 * @author Ian Leppo
 * @version 1.0 10/15/19
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

    protected User [] autoBidUsers;

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
                double currentHighestBid, Bitmap resID){
        this.title = title;
        this.description = desc;
        this.resID = resID;
        this.minInc = minInc;
        this.tags = tags;
        this.currentHighestBid = currentHighestBid;
        this.currentHighestBidder = "";

        this.autoBidUsers = new User[] {null, null};
        this.autoBidMax = new double[] {0.0, 0.0};

    }

    protected boolean addAutoBid(User user, double max){
        if(autoBidUsers[0] == null){
            autoBidUsers[0] = user;
            autoBidMax[0] = max;
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

    protected void updateAutoBid(){
        if(autoBidUsers[0] == null){
            return;
        }
        else if(autoBidUsers[1] == null){
            if(!this.currentHighestBidder.equals(autoBidUsers[0].firstName + " " + autoBidUsers[0].lastName)) {
                this.currentHighestBid += minInc;
                this.currentHighestBidder = autoBidUsers[0].firstName + " " + autoBidUsers[0].lastName;
            }
            else
                return;
        }
        else {
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


    protected int leastMaxIndex(){
        if(autoBidMax[0] > autoBidMax[1])
            return 1;
        else
            return 0;
    }

    protected int greatestMaxIndex(){
        if(autoBidMax[0] > autoBidMax[1])
            return 0;
        else
            return 1;
    }
}
