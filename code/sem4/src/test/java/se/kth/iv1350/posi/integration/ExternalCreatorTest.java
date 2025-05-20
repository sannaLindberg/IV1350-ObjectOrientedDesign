package se.kth.iv1350.posi.integration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ExternalCreatorTest {
    @Test
    public void testCreateExternalInventorySystem() {
        ExternalCreator instance = new ExternalCreator();
        ExternalInventorySystem result = instance.getEis();
        assertTrue(result instanceof ExternalInventorySystem, "RegistryCreator did not create RentalRegistry");
    }

    @Test
    public void testCreateExternalAccountingSystem() {
        ExternalCreator instance = new ExternalCreator();
        ExternalAccountingSystem result = instance.getEas();
        assertTrue(result instanceof ExternalAccountingSystem, "RegistryCreator did not create CarRegistry");
    }
}
