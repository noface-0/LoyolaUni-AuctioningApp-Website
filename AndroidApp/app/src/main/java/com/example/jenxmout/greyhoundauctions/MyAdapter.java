package com.example.jenxmout.greyhoundauctions;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyAdapter extends ArrayAdapter<String> {

    protected Context context;
    protected String[] titles;
    protected String[] descriptions;
    protected int[] images;
    protected double[] currentHighestBids;
    protected String[] currentHighestBidder;

    public MyAdapter(Context c, String[] titles, String[] descriptions, int[] images,
                     double[] currentHighestBids, String[] currentHighestBidder){
        super(c, R.layout.row, R.id.item_title, titles);
        this.context = c;
        this.titles = titles;
        this.descriptions = descriptions;
        this.images = images;
        this.currentHighestBids = currentHighestBids;
        this.currentHighestBidder = currentHighestBidder;
    }


}
