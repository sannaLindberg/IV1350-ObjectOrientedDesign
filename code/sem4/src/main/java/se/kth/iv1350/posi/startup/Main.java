package se.kth.iv1350.posi.startup;

import java.io.IOException;

import se.kth.iv1350.posi.controller.Controller;
import se.kth.iv1350.posi.integration.Printer;
import se.kth.iv1350.posi.view.View;

public class Main {
     /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        try{
        Printer printer = new Printer();
        Controller contr = new Controller(printer);
        new View(contr).sampleExecution();
        } catch (IOException ex) {
            System.out.println("Unable to start the application");
            ex.printStackTrace();
        }
    }
}