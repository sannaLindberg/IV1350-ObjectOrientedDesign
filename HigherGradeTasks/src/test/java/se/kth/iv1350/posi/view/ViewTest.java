package se.kth.iv1350.posi.view;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import se.kth.iv1350.posi.controller.Controller;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.integration.Printer;
import static org.junit.jupiter.api.Assertions.*;

public class ViewTest {

    private View instance;
    //private Controller contr;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;


     @BeforeEach
    public void setUp() throws IOException {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));;
        Printer printer = new Printer();
        Controller contr = new Controller(printer);
        instance = new View (contr);
        instance.startSale();
    }

    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    void testScannNewItemPrintsCorrectly() throws Exception {
        ItemID itemID = new ItemID("abc123");
        
        instance.scannNewItem(itemID);
        String output = outContent.toString();
         assertTrue(output.contains("Add 1 item with item id abc123 :"), "should contain the added Item");
        assertTrue(output.contains("Item ID: abc123"), "should contain Item Id ");
        assertTrue(output.contains("Item name: Big Wheel Oatmeal"), "should contain item name");
        assertTrue(output.contains("Item cost: 30,00 SEK"), "should contain cost of the item");
        assertTrue(output.contains("VAT: 6,00 %"), "should contain Item VAT");
        assertTrue(output.contains("Item description: BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free"), "should contain description");
        assertTrue(output.contains("Total cost (incl. VAT): 31,80"), "should contain total cost");
        assertTrue(output.contains("Total VAT: 1,80"), "should contain total VAT");
   
    }

    @Test
    void testEndSalePrintsCorrectly(){
        ItemID itemID = new ItemID("abc123");
        instance.scannNewItem(itemID);
         instance.endSale();
        String output = outContent.toString();

        assertTrue(output.contains("End Sale"), "should print that the sale has ended");
        assertTrue(output.contains("Total incl. VAT: 31,80"), "should contain total cost");
        assertTrue(output.contains("change to give to the customer: 68,20"), "should contain customers change");
    }

    @Test
    void testScannNewItemInvalidIDExceptionHandled() throws Exception {
        ItemID itemID = new ItemID("invalidID");
        instance.scannNewItem(itemID);
        String output = outContent.toString();
        assertTrue(output.contains("The itemID : " + itemID + " is invalid, scann different item "), "should contain right item ID in error meddage");

    }

    @Test
    void testScannNewItemOperationFailedExceptionHandled() throws Exception{
        ItemID itemID = new ItemID("crachDB");
        instance.scannNewItem(itemID);
        String output = outContent.toString();
         assertTrue(output.contains("could not add item because of system failure"), "should contain error message");


    }
}   
