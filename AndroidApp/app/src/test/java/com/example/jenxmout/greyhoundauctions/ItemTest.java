package com.example.jenxmout.greyhoundauctions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a JUnit test class that
 * tests the Item class and it's
 * methods/behaviors
 *
 * @author Jen Moutenot
 * @version 1.0 12/15/19
 */
public class ItemTest {

    Item item1;
    Item item2;
    Item item3;
    Item item4;

    User user1;
    User user2;

    @Before
    public void setUp() throws Exception {

        user1 = new User();
        user1.logIn("mfmorrow@loyola.edu", "12345","Mollie", "Morrow");
        user2 = new User();
        user2.signUp("Jennifer", "Moutenot", "jenmoutenot4@gmail.com",
                "12345");

        String[] tags = {"sports", "adventure", "outdoors"};
        item1 = new Item("Kayak", "This is a kayak.", 5.00, tags,
                20.00, "Jen Moutenot");
        item2 = new Item("Bowl", "This is a bowl.", 10.00, tags,
                40.00, "Herve Franceschi");
        item3 = new Item("Phone", "This is a phone.", 15.00, tags,
                60.00, "Javon Kitson");
        item4 = new Item("Computer", "This is a computer.", 20.00, tags,
                80.00, "Mollie Morrow");
    }

    @After
    public void tearDown() throws Exception {
        user1 = null;
        user2 = null;

        item1 = null;
        item2 = null;
        item3 = null;
        item4 = null;
    }

    @Test
    public void addAutoBidTestOne() {
        item1.addAutoBid(user1, 40.00);
        assertEquals(25.00, item1.currentHighestBid, 0.00);
        assertNotEquals(40.00, item1.currentHighestBid, 0.00);
        assertEquals("Mollie Morrow", item1.currentHighestBidder);
        assertNotEquals("Jennifer Moutenot", item1.currentHighestBidder);
    }

    @Test
    public void addAutoBidTestTwo() {
        item2.addAutoBid(user2, 60.00);
        assertEquals(50.00, item2.currentHighestBid, 0.00);
        assertNotEquals(60.00, item2.currentHighestBid, 0.00);
        assertEquals("Jennifer Moutenot", item2.currentHighestBidder);
        assertNotEquals("Mollie Morrow", item2.currentHighestBidder);
    }

    @Test
    public void addAutoBidTestThree() {
        item3.addAutoBid(user1, 80.00);
        assertEquals(75.00, item3.currentHighestBid, 0.00);
        assertNotEquals(80.00, item3.currentHighestBid, 0.00);
        assertEquals("Mollie Morrow", item3.currentHighestBidder);
        assertNotEquals("Jennifer Moutenot", item3.currentHighestBidder);
    }

    @Test
    public void addAutoBidTestFour() {
        item4.addAutoBid(user2, 200.00);
        assertEquals(100.00, item4.currentHighestBid, 0.00);
        assertNotEquals(200.00, item4.currentHighestBid, 0.00);
        assertEquals("Jennifer Moutenot", item4.currentHighestBidder);
        assertNotEquals("Mollie Morrow", item4.currentHighestBidder);
    }

    @Test
    public void updateAutoBidOne() {
        item1.addAutoBid(user1, 40.00);
        item1.updateAutoBid();
        assertEquals(25.00, item1.currentHighestBid, 0.00);
    }

    @Test
    public void updateAutoBidTwo() {
        item2.addAutoBid(user2, 60.00);
        item2.updateAutoBid();
        assertEquals(50.00, item2.currentHighestBid, 0.00);
        assertEquals("Jennifer Moutenot", item2.currentHighestBidder);
    }

    @Test
    public void updateAutoBidThree() {
        item3.addAutoBid(user1, 80.00);
        item3.updateAutoBid();
        assertEquals(75.00, item3.currentHighestBid, 0.00);
        assertEquals("Mollie Morrow", item3.currentHighestBidder);
    }

    @Test
    public void updateAutoBidFour() {
        item4.addAutoBid(user2, 200.00);
        item4.updateAutoBid();
        assertEquals(100.00, item4.currentHighestBid, 0.00);
        assertEquals("Jennifer Moutenot", item4.currentHighestBidder);
    }


    @Test
    public void leastMaxIndex() {
        item1.addAutoBid(user1, 40.00);
        item1.addAutoBid(user2, 20.00);
        int index = item1.leastMaxIndex();
        double bidValue = item1.autoBidMax[index];
        User maxUser = item1.autoBidUsers[index];
        String firstName = maxUser.firstName;
        String lastName = maxUser.lastName;
        String userName = firstName + " " + lastName;
        assertEquals(1, index,0.00);
        assertEquals(20.00, bidValue, 0.00);
        assertNotEquals(40.00, bidValue, 0.00);
        assertEquals("Jennifer Moutenot", userName);
    }

    @Test
    public void greatestMaxIndex() {
        item1.addAutoBid(user1, 40.00);
        int index = item1.greatestMaxIndex();
        double bidValue = item1.autoBidMax[index];
        assertEquals(0, index,0.00);
        assertNotEquals(1, index, 0.00);
        assertEquals(40.00, bidValue, 0.00);
        assertNotEquals(0.00, bidValue, 0.00);
    }

    @Test
    public void greatestMaxIndexTwo() {
        item1.addAutoBid(user1, 40.00);
        item1.addAutoBid(user2, 60.00);
        int index = item1.greatestMaxIndex();
        double bidValue = item1.autoBidMax[index];
        User maxUser = item1.autoBidUsers[index];
        String firstName = maxUser.firstName;
        String lastName = maxUser.lastName;
        String userName = firstName + " " + lastName;
        assertEquals(1, index,0.00);
        assertNotEquals(0, index, 0.00);
        assertEquals(60.00, bidValue, 0.00);
        assertNotEquals(40.00, bidValue, 0.00);
        assertEquals("Jennifer Moutenot", userName);
    }
}