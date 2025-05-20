package se.kth.iv1350.posi.view;
import se.kth.iv1350.posi.integration.InvalidIDException;
import se.kth.iv1350.posi.controller.Controller;
import se.kth.iv1350.posi.controller.OperationFailedException;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.util.LogHandler;
import java.io.IOException;
/**
 * This program has no view, instead, this class is a placeholder for the entire
 * view.
 */
public class View {
    private final Controller contr;
    private final TotalRevenueFileOutput totalRevenueFileOutput;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */
    public View (Controller contr) throws IOException {
        this.contr = contr;
        this.logger = new LogHandler();
        this.totalRevenueFileOutput = new TotalRevenueFileOutput();
            contr.saleObservers(totalRevenueFileOutput);
            contr.saleObservers(new TotalRevenueView());
    }

    /**
     * Starts a new sale and prints to the user.
     */
    public void startSale(){
        contr.startSale();
        System.out.println("New Sale");
    }

    /**
     * Adds new item to the sale, gets running total and running VAT.
     * catches exception if the item is not found or the inventory system is down it 
     * @param itemID
     */
    public void scannNewItem(ItemID itemID){
        try{
        System.out.println("Add 1 item with item id " + itemID + " :" +"\n"+ contr.addItem(itemID)+"\n");
        System.out.println("Total cost (incl VAT): " + contr.getCurRunningTotal()  + "\n" + "Total VAT :" + contr.getCurRunningVat()+"\n");
        }catch (InvalidIDException e) {
            errorMsgHandler.showErrorMsg("The itemID : " + itemID + " is invalid, scann different item ");
        } catch (OperationFailedException exc) {
            writeToLogAndUI("could not add item because of system failure", exc);   
        }
    }
    /**
     * ends sale, prints the sale total and change amount to the user.
     */
    public void endSale(){
       Amount saleTotal = new Amount();
       saleTotal = contr.endSale();
       System.out.println("End Sale"+"\n"+ "Total incl. VAT: " + saleTotal);
       System.out.println("change to give to the customer: " + contr.enterPayment(new Amount(100.0)));
       totalRevenueFileOutput.printFinalTotal();
    }
    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void sampleExecution() {
        startSale();
        scannNewItem(new ItemID("abc123"));
        scannNewItem(new ItemID("def456"));
        scannNewItem(new ItemID("invalidID"));
        scannNewItem(new ItemID("abc123"));
        endSale();

        startSale();
        scannNewItem(new ItemID("abc123"));
        scannNewItem(new ItemID("def456"));
        scannNewItem(new ItemID("invalidID"));
        scannNewItem(new ItemID("abc123"));
        scannNewItem(new ItemID("crachDB"));
        endSale();
        

    }
     
    private void writeToLogAndUI(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }
}