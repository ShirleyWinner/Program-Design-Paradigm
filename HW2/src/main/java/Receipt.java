import java.util.List;

/** Immutable summary of purchased and removed order items, one entry per unit. */
public class Receipt {
    private final double totalPricePaid;
    private final List<Product> receivedProducts;
    private final List<Product> unavailableProducts;
    private final List<Product> ageRestrictedProducts;

    /** Creates a snapshot of the completed order.
     * @param totalPricePaid total discounted price paid
     * @param receivedProducts products actually purchased
     * @param unavailableProducts originals without an available substitute
     * @param ageRestrictedProducts resolved products rejected by the age requirement
     */
    public Receipt(double totalPricePaid, List<Product> receivedProducts,
                   List<Product> unavailableProducts, List<Product> ageRestrictedProducts) {
        this.totalPricePaid = totalPricePaid;
        this.receivedProducts = List.copyOf(receivedProducts);
        this.unavailableProducts = List.copyOf(unavailableProducts);
        this.ageRestrictedProducts = List.copyOf(ageRestrictedProducts);
    }

    /** @return total price paid after promotions */
    public double getTotalPricePaid() {
        return totalPricePaid;
    }

    /** @return immutable list of received products */
    public List<Product> getReceivedProducts() {
        return receivedProducts;
    }

    /** @return immutable list of unavailable original products */
    public List<Product> getUnavailableProducts() {
        return unavailableProducts;
    }

    /** @return immutable list of products removed because of the customer's age */
    public List<Product> getAgeRestrictedProducts() {
        return ageRestrictedProducts;
    }
}
