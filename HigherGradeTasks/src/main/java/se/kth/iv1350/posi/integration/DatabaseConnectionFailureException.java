package se.kth.iv1350.posi.integration;
/**
 * Represents a simulated failure when trying to connect to a database.
 * This exeption is thrown when a database can not be accessed.
 */
public class DatabaseConnectionFailureException extends RuntimeException {
        /**
         * Creates a new instance representing the condition described in the specified message.
         *
         * @param msg A message that describes what went wrong.
         */
        public DatabaseConnectionFailureException(String msg) {
            super(msg);
        }
    }
