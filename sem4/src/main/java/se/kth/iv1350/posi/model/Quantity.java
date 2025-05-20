package se.kth.iv1350.posi.model;
/**
 * Represents a quantity of items in a sale.
 */
public class Quantity {
    private final Amount amount;

    /**
     * Creates a new instance of <code>Quantity</code> with the scecified amount.
     * 
     * @param amount The initital quantity.
     */
    public Quantity(Amount amount) {
        this.amount = amount;
    }

    /**
     * Increases the current quantity by one.
     * 
     * @return A new <code>Quantity</code> instace with the increased amount.
     */
    public Quantity increase() {
        return new Quantity(this.amount.add(new Amount(1.0)));
    }
    /**
     * Returns the current amount representing the quantity.
     * 
     * @return The <code>Amount</code> of the quantity.
     */
    public Amount getAmount() {
        return amount;
    }
}
