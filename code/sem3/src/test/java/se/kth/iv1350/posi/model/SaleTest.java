package se.kth.iv1350.posi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;

public class SaleTest {

    private Sale sale;  
    private ItemID itemIDJames;
    private ItemDTO itemDTOJames;
    private Item itemJames;
    private Item itemFake;
    
    
    @BeforeEach
    
    void setUp() {
    
    sale = new Sale();
    Amount vAT = new Amount(2.0);
    itemIDJames = new ItemID("007");
    itemDTOJames = new ItemDTO(itemIDJames,  "ActionFigure of James Bond", new Amount(100.0), vAT, "James Bond");
    itemJames = new Item(itemDTOJames);

    Amount priceBeforeVAT = new Amount (100.0);     
    Amount vatRate = new Amount(2.0);
    ItemDTO itemDTOFake = new ItemDTO(new ItemID("item123"), "fake item", priceBeforeVAT, vatRate, "fakeItemName");
    itemFake = new Item(itemDTOFake);

    }   

    @Test
    
    public void testAddNewItem() {
    Amount expeAmount = new Amount(1.0);
    sale.addItemToList(itemJames);
    
    assertTrue(sale.getItems().containsKey(itemIDJames), "Item be added to sale.");
    
    assertEquals(expeAmount.getValue(), sale.getItems().get(itemIDJames).getQuantity().getAmount().getValue(), "Quantity should be 1");
    
    assertEquals(itemDTOJames.getPriceWithVAT().getValue(), sale.getRunningTotal().getValue(), "Total should be equal to total price of item incl. VAT.");
    }
     
    @Test
    
    void testAddSameItemIncreaseQuantity() {
    sale.addItemToList(itemJames);
    sale.addItemToList(itemJames);
    Amount expeQuantAmount = new Amount(2.0);

    assertEquals(expeQuantAmount.getValue(), sale.getItems().get(itemIDJames).getQuantity().getAmount().getValue(), "Quantity should be 2 since there are two of the same items");
    Amount expectedTotal = itemDTOJames.getPriceWithVAT().multiply(new Amount(2));
    assertEquals(expectedTotal.getValue(), sale.getRunningTotal().getValue(), "Total should be total price times 2");
    } 

    @Test  
public void testCalculateRunningVATUpdatesCorrectly() {

    sale.calculateRunningVAT(itemJames); 
    Amount result = sale.getRunningVat();
    assertEquals(2.0, result.getValue());

    sale.calculateRunningVAT(itemFake); 
    result = sale.getRunningVat();
    assertEquals(4.0, result.getValue(), 0.001);

}    

    @Test
public void testCalculateRunningTotalUpdatesCorrectly() {
    sale = new Sale();
    sale.addItemToList(itemJames);
    Amount result = sale.getRunningTotal();
    assertEquals(102.0, result.getValue());

    sale.addItemToList(itemFake);
    result = sale.getRunningTotal();
    assertEquals(204.0, result.getValue());

}
}
