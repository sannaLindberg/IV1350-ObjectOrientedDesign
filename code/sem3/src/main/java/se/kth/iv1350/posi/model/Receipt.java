package se.kth.iv1350.posi.model;

import java.time.LocalDateTime;
//import se.kth.iv1350.posi.integration.ItemDTO;
/**
 * Represents a receipt for A completed sale 
 */
public class Receipt {
    private final Sale sale;
    private final Amount paidAmount;

    /**
     * Creates a new instance of <code>Recepeit</code>
     * 
     * @param sale The sale that this reseipt represents.
     * @param paidAmount The amount of cash paid by the customer.
     */
    public Receipt(Sale sale, Amount paidAmount) {
        this.sale = sale;
        this.paidAmount = paidAmount;
    }
    
    /** NY METOD
     * Creates a well-formatted string with the entire content of the receipt.
     *
     * @return The well-formatted receipt string.
     */
    public String generateText() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------Begin receipt---------------- \n");
        sb.append("Time of Sale: ")
        .append(LocalDateTime.now())
        .append("\n \n");


        for (Item item : sale.getItems().values()) {
            String name = item.getItemDTO().getName();
            Amount unitPrice = item.getItemDTO().getPrice();
            Amount quantity = item.getQuantity().getAmount();
            Amount lineTotal = unitPrice.multiply(quantity);
            sb.append(name)
            .append(" ")
            .append(quantity).append(" x ")
            .append(String.format("%s", unitPrice.getValue()))
            .append(" ")
            .append(String.format("        %s SEK \n", lineTotal.getValue()));
        }

        sb.append("\n");
        sb.append("Total price: ").append(sale.getRunningTotal().toString()).append(" SEK\n");
        sb.append("Total VAT: ").append(sale.getRunningVat().toString()).append(" SEK\n");
        sb.append("\n");
        sb.append("Cash: ").append(paidAmount.toString()).append(" SEK\n");
        sb.append("Total Change: ").append(paidAmount.subtract(sale.getRunningTotal()).toString()).append(" SEK\n");
       
        sb.append("----------------End receipt----------------");
        return sb.toString();
    }
}
