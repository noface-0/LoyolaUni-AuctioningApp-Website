package com.example.jenxmout.greyhoundauctions;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * This is a Item class that defines an item
 * to be used for the GreyhoundsAuctions event
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
     * The resID that represents the bitmap of the image
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
     * The top two have the highest max bids
     */
    protected User [] autoBidUsers;

    /**
     * The list of the top two auto-bid values for a particular item.
     * The top two have the highest max bids
     */
    protected double [] autoBidMax;

    /**
     * Item Constructor
     * @param title the title of the item
     * @param desc the description of the item
     * @param resID the image bitmap of the item
     * @param minInc the minimum increment of the item's next bid
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


    public Item(String title, String desc, double minInc, String [] tags,
                double currentHighestBid, String currentHighestBidder){
        this.title = title;
        this.description = desc;
        this.minInc = minInc;
        this.tags = tags;
        this.currentHighestBid = currentHighestBid;
        this.currentHighestBidder = currentHighestBidder;
        this.autoBidUsers = new User[] {null, null};
        this.autoBidMax = new double[] {0.0, 0.0};
    }

    /**
     * This method is called when a user auto-bids
     * on an item.
     * The user and their max bid are added to the auto-bid lists
     * if there are no other users or one other user in the auto-bid lists
     * or the users max bid is higher than the values in the auto-bid
     * max list
     *
     * @param user the user placing an auto-bid
     * @param max the user's max bid value on an item
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
     * This method, when called, evaluates the current
     * highest bid in regard to the two current highest
     * max bids.
     * This method also updates the current highest bid and
     * current highest bidder according to the highest
     * auto-bid request.
     */
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

    /**
     * This method returns the index of
     * the smaller value of the two max bid values
     * in the list
     *
     * @return 1 if the smallest value is in the second index
     *         0 if the smallest value is in the first index
     */
    protected int leastMaxIndex(){
        if(autoBidMax[0] > autoBidMax[1])
            return 1;
        else
            return 0;
    }

    /**
     * This method returns the index of
     * the greatest value of the two max bid values
     * in the list
     *
     * @return 1 if the greatest value is in the second index
     *         0 if the greatest value is in the first index
     */
    protected int greatestMaxIndex(){
        if(autoBidMax[0] > autoBidMax[1])
            return 0;
        else
            return 1;
    }
}
