package se.kth.iv1350.posi.view;

import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.TotalRevenueObserver;
/**
 * A wiew class that implements TotalRevenueObserver and displays the updated
 * total revenue to the user after each completed sale.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    private Amount totalRevenue = new Amount(0.0); 
    /**
     * New instance of TotalRevenueView.
     */
    public TotalRevenueView() {
    }
        
    /**
     * Updates the total revenue and prints new total to the console.
     * @param saleAmount the revenue from the most recently completed sale.
     */
    @Override
    public void printTotalRevenue(Amount saleAmount) {
        totalRevenue = (totalRevenue.add(saleAmount));
        StringBuilder totalIncome = new StringBuilder(); 
        totalIncome.append("\n------ Total Revenue -------\n"); 
        totalIncome.append("Total Revenue: ");
        totalIncome.append(totalRevenue );        
        totalIncome.append("\n---------------------------\n"); 

        System.out.println(totalIncome);
    }

}
