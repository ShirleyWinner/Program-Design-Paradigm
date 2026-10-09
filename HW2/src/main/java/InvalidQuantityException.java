/** Unchecked exception for a zero or negative cart quantity. */
public class InvalidQuantityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Creates an exception explaining the invalid quantity.
     * @param message explanation
     */
    public InvalidQuantityException(String message) {
        super(message);
    }
}
