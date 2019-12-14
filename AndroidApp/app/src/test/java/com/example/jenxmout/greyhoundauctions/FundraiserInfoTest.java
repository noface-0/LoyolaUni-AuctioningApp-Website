package com.example.jenxmout.greyhoundauctions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FundraiserInfoTest {

    FundraiserInfo finfo;

    @Before
    public void setUp() throws Exception {
        FundraiserInfo finfo = new FundraiserInfo("this is an event","2019.12.10 12:00:00 EST","2019.12.20 12:00:00 EST");
    }

    @After
    public void tearDown() throws Exception {
        finfo = null;
    }

    @Test
    public void getEndTimeMillis() {
        long endMillis = finfo.getEndTimeMillis();
        assertEquals(endMillis, 1575997200000 - (5*3600);
    }

    @Test
    public void getStartTimeMillis() {
        long startMillis = finfo.getStartTimeMillis();
        assertEquals(startMillis, 1576861200000 - (5*3600) );
    }
}