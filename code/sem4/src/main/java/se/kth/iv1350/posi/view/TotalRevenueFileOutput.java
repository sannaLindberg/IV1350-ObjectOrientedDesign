package se.kth.iv1350.posi.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.TotalRevenueObserver;
/**
 * imlements the TotalRevenueObserver and writes the updated total revenue
 * to a file after each completed sale.
 * 
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String TOTAL_REVENUE_FILE = "TotalRevenueFile.txt";
    private PrintWriter printerWriter;
    private Amount totalRevenue = new Amount(0.0);

    /**
     * crreates an instance of TotalRevenueFileOutput and opens
     * a writer to the output file.
     * 
     * @throws IOException if the file cannot be created or written to.
     */
    public TotalRevenueFileOutput() throws IOException {
            printerWriter = new PrintWriter(TOTAL_REVENUE_FILE);
            printerWriter.println(TOTAL_REVENUE_FILE);
        
    } 
   
    /**
     * Updates the total revenue and writes it to a file.
     * @param addedSale The revenue from the most recently completed sale.
     */
    @Override
    public void printTotalRevenue(Amount addedSale) {
        totalRevenue = (totalRevenue.add(addedSale));

    }
    /**
     * Prints the total re
     */
    public void printFinalTotal() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TOTAL_REVENUE_FILE, false))) {
        writer.println("Final total Revenue: " + totalRevenue + " SEK");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

}
