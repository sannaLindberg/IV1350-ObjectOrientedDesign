package se.kth.iv1350.posi.controller;

import se.kth.iv1350.posi.integration.ExternalAccountingSystem;
import se.kth.iv1350.posi.integration.ExternalCreator;
import se.kth.iv1350.posi.integration.ExternalInventorySystem;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.integration.Printer;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.CashPayment;
import se.kth.iv1350.posi.model.CashRegister;
import se.kth.iv1350.posi.model.Item;
import se.kth.iv1350.posi.model.Receipt;
import se.kth.iv1350.posi.model.Sale;

/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */
public class Controller {
    private Sale currentSale;
    private final Printer printer;
    private final CashRegister cashRegister;
    private final ExternalAccountingSystem eas;
    private final ExternalInventorySystem eis;

    /**
     * Creates a new instance of the Controller.
     * 
     * @param printer Interface to printer.
     * @param creator Used to get all classes that handle database calls.
     */
    public Controller(Printer printer, ExternalCreator creator) {
        this.printer = printer;
        this.eas = creator.getEas();
        this.eis = creator.getEis();
        this.cashRegister = new CashRegister(new Amount(1234));
    }

    /**
     * Starts a new sale.
     */
    public void startSale() {
        currentSale = new Sale();
    }


    /**ÄNDRAD
     * Retrieves the item data from inventory and adds 
     * the scanned item to the current sale 
     * 
     * @param itemID the identifier of the scanned item.
     * @return the <code>ItemDTO</code> containing the the scanned items data.
     */
    public ItemDTO addItem(ItemID itemID) {
        ItemDTO itemDTO = eis.findItem(itemID);
        currentSale.addItemToList(new Item (itemDTO));
        return itemDTO;     
    }

    /** NY
     * Retrives the current running total incl VAT of the current sale.
     * 
     * @return an <code>Amount</code> representing the running total.
     */
    public Amount getCurRunningTotal(){
        return currentSale.getRunningTotal();
    }

    /** NY
     * Retrives the current running VAT of the current sale.
     * 
     * @return an <code>Amount</code> representing the running VAT.
     */
    public Amount getCurRunningVat(){
        return currentSale.getRunningVat();
    }
        

     /**ÄNDRAD
      *  Ends the current sale and retrives the total amount of the sale.
      * @return an <code>Amount</code> representing the Total Amount.
      */
    public Amount endSale(){
        return currentSale.getRunningTotal();
    }

    /** ÄNDRAD
     * Processes the customer's payment and finalizes the sale.
     * 
     * @param paidAmount The amount paid by the customer.
     */

     /**ÄNDRAD 
      * Processes the customer's payment. Sends sale info to inventory
      * and accounting. Updates the balance of the cash register where
     * the payment was performed. Calculates change. Prints the receipt.

      * @param paidAmount The amount paid by the customer.
      * @return the <code>Amount</code> representing the change to give to the customer.
      */
    public Amount enterPayment(Amount paidAmount) {
        CashPayment payment = new CashPayment(paidAmount);
        currentSale.processPayment(payment);
        cashRegister.addAmount(paidAmount);
        eas.recordSale(currentSale);
        eis.updateInventory(currentSale);
        Receipt receipt = new Receipt(currentSale, paidAmount);
        printer.printReceipt(receipt);
        return payment.getChange(currentSale.getRunningTotal());
    }

}
