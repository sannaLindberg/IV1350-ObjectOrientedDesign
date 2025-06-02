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
        assertTrue(output.contains("Add 1 item with item id abc123 :"));
        assertTrue(output.contains("Item ID: abc123"));
        assertTrue(output.contains("Item name: Big Wheel Oatmeal"));
        assertTrue(output.contains("Item cost: 30,00 SEK"));
        assertTrue(output.contains("VAT: 6,00 %"));
        assertTrue(output.contains("Item description: BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free"));
        assertTrue(output.contains("Total cost (incl. VAT): 31,80"));
        assertTrue(output.contains("Total VAT: 1,80"));
   
    }

    @Test
    void testEndSalePrintsCorrectly(){
        ItemID itemID = new ItemID("abc123");
        instance.scannNewItem(itemID);
         instance.endSale();
        String output = outContent.toString();

        assertTrue(output.contains("End Sale"));
        assertTrue(output.contains("Total incl. VAT: 31,80"));
        assertTrue(output.contains("change to give to the customer: 68,20"));
    }

    @Test
    void testScannNewItemInvalidIDExceptionHandled() throws Exception {
        ItemID itemID = new ItemID("invalidID");
        instance.scannNewItem(itemID);
        String output = outContent.toString();
        assertTrue(output.contains("The itemID : " + itemID + " is invalid, scann different item "));
    }

    @Test
    void testScannNewItemOperationFailedExceptionHandled() throws Exception{
        ItemID itemID = new ItemID("crachDB");
        instance.scannNewItem(itemID);
        String output = outContent.toString();
        assertTrue(output.contains("could not add item because of system failure"));

    }
}   