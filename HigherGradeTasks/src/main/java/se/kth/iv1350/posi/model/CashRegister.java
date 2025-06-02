package se.kth.iv1350.posi.model;
/**
 * Represents a cash register that holds and updates the current balance.
 */
public class CashRegister {
    private Amount balance; 
/**
 * Creates a new cash register instance with an initial amount of money.
 * 
 * @param initialAmount The initial balance of the cash register.
 */
    public CashRegister(Amount initialAmount) {
        this.balance = initialAmount;
    }

    /**
     * Adds a payment amount to the current balance of the cash register.
     * 
     * @param payment The amount to be added to the balancce.
     */
    public void addAmount(Amount payment) {
        balance = balance.add(payment);
    }

    /**
     * Gets the current balance stored in the cash register.
     * 
     * @return The current balance.
     */
    public Amount getBalance() {
        return balance;
    }
}
