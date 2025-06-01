package se.kth.iv1350.posi.integration;

import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.Sale;
/**
 * Represents an external accounting system that keeps track of total revenue.
 * This class simulates the behavior of updating an external system by summing 
 * the total amounts from all completed sales.
 */
public class ExternalAccountingSystem {
    private Amount totalRevenue = new Amount(0.0);

    /**ÄNDRAD
     * Records the specified sale in the external accounting system by
     * updating the total accumulated revenue.
     *
     * @param sale The completed sale to be recorded.
     */
    public void recordSale(Sale sale) {
        totalRevenue = totalRevenue.add(sale.getRunningTotal());
    }

    /**
     * gets the totalRevenue for this sale
     * 
     * @return totalRevenue 
     */

    public Amount getTotalRevenue() {
        return totalRevenue;
    }


}
