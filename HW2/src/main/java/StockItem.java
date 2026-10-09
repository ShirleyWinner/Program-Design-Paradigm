import java.util.Objects;

/** A product and its current inventory quantity. */
public class StockItem {
    private final Product product;
    private int quantity;

    /** Creates a stock entry.
     * @param product stocked product
     * @param quantity nonnegative initial quantity
     * @throws IllegalArgumentException if quantity is negative
     */
    public StockItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "product");
        if (quantity < 0) {
            throw new IllegalArgumentException("Negative stock quantity");
        }
        this.quantity = quantity;
    }

    /** @return stocked product */
    public Product getProduct() {
        return product;
    }

    /** @return current quantity */
    public int getQuantity() {
        return quantity;
    }

    /** Checks availability; a zero quantity is allowed.
     * @param requestedQuantity nonnegative requestedQuantity quantity
     * @return whether enough stock is available
     * @throws IllegalArgumentException if requestedQuantity is negative
     */
    public boolean hasEnoughStock(int requestedQuantity) {
        if (requestedQuantity < 0) {
            throw new IllegalArgumentException("Negative purchase quantity");
        }
        return requestedQuantity <= quantity;
    }

    /** Purchases units, leaving stock unchanged if the purchase fails.
     * @param requestedQuantity nonnegative requestedQuantity quantity; zero is a no-op
     * @throws InsufficientStockException if there is insufficient stock
     * @throws IllegalArgumentException if requestedQuantity is negative
     */
    public void purchase(int requestedQuantity) throws InsufficientStockException {
        if (!hasEnoughStock(requestedQuantity)) {
            throw new InsufficientStockException("Requested " + requestedQuantity + ", available " + quantity);
        }
        quantity -= requestedQuantity;
    }
}
