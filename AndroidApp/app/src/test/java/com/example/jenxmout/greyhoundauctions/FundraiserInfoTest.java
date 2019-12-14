package com.example.jenxmout.greyhoundauctions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.example.jenxmout.greyhoundauctions.MainActivity.fInfo;
import static org.junit.Assert.*;

/**
 * This is a JUnit test class that
 * tests the Fundraiser Info class and it's
 * methods/behaviors
 *
 * @author Ian Leppo
 * @version 1.0 12/15/19
 */
public class FundraiserInfoTest {

    FundraiserInfo finfo;
    FundraiserInfo fInfo2;


    @Before
    public void setUp() throws Exception {
        finfo = new FundraiserInfo("this is an event",
                "2019.12.10 12:00:00 EST","2019.12.20 12:00:00 EST");

        fInfo2 = new FundraiserInfo("this is an event",
                "2019.12.15 12:00:00 EST","2019.12.25 12:00:00 EST");
    }

    @After
    public void tearDown() throws Exception {
        finfo = null;
    }

    @Test
    public void getEndTimeMillis() {
        if (finfo != null) {
            long endMillis = finfo.getEndTimeMillis();
            assertEquals(endMillis, (1576861200000L));
        }
    }

    @Test
    public void getEndTimeMillis2() {
        if (fInfo2 != null) {
            long endMillis = fInfo2.getEndTimeMillis();
            assertEquals(endMillis, (1577293200000L));
        }
    }

    @Test
    public void getDescription() {
        if (finfo != null) {
            String description = finfo.description;
            assertEquals("this is an event", description);
        }
    }

    @Test
    public void getDescription2() {
        if (fInfo2 != null) {
            String description = finfo.description;
            assertEquals("this is an event", description);
        }
    }

    @Test
    public void getStartString() {
        String startTime = finfo.startTime;
        assertEquals("2019.12.10 12:00:00 EST", startTime);
    }

    @Test
    public void getStartString2() {
        String startTime = fInfo2.startTime;
        assertEquals("2019.12.15 12:00:00 EST", startTime);
    }

    @Test
    public void getEndString() {
        String endTime = finfo.endTime;
        assertEquals("2019.12.20 12:00:00 EST", endTime);
        assertNotEquals("2019.19.20 12:01:00 EST", endTime);
    }

    @Test
    public void getEndString2() {
        String endTime = fInfo2.endTime;
        assertEquals("2019.12.25 12:00:00 EST", endTime);
        assertNotEquals("2019.19.20 12:01:00 EST", endTime);
    }

    @Test
    public void getStartTimeMillis() {
        if (finfo != null) {
            long startMillis = finfo.getStartTimeMillis();
            assertEquals(startMillis, (1575997200000L ));
        }
    }

    @Test
    public void getStartTimeMillis2() {
        if (fInfo2 != null) {
            long startMillis = fInfo2.getStartTimeMillis();
            assertEquals(startMillis, 1576429200000L);
        }
    }
}