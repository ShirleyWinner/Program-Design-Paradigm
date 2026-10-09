/** Checked exception raised when a purchase exceeds available stock. */
public class InsufficientStockException extends Exception {
    private static final long serialVersionUID = 1L;

    /** Creates an exception describing the shortage.
     * @param message shortage description
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
