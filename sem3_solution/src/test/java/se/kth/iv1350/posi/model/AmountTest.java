package se.kth.iv1350.posi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AmountTest {
 
    @Test
    public void subtract() {
        double amountOfOperand1 = 10.0;
        double amountOfOperand2 = 3.0;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.subtract(operand2);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");
    }

    @Test
    public void subtractNegRes() {
        double amountOfOperand1 = 3.0;
        double amountOfOperand2 = 10.0;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.subtract(operand2);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");
    }

    @Test
    public void subtractNegOperand() {
        double amountOfOperand1 = -3.0;
        double amountOfOperand2 = -3.0;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.subtract(operand2);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");
    }


    @Test
    void testAdd(){
        double firstDouble = 2.0;
        double secondDouble = 5.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble + secondDouble);
        Amount result = amountOne.add(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Addition fails!");
    }

    @Test
    void testAddNegRes(){
        double firstDouble = 2.0;
        double secondDouble = -5.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble + secondDouble);
        Amount result = amountOne.add(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Addition fails!");
    }

    @Test
    void testAddNegZeroRes(){
        double firstDouble = 5.0;
        double secondDouble = -5.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble + secondDouble);
        Amount result = amountOne.add(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Addition fails!");
    }

    @Test
    void testMultiply(){
        double firstDouble = 5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble * secondDouble);
        Amount result = amountOne.multiply(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testMultiplyNeg(){
        double firstDouble = -5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble * secondDouble);
        Amount result = amountOne.multiply(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testMultiplyZero(){
        double firstDouble = 0.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble * secondDouble);
        Amount result = amountOne.multiply(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testDividePosRes(){
        double firstDouble = 5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble / secondDouble);
        Amount result = amountOne.divide(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testDivideNegRes(){
        double firstDouble = -5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble / secondDouble);
        Amount result = amountOne.divide(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testDivideNegOperandPosRes(){
        double firstDouble = -5.0;
        double secondDouble = -3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble / secondDouble);
        Amount result = amountOne.divide(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    public void toStringPosAmt() {
        Amount amount = new Amount(10.0);
        String expResult = new String("10,00");
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void toStringNegAmt() {
        Amount amount = new Amount(-10.0);
        String expResult = new String("-10,00");
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void toStringZeroAmt() {
        Amount amount = new Amount(0.0);
        String expResult =  new String("0,00");
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
}
