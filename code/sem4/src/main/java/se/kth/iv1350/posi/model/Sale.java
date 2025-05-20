package se.kth.iv1350.posi.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.kth.iv1350.posi.integration.ItemID;
/**
 * Represents one particular sale transaction.
 */
public class Sale {
    private Map<ItemID, Item> items = new HashMap<>();
    private CashPayment payment;
    private Amount runningVAT = new Amount(0);
    private Amount runningTotal = new Amount(0);
    private List<TotalRevenueObserver> saleObservers = new ArrayList<>();

    /**
     * Adds an item to the current sale. if item is already scanned, its quantity is increased.
     * The runningTotal is updated with items price incl VAT and runningVAT is updatet.
     * 
     * @param item The item added to the sale.
     */

    public void addItemToList(Item item) {
        ItemID id = item.getItemID();
        if (items.containsKey(id)) {
            items.get(id).increaseQuantity();
        } else {
            items.put(id, item);
        }
        calculateRunningTotal(item);
        calculateRunningVAT(item);
    }

    /**
     * Calculates and updates the running total incl VAT 
     * 
     * @param item The item whose price will be added to the running total.
     */
    public void calculateRunningTotal(Item item) {
        Amount itemTotal = item.getItemDTO().getPriceWithVAT();
        runningTotal =  runningTotal.add(itemTotal);      
    }

    /** 
     * Calculates and updates the total VAT amount of the sale.
     * 
     * @param item The item whose VAT will be added to the running VAT.
     */
    public void calculateRunningVAT(Item item) {
        Amount itemVAT = item.getItemDTO().calculateVATAmount().multiply((item.getQuantity()).getAmount());
        runningVAT = runningVAT.add(itemVAT); 
       
    }

    /**
     * Gets the running total for this sale.
     * 
     * @return the <code>Amount</code> running total.
     */
    public Amount getRunningTotal(){
        return runningTotal;
    }

    /**
     * Gets the running total for this sale.
     * 
     * @return the <code>Amount</code> running total.
     */
    public Amount getRunningVat(){
        return runningVAT;
    }

    /**
     * Registers the payment for the sale.
     * 
     * @param payment the <code>CashPayment</code> representing the amount payed by the customer.
     */
    public void processPayment (CashPayment payment) {
        notifyObservers();
        this.payment = payment;
    }

    /**
     * Gets the cash payment for this sale.
     * 
     * @return the <code>CashPayment</code> payment.
     */
    public CashPayment getPayment(){
        return payment;
    }

    /**
     * Adds a list of observers that will be notified when a sale is completed.
     * 
     * @param obs a list of TotalRevenueObserver instences to be registered.
     */
    public void addSaleObservers (List<TotalRevenueObserver> obs) {
        saleObservers.addAll(obs);

    }

   /**
    * Notifies all registered observers about the completed sale.
    */
    private void notifyObservers () {
        for (TotalRevenueObserver sObs : saleObservers) {
            sObs.printTotalRevenue(getRunningTotal());
        }
    }

    /**
     * Gets the map of all the current items of the sale.
     * 
     * @return A map of <code>ItemID</code> to <code>Item</code>:
     */
    public Map<ItemID, Item> getItems() {
        return items;
    }

}
