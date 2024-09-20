package hinlok.exceptions;

/**
 * Represents an exception to hinlok
 */
public class HinlokException extends Exception {

    /**
     * Constructs a exception with a message
     * @param message error message
     */
    public HinlokException(String message) {
        super(message);
    }
}
