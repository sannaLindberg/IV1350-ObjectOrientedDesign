package se.kth.iv1350.posi.view;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
* Writes the updated total revenue to a file after each completed sale,
* using the Template Method pattern.
*/
public class TotalRevenueFileOutput extends TotalRevenueObserverTemplate {
private static final String TOTAL_REVENUE_FILE = "TotalRevenueFile.txt";
public TotalRevenueFileOutput() {

// Initialization if needed

}


    @Override

    protected void doShowTotalRevenue() throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(TOTAL_REVENUE_FILE, false))) {
        writer.println("Updated total revenue: " + totalRevenue + " SEK");
        }
    }



    @Override

    protected void handleErrors(Exception e) {
        System.err.println("Error writing to file: " + e.getMessage());
        e.printStackTrace();
        }

}