package se.kth.iv1350.posi.integration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemIDTest {

    @Test
    public void testToStringReturnsCorrectID() {
        String expectedID = "ABC123";
        ItemID itemID = new ItemID(expectedID);
        assertEquals(expectedID, itemID.toString(), "toString() should return the ID");
    }

    @Test
    public void testEqualsSameObject() {
        ItemID itemID = new ItemID("XYZ");
        assertEquals(itemID, itemID, "An object should equal itself");
    }

    @Test
    public void testEqualsEqualObjects() {
        ItemID id1 = new ItemID("123");
        ItemID id2 = new ItemID("123");
        assertEquals(id1, id2, "Objects with the same ID should be equal");
    }

    @Test
    public void testEqualsDifferentObjects() {
        ItemID id1 = new ItemID("123");
        ItemID id2 = new ItemID("456");
        assertNotEquals(id1, id2, "Objects with different IDs should not be equal");
    }

    @Test
    public void testNotEqualsNull() {
        ItemID itemID = new ItemID("123");
        assertNotEquals(itemID, null, "ItemID should not equal null");
    }

    @Test
    public void testNotEqualsDifferentClass() {
        ItemID itemID = new ItemID("123");
        String notAnItemID = "123";
        assertNotEquals(itemID, notAnItemID, "ItemID should not equal an object of another class");
    }

    @Test
    public void testHashCodeEqualForSameIDs() {
        ItemID id1 = new ItemID("HASH123");
        ItemID id2 = new ItemID("HASH123");
        assertEquals(id1.hashCode(), id2.hashCode(), "Equal objects must have equal hashCodes");
    }
}

