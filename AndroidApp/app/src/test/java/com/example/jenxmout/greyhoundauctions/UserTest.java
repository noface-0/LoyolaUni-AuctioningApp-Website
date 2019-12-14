package com.example.jenxmout.greyhoundauctions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This is a UserTest class to test the methods
 * of the User class
 *
 * @author Mollie Morrow
 * @version 1.0 12/14/19
 */
public class UserTest {

    User user;
    Item item;

    @Before
    public void setUp() throws Exception {
        user = new User();
        String [] tags = {"ipad", "tech", "apple"};
        item = new Item("iPad Pro", "Brand new 2019 iPad Pro with stylus.", 25.00,
                tags, 100.00,"");
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
        user.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        user.bid(150.00, item);
        assertEquals(150.00, item.currentHighestBid, 0.00);

    }

    @Test
    public void bidCHBrTest() {
        user.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        user.bid(150.00, item);
        assertEquals("Mollie Morrow", item.currentHighestBidder);

    }
    @Test
    public void bidCHBTest2() {
        user.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        user.bid(250.00, item);
        assertEquals(250.00, item.currentHighestBid, 0.00);

    }

    @Test
    public void bidCHBrTest2() {
        user.logIn("jmoutenot@loyola.edu", "54321","Jen", "Moutenot");
        user.bid(250.00, item);
        assertEquals("Jen Moutenot", item.currentHighestBidder);

    }

    @Test
    public void bidCHBTest3() {
        user.bid(125.00, item);
        assertEquals(100.00, item.currentHighestBid, 0.00);

    }

    @Test
    public void bidCHBrTest3() {
        user.bid(50.00, item);
        assertEquals("", item.currentHighestBidder);

    }

    @Test
    public void bidCHBTest4() {
        user.signUp("Jen", "Moutenot","jmoutenot@loyola.edu", "54321");
        user.bid(item.currentHighestBid+item.minInc, item);
        assertEquals(125.00, item.currentHighestBid, 0.00);

    }

    @Test
    public void bidCHBrTest4() {
        user.signUp("Javon", "Kitson", "jckitson@loyola.edu", "76549");
        user.bid(50.00, item);
        assertEquals("Javon Kitson", item.currentHighestBidder);

    }
}