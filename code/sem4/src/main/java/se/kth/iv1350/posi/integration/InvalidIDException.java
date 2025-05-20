package se.kth.iv1350.posi.integration;

/**
 * This exeption is thrown when an attempt is made to retrive an item 
 * with a invalid or non-existent identifier.
*/
public class InvalidIDException extends Exception{

    /**
    * Creates a new instance with the specified message.
    *
     * @param errorMsg The message that describes the reason for the exception.
     */
    public InvalidIDException(String errorMsg){
        super(errorMsg);
    }
}
