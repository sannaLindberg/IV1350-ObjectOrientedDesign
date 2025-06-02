package se.kth.iv1350.posi.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
    
   
import se.kth.iv1350.posi.integration.DatabaseConnectionFailureException;
import se.kth.iv1350.posi.integration.InvalidIDException;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.integration.Printer;
import se.kth.iv1350.posi.model.Amount;
   
    public class ControllerTest {
    private Controller instance;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

    @BeforeEach
    public void setUp() throws IOException {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();
        instance = new Controller(printer);
    }

    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    void testStartSaleCreatesNewSale() {
        instance.startSale();
        assertNotNull(instance.getCurRunningTotal(), "Sale should be initialized and not return null total.");
    }

    @Test

    public void testEndSaleReturnsCorrectRunningTotal() {
        try{
        instance.startSale();  // Start a new sale
        ItemID itemID = new ItemID("abc123");

        instance.addItem(itemID); // Add an item (assumes price is known)
        Amount expectedTotal = new Amount(31.8); // Replace with actual expected total based on itemID
        Amount actualTotal = instance.endSale();
        assertEquals(expectedTotal.getValue(), actualTotal.getValue(), "Running total after endSale is incorrect.");
        }catch(Exception e) {
                fail("No exception should have been thrown, but got: " + e.getMessage());
        }
    }
    
    @Test

    public void testAddItemNonExistingID(){
        ItemID existingID = new ItemID("abc123"); // Assuming this ID exists in ExternalInventorySystem
        instance.startSale();
        try{
        ItemDTO returnedItem = instance.addItem(existingID);
        assertEquals(existingID, returnedItem.getItemID(), "Returned item has wrong ID.");
        }catch(InvalidIDException | OperationFailedException ex){
            fail("Got exception.");
            ex.printStackTrace();
        }
        ItemID invalidID = new ItemID("invalid");
        try{
            instance.addItem(invalidID);
            fail("Managed to add item with ivalidID");
        }catch(OperationFailedException ex) {
            fail("Wrong exception type thrown for invalid item ID.");
            ex.printStackTrace();
        }catch(InvalidIDException ex){
            assertTrue(ex.getMessage().contains(invalidID.toString()),
            "Wrong exception message, does not contain specified ID: " + ex.getMessage());     
        }
    }


    @Test 

    public void testAddDatabaseFailiureID(){
        ItemID crachDBID = new ItemID("crachDB");
        instance.startSale();
        try{
            instance.addItem(crachDBID);
            fail("Expected exeption was not thrown.");
        }catch(InvalidIDException exc){
            fail("Wrong exception type thrown.");
            exc.printStackTrace();
        }catch(OperationFailedException ex){
            assertTrue(ex.getMessage().contains("could not register item"),
            "Wrong exception message" + ex.getMessage());   
            assertNotNull(ex.getCause(), "the cause of OperationFailedException should not be null");
            assertTrue(ex.getCause() instanceof DatabaseConnectionFailureException,
            "Wrong root cause, expected DatabaseConnectionFailureException but got " + ex.getCause().getClass().getCanonicalName());
        }
    }

    @Test

        public void testEnterPaymentReturnsCorrectChange() {
            try{
            instance.startSale(); // Make sure a sale is started
            ItemID itemID = new ItemID("abc123");
            
            instance.addItem(itemID); // Assume item costs 500, for example
            Amount paymentAmount = new Amount(1000);
            Amount expectedChange = new Amount(968.2); // Adjust based on the actual item price
            Amount actualChange = instance.enterPayment(paymentAmount);
        
            assertEquals(expectedChange.getValue(), actualChange.getValue(), "Incorrect change returned from enterPayment.");
            }catch(Exception e) {
            fail("No exception should have been thrown, but got: " + e.getMessage());
        }
    }      
}  
 


