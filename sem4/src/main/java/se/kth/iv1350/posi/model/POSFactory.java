package se.kth.iv1350.posi.model;

//import se.kth.iv1350.posi.integration.ExternalAccountingSystem;
import se.kth.iv1350.posi.integration.ExternalCreator;
//import se.kth.iv1350.posi.integration.ExternalInventorySystem;

public class POSFactory {
    private static final POSFactory instance = new POSFactory();


    /**
     * 
     * private constructor that prevents external instantiation
     * 
     */
    private POSFactory() {}


    /**
     * provides access to the singleton instance
     * 
     * @return instance
     */
    public static POSFactory getInstance() {
        return instance;
    }
    
    /**
     * Creates a new instance of a cashregister with a specified initial balance.
     * 
     * @param balance the initial amount present in the cashregister
     * @return a new CashRegister initialized with the specified balance.
     */
    public CashRegister createCashRegister (Amount balance) {
        return new CashRegister(balance);
     }

    /**
     * creates a new instance of a cash payment with the specified paid amount.
     * 
     * @param paidAmount the amount of money paid by the customer.
     * @return a new cash payment with the initialized paid amount.
     */
    public CashPayment createCashPayment (Amount paidAmount) {
        return new CashPayment(paidAmount);
    }
     
    /**
     * Creates a new instance of a sale.
     * 
     * @return the new Sale.
     */
    public Sale createSale() {
        return new Sale();
      }

      
    /**
     * Creates a new instance of an external creator.
     * @return a new external creator.
     */
    public ExternalCreator createExternalCreator() {
        return new ExternalCreator();
    }
}
