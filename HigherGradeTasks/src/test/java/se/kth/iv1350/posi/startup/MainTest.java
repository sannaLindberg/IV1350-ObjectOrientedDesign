package se.kth.iv1350.posi.startup;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.posi.view.View;
import se.kth.iv1350.posi.controller.Controller;
import se.kth.iv1350.posi.integration.Printer;

public class MainTest {

        private View view;
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;

        @BeforeEach
        void setUp() throws Exception{
            System.setOut(new PrintStream(outContent));
            Printer printer = new Printer();
            Controller contr = new Controller(printer);
            view = new View (contr);

        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

 @Test
    public void testSampleExecutionPrintsCorrect() {
        view.sampleExecution();
        String output = outContent.toString();

        assertTrue(output.contains("New Sale"), "should contain information about sale");
        assertTrue(output.contains("Add 1 item with item id abc123 :"));
        assertTrue(output.contains("Item ID: abc123"));
        assertTrue(output.contains("Item name: Big Wheel Oatmeal"));
        assertTrue(output.contains("Item cost: 30,00 SEK"));
        assertTrue(output.contains("VAT: 6,00 %"));
        assertTrue(output.contains("Item description: BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free"));
        assertTrue(output.contains("Total cost (incl. VAT): 31,80"));
        assertTrue(output.contains("Total VAT: 1,80"));
        assertTrue(output.contains("End Sale"));
        assertTrue(output.contains("Total incl. VAT: 31,80"));

        assertTrue(output.contains("Total revenue: 31,80"), "should contain revenue");


        assertTrue(output.contains("----------------Begin receipt----------------"), "Should contain receipt");
        assertTrue(output.contains("Big Wheel Oatmeal 1,00 x 30.0         30.0 SEK"));
        assertTrue(output.contains("Total price: 31,80 SEK"));
        assertTrue(output.contains("Total VAT: 1,80 SEK"));
        assertTrue(output.contains("Cash: 100,00 SEK"));
        assertTrue(output.contains("Total Change: 68,20 SEK"));
        assertTrue(output.contains("----------------End receipt----------------"));
        assertTrue(output.contains("change to give to the customer: 68,20"));

    }
}
