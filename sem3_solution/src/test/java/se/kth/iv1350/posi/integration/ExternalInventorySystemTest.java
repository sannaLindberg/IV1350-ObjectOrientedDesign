package se.kth.iv1350.posi.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posi.model.Amount;

public class ExternalInventorySystemTest {

    private ExternalInventorySystem inventorySystem;
    private ItemID existingID;
    private ItemID nonExistingID;
    private ItemDTO expectedDTO;

    @BeforeEach
    void setUp() {
        inventorySystem = new ExternalInventorySystem();
        existingID = new ItemID("abc123");
        nonExistingID = new ItemID("nonexistent");
        expectedDTO = new ItemDTO(
            new ItemID("abc123"),
            "BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free",
            new Amount(30.0),
            new Amount(6.0),
            "Big Wheel Oatmeal"
        );
    }

    @Test
    void testFindItemReturnsCorrectItem() {
        ItemDTO result = inventorySystem.findItem(existingID);
        assertNotNull(result, "findItem() should not return null for a valid ID");
        assertEquals(expectedDTO.getItemID(), result.getItemID(), "ItemID should match");
        assertEquals(expectedDTO.getName(), result.getName(), "Item name should match");
        assertEquals(expectedDTO.getPrice().getValue(), result.getPrice().getValue(), "Item price should match");
        assertEquals(expectedDTO.getVAT().getValue(), result.getVAT().getValue(), "Item VAT should match");
    }

    @Test
    void testFindItemReturnsNullForUnknownID() {
        ItemDTO result = inventorySystem.findItem(nonExistingID);
        assertNull(result, "findItem() should return null for an unknown ID");
    }
}
