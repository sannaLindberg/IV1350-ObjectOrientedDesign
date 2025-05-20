package se.kth.iv1350.posi.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posi.model.Amount;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {

    private ExternalInventorySystem inventorySystem;
    private ItemID existingID;
    private ItemID nonExistingID;
    private ItemDTO expectedDTO;
    private ItemID crachDBID;

    @BeforeEach
    void setUp() {
        inventorySystem = new ExternalInventorySystem();
        existingID = new ItemID("abc123");
        nonExistingID = new ItemID("nonexistent");
        crachDBID = new ItemID("crachDB");
        expectedDTO = new ItemDTO(
            new ItemID("abc123"),
            "BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free",
            new Amount(30.0),
            new Amount(6.0),
            "Big Wheel Oatmeal"
        );
    }

    @Test 

    public void testFindItemReturnsCorrectItemForValidID() throws Exception{
        ItemDTO result = inventorySystem.findItem(existingID);
        assertNotNull(result, "findItem() should not return null for valid ID");
        assertEquals(expectedDTO.getItemID(), result.getItemID(), "Item should match");
        assertEquals(expectedDTO.getName(), result.getName(), "Item name should match");
        assertEquals(expectedDTO.getPrice().getValue(), result.getPrice().getValue(), "Item price should match");
        assertEquals(expectedDTO.getVAT().getValue(), result.getVAT().getValue(), "Item VAT should Match");
    }

    @Test

    public void testFindItemThrowsInvalidIDExceptionForInvalidID(){

        InvalidIDException thrown = assertThrows(InvalidIDException.class, () -> {
            inventorySystem.findItem(nonExistingID);
        });

        assertTrue(thrown.getMessage().contains(nonExistingID.toString()), "should contain nonExistingID");
    }

    @Test
    public void TestFindItemThrowsDatabaseConnectionFailureException(){
        DatabaseConnectionFailureException thrown = assertThrows(DatabaseConnectionFailureException.class, () -> {
            inventorySystem.findItem(crachDBID);
        });
        assertTrue(thrown.getMessage().contains("Could not connect to the inventory database."), "should indicate a database failure");
    }
} 
