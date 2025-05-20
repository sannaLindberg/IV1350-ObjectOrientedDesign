package se.kth.iv1350.posi.view;

import se.kth.iv1350.posi.controller.Controller;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.model.Amount;

/**
 * This program has no view, instead, this class is a placeholder for the entire
 * view.
 */
public class View {
    private final Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */

    public View (Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */

    public void sampleExecution() {
       contr.startSale();
       
       ItemID firsItemID = new ItemID("abc123");
       System.out.println("Add 1 item with item id " + firsItemID + " :" +"\n"+ contr.addItem(firsItemID)+"\n");
       System.out.println("Total cost (incl VAT): " + contr.getCurRunningTotal()  + "\n" + "Total VAT :" + contr.getCurRunningVat()+"\n");

       ItemID secondItemID = new ItemID("def456");
       System.out.println("Add 1 item with item id " + secondItemID + " :" +"\n"+ contr.addItem(secondItemID)+"\n");
       System.out.println("Total cost (incl VAT): " + contr.getCurRunningTotal()  + "\n" + "Total VAT :" + contr.getCurRunningVat()+"\n");

       ItemID sameItemID = new ItemID("abc123");
       System.out.println("Add 1 item with item id " + sameItemID + " :" +"\n"+ contr.addItem(sameItemID)+"\n");
       System.out.println("Total cost (incl VAT): " + contr.getCurRunningTotal()  + "\n" + "Total VAT :" + contr.getCurRunningVat()+"\n");
    
       Amount saleTotal = new Amount();
       saleTotal = contr.endSale();
       System.out.println("End Sale"+"\n"+ "Total incl. VAT: " + saleTotal);
       
       //Amount change = new Amount();
      // change = contr.enterPayment(new Amount(100.0));
       System.out.println("change to give to the customer: " + contr.enterPayment(new Amount(100.0)));

    }
}