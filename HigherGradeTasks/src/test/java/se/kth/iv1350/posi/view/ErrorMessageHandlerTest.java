package se.kth.iv1350.posi.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ErrorMessageHandlerTest {
 private ErrorMessageHandler errorHandler;
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;

        @BeforeEach
        void setUp() throws Exception{
            System.setOut(new PrintStream(outContent));
            errorHandler = new ErrorMessageHandler();

        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

        @Test
        void testShowErrorMsgPrintsCorrectly() {
            String testMsg = "Error exception!";
            errorHandler.showErrorMsg(testMsg);
            
            String output = outContent.toString();
            assertTrue(output.contains("ERROR"), "sould contain ERROR");
            assertTrue(output.contains(testMsg), "Should contain right message");
            assertFalse(output.isBlank(), "Output should not be blank");
            
        }

}
