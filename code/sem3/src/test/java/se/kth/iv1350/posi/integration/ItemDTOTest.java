package se.kth.iv1350.posi.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posi.model.Amount;

public class ItemDTOTest {

    @Test
    public void calculateVATAmountTest() {
        double valueOfVAT = 3.0;
        double valueOfPrice = 100.0;
        double valueOfDevider = 100;

        Amount vAT = new Amount(valueOfVAT);
        Amount price = new Amount(valueOfPrice);
        Amount devider = new Amount(valueOfDevider);
       

        Amount expResult = new Amount((valueOfVAT/valueOfDevider) * valueOfPrice);

        Amount vatRate =  vAT.divide(devider); // 'vat' is the item's rate, e.g., 6.0, 12.0, 25.0
        Amount result = price.multiply((vatRate));

        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");

        
    }
}
