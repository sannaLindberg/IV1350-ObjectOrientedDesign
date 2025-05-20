package se.kth.iv1350.posi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class CashRegisterTest {

    @Test
    public void testAddAmountIncreasesBalance() {
        Amount initialAmount = new Amount(100.0);
        CashRegister instance = new CashRegister(initialAmount);

        Amount payment = new Amount(50.0);
        instance.addAmount(payment);

        Amount expected = initialAmount.add(payment);
        Amount actual = instance.getBalance();

        assertEquals(expected.getValue(), actual.getValue(), "Saldo efter addAmount blev fel.");
    }
}
