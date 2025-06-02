package se.kth.iv1350.posi.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;

public class ReceiptTest {

    @Test

    void testGenerateText() {

        ItemID itemID = new ItemID("abc123");
        Amount price = new Amount (30.0);
        Amount vat = new Amount(6.0);
        String itemDesc = "Green cucumber";
        String name = "cucumber";
        ItemDTO itemDTO = new ItemDTO(itemID, name, price, vat, itemDesc);
        Item item = new Item(itemDTO);
        Sale sale = new Sale();

        sale.addItemToList(item);
        Amount paidAmount = new Amount(100.0);
        Receipt receipt = new Receipt(sale, paidAmount);
        String testText = receipt.generateText();

        assertTrue(testText.contains("----------------Begin receipt----------------"), "Receipt should contain a start");
        assertTrue(testText.contains("cucumber"), "Receipt should contain name");
        assertTrue(testText.contains("Total price: 31,80 SEK"), "Receipt should mention cash amount");
        assertTrue(testText.contains("Total VAT: 1,80 SEK"), "Receipt should mention cash amount");
        assertTrue(testText.contains("Cash: 100,00 SEK"), "Receipt should mention total change of the sale");
        assertTrue(testText.contains("Total Change: 68,20 SEK"), "Receipt should mention total change of the sale");

    }

}

