package com.example.jenxmout.greyhoundauctions;

import android.graphics.Bitmap;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This is the Fundraiser Info class that defines the
 * silent auction event and gives functionality
 * to the countdown clock
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @version 1.0 12/15/19
 */
public class FundraiserInfo {

    /**
     * The bitmap of the image
     * that represents the fundraiser
     */
    protected Bitmap fundraiserImage;

    /**
     * The description of the fundraiser event
     */
    protected String description;

    /**
     * The start time of the event
     * In format of yyyy.MM.dd HH:mm:ss z
     */
    protected String startTime;

    /**
     * The end time of the event
     * In format of yyyy.MM.dd HH:mm:ss z
     */
    protected String endTime;

    /**
     * FundraiserInfo Constructor
     * @param resID the bitmap of the image to represent the fundraiser
     * @param desc the description for the fundraiser
     * @param start the start time of the fundraiser
     * @param end the end time of the fundraiser
     */
    public FundraiserInfo(Bitmap resID, String desc, String start, String end){
        this.fundraiserImage = resID;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Converts end time to milliseconds for the countdown clock
     *
     * @return endTimeMillis the date of the end time in milliseconds
     */
    public long getEndTimeMillis(){
        SimpleDateFormat endDateFormatted = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z", Locale.US);
        endDateFormatted.setTimeZone(TimeZone.getTimeZone("EST"));
        long endTimeMillis = System.currentTimeMillis();
        try {
            Date endDateObj = endDateFormatted.parse(endTime);
            endTimeMillis = endDateObj.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.w("end time millis", String.valueOf(endTimeMillis));

        return endTimeMillis;

    }

    /**
     * Converts start time to milliseconds for the countdown clock
     *
     * @return startTimeMillis the date of the start time in milliseconds
     */
    public long getStartTimeMillis(){
        SimpleDateFormat startDateFormatted = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z", Locale.US);
        startDateFormatted.setTimeZone(TimeZone.getTimeZone("EST"));
        long startTimeMillis = System.currentTimeMillis();
        try {
            Date startDateObj = startDateFormatted.parse(startTime);
            startTimeMillis = startDateObj.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.w("start time millis", String.valueOf(startTimeMillis));
        return startTimeMillis;
    }
}
