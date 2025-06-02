package se.kth.iv1350.posi.controller;
/**
 * This exeption is thrown when an operation fails for a unknown reason.
*/
public class OperationFailedException extends Exception{
    
    /**
     * Creates a new instance with the specified message and root cause.
     *
     * @param msg   The exception message.
     * @param cause The exception that caused this exception.
     */
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}
