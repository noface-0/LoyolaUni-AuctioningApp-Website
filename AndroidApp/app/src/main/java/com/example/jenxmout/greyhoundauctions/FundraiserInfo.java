package com.example.jenxmout.greyhoundauctions;

import android.media.Image;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This is the Fundraiser Info class that...
 *
 * @author Jennifer Moutenot
 * @author Mollie Morrow
 * @author Ian Leppo
 * @author Javon Kitson
 * @version 1.0 10/21/19
 */
public class FundraiserInfo {

    /**
     * The image that represents the fundraiser
     */
    protected int fundraiserImage;

    /**
     * The description of the fundraiser event
     */
    protected String description;

    /**
     * The start time of the event
     * In format of yyyy/mm/dd hh:mm
     */
    protected String startTime;

    /**
     * The end time of the event
     * In format of yyyy/mm/dd hh:mm
     */
    protected String endTime;

    /**
     * FundraiserInfo Constructor
     * @param resID the image int red id to represent the fundraiser
     * @param desc the descripion for the fundraiser
     * @param start the start time of the fundraiser
     * @param end the end time of the fundraiser
     */
    public FundraiserInfo(int resID, String desc, String start, String end){
        this.fundraiserImage = resID;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Getter to get the end time in milliseconds
     *
     * @return endTimeMillis the date of the end time in milliseconds
     */
    public long getEndTimeMillis(){
        SimpleDateFormat endDateFormatted = new SimpleDateFormat("yyyy/MM/dd HH:mm a", Locale.ENGLISH);
        endDateFormatted.setTimeZone(TimeZone.getTimeZone("ET"));
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
     * Getter to get the start time in milliseconds
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
