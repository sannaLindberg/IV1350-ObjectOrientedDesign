package se.kth.iv1350.posi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class CashPaymentTest {
@Test
    public void testGetChange() {
        Amount totalCost = new Amount(100);
        Amount paidAmt = new Amount(500);
        CashPayment instance = new CashPayment(paidAmt);
        Amount expResult = paidAmt.subtract(totalCost);
        Amount result = instance.getChange(totalCost);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong total cost.");
    }    

}
