package se.kth.iv1350.posi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;

public class ItemTest {

@Test
    public void increaseQuantityTest(){
    Amount price = new Amount(100.0);
        ItemID itemID = new ItemID("abs");
        ItemDTO itemDTO = new ItemDTO(itemID, "hejehj", price, new Amount(1.0), "kakor");
        Item item = new Item(itemDTO);
        
        item.increaseQuantity();

        Amount expected = new Amount(2.0);
        Amount actual = item.getQuantity().getAmount();

    
        assertEquals(expected.getValue(), actual.getValue(), 0.0001, "Quantity should increase by 1.0");
    
    }
}
