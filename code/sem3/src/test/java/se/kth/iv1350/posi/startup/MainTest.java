package se.kth.iv1350.posi.startup;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MainTest {
 
    @Test
    public void testMain() {
        PrintStream originalSysOut = null;
        try {
            originalSysOut = System.out;
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            String[] args = null;            Main.main(args);
            assertTrue(outContent.toString().contains("change to give to the customer: "),
                       "Wrong output when main is executed");

            } finally {
                System.setOut(originalSysOut);
            }
    }
}
