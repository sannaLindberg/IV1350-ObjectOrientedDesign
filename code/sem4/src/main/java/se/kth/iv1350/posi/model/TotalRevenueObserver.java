package se.kth.iv1350.posi.model;
/**
 * Observer interface for monitoring updates in the total revenue.
 */
public interface  TotalRevenueObserver {
    /**
     * Called when the total revenue is updated after a completed sale. 
     * 
     * @param totalRevenue the updated total revenue after the last sale.
     */
     void printTotalRevenue (Amount totalRevenue) ;
}
