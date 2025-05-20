package se.kth.iv1350.posi.integration;
import se.kth.iv1350.posi.model.Receipt;
/**
 * The interface to the printer, used for all printouts initiated by this
 * program.
 */
public class Printer {
    /**
     * Prints the given receipt by outputting its formatted text to the console.
     *
     * @param receipt The receipt to be printed.
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(receipt.generateText());
    } 
}
