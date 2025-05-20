package se.kth.iv1350.posi.model;

/**
 * Represents an amount of money
 */

public class Amount {
    private double value; 

    /**
     * Creates a new instance, representing the amount 0.
     */
    public Amount () {
        this(0);
    }

    /**
     * Creates a new instance, representing the specified amount.
     *
     * @param value The amount represented by the newly created instance.
     */
    public Amount (double value) {
        this.value = value;
    }

    /**
    * Adds the specified <code>Amount</code> to this <code>Amount</code> and returns a
    * new <code>Amount</code> instance containing the result. This operation may
     * overflow if the resulting value exceeds <code>Double.MAX_VALUE</code>.
    *
    * @param other The <code>Amount</code> to add to this one.
    * @return A new <code>Amount</code> representing the sum.
    */
    public Amount add(Amount other) {
        return new Amount (this.value + other.value);
    }
    
    /**
     * Subtracts the specified <code>Amount</code> from this object and returns
     * a new <code>Amount</code> instance containing the result. The operation may
     * overflow if the resulting value is smaller than <code>Double.MIN_VALUE</code>.
     *
     * @param other The <code>Amount</code> to subtract.
     * @return The result of the subtraction.
     */
    public Amount subtract(Amount other) {
        return new Amount(this.value - other.value);
    }

    /**
    * Multiplies this <code>Amount</code> by the specified <code>Amount</code> and returns
    * a new <code>Amount</code> instance containing the resultThis operation may
     * overflow if the resulting value exceeds <code>Double.MAX_VALUE</code>.
    *
    * @param other The <code>Amount</code> to multiply with.
    * @return A new <code>Amount</code> representing the product.
    */
    public Amount multiply(Amount other) {
        return new Amount(this.value * other.value);
    }

    /**
    * Divides this <code>Amount</code> by the specified <code>Amount</code> and returns
    * a new <code>Amount</code> instance containing the result. Division by zero will result in
    * <code>Infinity</code> or <code>NaN</code>, depending on the value of the numerator.
    *
    * @param other The <code>Amount</code> to divide by.
    * @return A new <code>Amount</code> representing the quotient.
    */
    public Amount divide(Amount other) {
        return new Amount (this.value / other.value);
    }

    /**
    * Returns the numeric value represented by this object.
    *
    * @return The value as a double.
    */
    public double getValue() {
        return value; 
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

}
