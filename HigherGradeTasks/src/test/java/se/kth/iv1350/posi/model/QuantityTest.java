package se.kth.iv1350.posi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class QuantityTest {
    @Test
    public void testIncrease() {
        Amount originalAmount = new Amount(3.0);
        Quantity quantity = new Quantity(originalAmount);

        Quantity increasedQuantity = quantity.increase();
        double expected = 4.0;
        double actual = increasedQuantity.getAmount().getValue();

        assertEquals(expected, actual, 0.0001, "Increase did not add 1.0 correctly");
    }
}
