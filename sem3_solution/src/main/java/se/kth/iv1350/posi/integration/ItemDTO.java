package se.kth.iv1350.posi.integration;

import se.kth.iv1350.posi.model.Amount;
/**
 * Contains information about one particular item.
 */
public class ItemDTO {
    private final ItemID id;
    private final Amount price; 
    private final Amount vAT;
    private final String itemDescription;
    private final String name;

/**
     * Creates a new instance representing a particular item.
     * 
     * @param id The unique identifier of the item.
     * @param itemDescription A textual description of the item.
     * @param price The item's price excluding VAT.
     * @param vAT The item's VAT rate as a percentage (e.g., 6.0, 12.0).
     * @param name The name of the item.
     */
    public ItemDTO (ItemID id, String itemDescription, Amount price, Amount vAT, String name) {
        this.id = id;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vAT = vAT;
        this.name = name; 
    }
    /**
     * NY METOD
     * @return information regarding sale
     */
    @Override
    public String toString() {
        return "Item ID: " + id + "\n" +
                "Item name: " + name + "\n" +
                "Item cost: " + price + " SEK \n" +
                "VAT: " + vAT + " % \n" +
                "Item description: " + itemDescription;
    }

    /**
     * Returns the description of the item.
     * 
     * @return A string containing the item description.
     */
    public String getItemDescription () {
        return itemDescription;
    }

    /**
     * Returns the price of the item excluding VAT.
     * 
     * @return The base price of the item.
     */
    public Amount getPrice() {
        return price; 
    }

     /**
     * Calculates and returns the price of the item including VAT.
     * 
     * @return The total price with VAT included.
     */
    public Amount getPriceWithVAT() {
        return price.add(price.divide(new Amount(100.0)).multiply((vAT)));
    }

    /**
     * Returns the unique item identifier.
     * 
     * @return The ItemID of the item.
     */
    public ItemID getItemID() {
        return id;
    }

    /**
     * Returns the name of the item.
     * 
     * @return A string containing the name of the item.
     */
    public String getName() {
        return name; 
    }

     /**
     * Returns the VAT rate of the item.
     * 
     * @return The VAT rate as an {<code>Amount</code>.
     */
    public Amount getVAT() {
        return vAT;
    }

    /**
     * Calculates the VAT amount for the item based on its price and VAT rate.
     * The result is rounded to two decimal places.
     * 
     * @return The VAT amount.
     */
    public Amount calculateVATAmount() {
        double rawVat = price.getValue() * vAT.getValue() / 100.0;
        double roundedVat = Math.round(rawVat * 100.0) / 100.0; 
        return new Amount(roundedVat);
    }

    /**
     * Returns the VAT rate as a formatted string with a percent symbol.
     * 
     * @return The VAT rate formatted as a string.
     */
    public String getFormattedVAT() {
        return String.format("%.1f%%", vAT);
    }
 }