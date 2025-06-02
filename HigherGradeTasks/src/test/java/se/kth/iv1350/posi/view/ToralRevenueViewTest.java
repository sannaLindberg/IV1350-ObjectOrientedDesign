package se.kth.iv1350.posi.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posi.model.Amount;

public class ToralRevenueViewTest {
     private TotalRevenueView revenueView;
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;

        @BeforeEach
        void setUp() throws Exception{
            System.setOut(new PrintStream(outContent));
            revenueView = new TotalRevenueView ();

        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

        @Test
        void testTotalRevenuePrintsCorrectly(){
            revenueView.printTotalRevenue(new Amount(50.0));
            revenueView.printTotalRevenue(new Amount(25.0));

            String output = outContent.toString();
            assertTrue(output.contains("Total revenue: 50,00 SEK"), "Output should contain right value");
            assertTrue(output.contains("Total revenue: 75,00 SEK"),"output should contain new added revenue");


            
        }
}
