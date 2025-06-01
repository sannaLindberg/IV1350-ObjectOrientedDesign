package se.kth.iv1350.posi.model;
/**
 * Represents one specific payment for one specific sale. The sale is payed
 * with cash.
 */
public class CashPayment {
    private final Amount paidAmount;
    /**
     * Creates a new instance representing a cash payment.
     *
     * @param paidAmount The amount of money provided by the customer.
     */
    
    public CashPayment(Amount paidAmount) {
        this.paidAmount = paidAmount;
    }

     /**NY METOD
     * Calculates the change to return to the customer.
     *
     * @param totalcost The total cost of the sale.
     * @return The change to give back.
     */
    public Amount getChange(Amount totalcost) {

        return new Amount(Math.round((paidAmount.getValue() - totalcost.getValue()) * Math.pow(10, 2))
        / Math.pow(10, 2));
    }


    /**
     * Gets the amount that was paid by the customer.
     *
     * @return The paid amount.
     */
    Amount getPaidAmount() {
        return paidAmount;
    }

}