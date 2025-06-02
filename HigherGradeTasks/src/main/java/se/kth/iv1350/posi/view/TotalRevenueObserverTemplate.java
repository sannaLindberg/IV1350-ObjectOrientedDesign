package se.kth.iv1350.posi.view;

import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.TotalRevenueObserver;

/**
* Template class for displaying total revenue using the Template Method pattern.
*/
public abstract class TotalRevenueObserverTemplate implements TotalRevenueObserver {
    protected Amount totalRevenue = new Amount(0.0);

    @Override

    public final void printTotalRevenue(Amount saleAmount) {
        calculateTotalRevenue(saleAmount);
        showTotalRevenue();
    }



    private void calculateTotalRevenue(Amount saleAmount) {
        totalRevenue = totalRevenue.add(saleAmount);
    }



    private void showTotalRevenue() {
        try {
            doShowTotalRevenue();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalRevenue() throws Exception;
    protected abstract void handleErrors(Exception e);

}