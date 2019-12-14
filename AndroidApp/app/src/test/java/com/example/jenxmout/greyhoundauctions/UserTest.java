package com.example.jenxmout.greyhoundauctions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user;
    Item item;

    @Before
    public void setUp() throws Exception {
        user = new User();
        String [] tags = {"ipad", "tech", "apple"};
        item = new Item("iPad Pro", "Brand new 2019 iPad Pro with stylus.", 25.00,
                tags, 100.00,"Jen Moutenot");
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void signUpFirstNameTest() {
        user.signUp("Mollie", "Morrow", "mfmorrow@loyola.edu", "12345");
        assertEquals(user.firstName, "Mollie");
    }

    @Test
    public void signUpLastNameTest() {
        user.signUp("Mollie", "Morrow", "mfmorrow@loyola.edu", "12345");
        assertEquals(user.lastName, "Morrow");
    }

    @Test
    public void signUpLoggedInTest() {
        user.signUp("Mollie", "Morrow", "mfmorrow@loyola.edu", "12345");
        assertTrue(user.signedIn);
    }

    @Test
    public void logInFirstNameTest() {
        user.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        assertEquals(user.firstName, "Mollie");
    }

    @Test
    public void logInLastNameTest() {
        user.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        assertEquals(user.lastName, "Morrow");
    }

    @Test
    public void logInLoggedInTest() {
        user.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        assertTrue(user.signedIn);
    }

    @Test
    public void bidCHBTest() {
        user.bid(150.00, item);
        assertEquals(item.currentHighestBid, 150.00, 0.00);

    }

    @Test
    public void bidCHBrTest() {
        user.bid(150.00, item);
        assertEquals(item.currentHighestBidder, "Jen Moutenot");

    }
}