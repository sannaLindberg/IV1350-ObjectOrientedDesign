package se.kth.iv1350.posi.integration;
import java.util.HashMap;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.Sale;
/**
 * Represents an external inventory system containing a set of items.
 * This class simulates access to an external inventory database.
 */
 public class ExternalInventorySystem {
    private final HashMap<ItemID, ItemDTO> inventory = new HashMap<>();

    /**
     * Creates a new instance of the external inventory system and initializes
     * it with two sample items for simulation purposes.
     */
    ExternalInventorySystem () {
       inventory.put(new ItemID("abc123"), new ItemDTO(new ItemID("abc123"), "BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free", new Amount(30.0), new Amount(6.0), "Big Wheel Oatmeal"));
       inventory.put(new ItemID("def456"), new ItemDTO(new ItemID("def456"), "YouGoGo Blueberry 240 g , low sugar youghurt , blueberry flavour", new Amount(20.0), new Amount(6.0), "YouGoGo Blueberry"));
       inventory.put(new ItemID("crachDB"), new ItemDTO(new ItemID("crachDB"), "crach", new Amount(0.0), new Amount(0.0), "crach"));
    }

    /**
     * Finds an item in the inventory based on its ID.
     * 
     * @param id The unique identifier of the item.
     * @return The <code>ItemDTO</code> representing the item.
     * @throws InvalidIDException if no item with the specified ID exists in the inventory.
     * @throws DatabaseConnectionFailureException if the specified ID simulates a database failure. 
     */
    public ItemDTO findItem(ItemID id) throws InvalidIDException{
        if(id.equals((new ItemID("crachDB")))){
            throw new DatabaseConnectionFailureException("Could not connect to the inventory database.");
        }
        ItemDTO foundItem = inventory.get(id);
        if (foundItem == null) {
            throw new InvalidIDException("Item with ID " + id + " not found in inventory.");
        }
        return foundItem;
    }

    /**
     * Update the inventorySystem.
     * @param sale The sale to update inventory with.
     */
    public void updateInventory (Sale sale) {
        
    }

}
