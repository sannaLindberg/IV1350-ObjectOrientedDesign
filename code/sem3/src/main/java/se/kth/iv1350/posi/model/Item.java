package se.kth.iv1350.posi.model;

import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
/**
 * Represents an item in a sale, including its data and quantity.
 */
public class Item {
    private final ItemDTO itemDTO;
    public Quantity quantity;
    public ItemDTO getItemDTO;
    
    /**
     * Constructs a new <code>Item</code> with an initial quantity of 1.
     *
     * @param itemDTO The data transfer object containing item information.
     */
    public Item (ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
        this.quantity = new Quantity(new Amount(1.0)); 
    }

    /**
     * Increases the quantity of this item by 1.
     */
    public void increaseQuantity() {
        quantity = quantity.increase();
    }

    /**
     * Returns the <code>ItemDTO</code> containing information about this item.
     *
     * @return The item data.
     */
    public ItemDTO getItemDTO () {
        return itemDTO;
    }

     /**
     * Returns the unique identifier of this item.
     *
     * @return The item ID.
     */
    public ItemID getItemID () {
        return itemDTO.getItemID();
    }

    /**
     * Returns the current quantity of this item.
     *
     * @return The quantity of the item.
     */
    public Quantity getQuantity () {
        return quantity;

    }
}

