package se.kth.iv1350.posi.view;

/**
* A wiew class that implements TotalRevenueObserver and displays the updated
* total revenue tp the user after each completed sale.
*/
public class TotalRevenueView extends TotalRevenueObserverTemplate {

    public TotalRevenueView() {
        super();
    } 

    @Override

    protected void doShowTotalRevenue() {
        System.out.println("Total revenue: " + totalRevenue + " SEK");
    }

    @Override

    protected void handleErrors(Exception e) {
        System.err.println("Error" + e.getMessage());
        e.printStackTrace();
    }

}
