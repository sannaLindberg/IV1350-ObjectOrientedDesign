package se.kth.iv1350.posi.integration; 
import java.util.Objects;
/**
 * Represents a unique identifier for an item.
 */
public class ItemID {
    private final String id;

    /**
     * Creates a new instance of ItemID.
     *
     * @param id A unique string identifier for the item.
     */
    public ItemID(String id) {
        this.id = id;
    }

    /**
     * Returns the string representation of this item ID.
     *
     * @return The string ID.
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * Compares this ItemID to another object to determine equality.
     * Two ItemID objects are considered equal if their string IDs are equal.
     *
     * @param obj The object to compare to.
     * @return <code>true</code> if the objects are of the same class and have the same ID, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemID itemID = (ItemID) obj;
        return Objects.equals(id, itemID.id);
    }

     /**
     * Computes a hash code for this ItemID based on its string ID.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}