package com.example.jenxmout.greyhoundauctions;

import android.media.Image;

import java.util.LinkedList;

/**
 * This is a Item class
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
    protected int resID;

    /**
     * The minimum increment you can set on an item as a bidder
     */
    protected double minInc;

    /**
     * The list of tags that pertain to an item
     */
    protected LinkedList<String> tags;

    /**
     * The value of the current highest bid on an item
     */
    protected double currentHighestBid;

    /**
     * The name of the current highest bidder on an item
     */
    protected String currentHighestBidder;

    /**
     * Item Constructor
     * @param title the title of the item
     * @param desc the description of the item
     * @param resID the image reference of the item
     * @param minInc the minimum increment the item can be auto-bidded
     * @param tags the list of tags that pertain to an item
     * @param currentHighestBid the current highest bid of an item
     */
    public Item(String title, String desc, int resID, double minInc, LinkedList<String> tags,
                double currentHighestBid){
        this.title = title;
        this.description = desc;
        this.resID = resID;
        this.minInc = minInc;
        this.tags = tags;
        this.currentHighestBid = currentHighestBid;
        this.currentHighestBidder = "";
    }
}
