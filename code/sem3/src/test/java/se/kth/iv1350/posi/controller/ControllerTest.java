package se.kth.iv1350.posi.controller;
    
    import java.io.ByteArrayOutputStream;
    import java.io.PrintStream;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import se.kth.iv1350.posi.integration.ExternalCreator;
    import se.kth.iv1350.posi.integration.ItemDTO;
    import se.kth.iv1350.posi.integration.ItemID;
    import se.kth.iv1350.posi.integration.Printer;
    import se.kth.iv1350.posi.model.Amount;
    
    public class ControllerTest {
        private Controller controller;
        private ByteArrayOutputStream outContent;
       
        @BeforeEach
    
        public void setUp() {
    
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
    
            Printer printer = new Printer(); // Assuming real printer prints to console
            ExternalCreator creator = new ExternalCreator(); // Provides real integrations
            controller = new Controller(printer, creator);
            controller.startSale();
        }
    
        @Test
            public void testEndSaleReturnsCorrectRunningTotal() {
                controller.startSale();  // Start a new sale
                ItemID itemID = new ItemID("abc123");
                controller.addItem(itemID); // Add an item (assumes price is known)
                Amount expectedTotal = new Amount(31.8); // Replace with actual expected total based on itemID

                Amount actualTotal = controller.endSale();

                assertEquals(expectedTotal.getValue(), actualTotal.getValue(), "Running total after endSale is incorrect.");

            }
    
        @Test
        public void testAddItemReturnsCorrectItem() {
            ItemID itemID = new ItemID("abc123"); // Assuming this ID exists in ExternalInventorySystem
            ItemDTO returnedItem = controller.addItem(itemID);
            assertNotNull(returnedItem, "Returned ItemDTO should not be null.");
            assertEquals(itemID, returnedItem.getItemID(), "Returned item has wrong ID.");
        }

        @Test
        public void testEnterPaymentReturnsCorrectChange() {
            controller.startSale(); // Make sure a sale is started
            ItemID itemID = new ItemID("abc123");
            controller.addItem(itemID); // Assume item costs 500, for example        
            Amount paymentAmount = new Amount(1000);
            Amount expectedChange = new Amount(968.2); // Adjust based on the actual item price
            Amount actualChange = controller.enterPayment(paymentAmount);
            assertEquals(expectedChange.getValue(), actualChange.getValue(), "Incorrect change returned from enterPayment.");
        
        }
    
    }


