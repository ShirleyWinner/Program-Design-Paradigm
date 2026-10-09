import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests cart behavior with a hand-written pricing stub. */
class ShoppingCartTest {
    /** Clears shared static stock so each test starts independently. */
    @BeforeEach
    void resetStock() {
        Inventory.groceryStock.clear();
        Inventory.householdStock.clear();
    }

    /** Predictable local test double; no external service is called. */
    static class PromotionStub implements PromotionService {
        private final Product discountedProduct;
        private final double discount;
        /** Creates a stub for one discounted product. */
        PromotionStub(Product discountedProduct, double discount) {
            this.discountedProduct = discountedProduct;
            this.discount = discount;
        }
        /** Returns the configured discount for the selected product. */
        @Override
        public double getDiscountPercentage(Product product) {
            if (product == discountedProduct) {
                return discount;
            }
            return 0;
        }
    }

    /** Checks both stock categories, unit counts and discounted total. */
    @Test void discountedTotalAndUnchangedStock() throws InsufficientStockException {
        Product grocery = new Grocery("maker", "food", 10, 8);
        Product household = new Household("maker", "soap", 5, 1);
        StockItem groceryStock = new StockItem(grocery, 4);
        StockItem householdStock = new StockItem(household, 2);
        Inventory inventory = new Inventory();
        inventory.addStockItem(groceryStock);
        inventory.addStockItem(householdStock);
        ShoppingCart cart = new ShoppingCart(new PromotionStub(grocery, 0.20));
        assertEquals(0, cart.calculateTotal());
        cart.addProduct(inventory, grocery, 2);
        cart.addProduct(inventory, household);
        assertEquals(21, cart.calculateTotal(), 0.000001);
        assertEquals(3, cart.getProducts().size());
        assertEquals(4, groceryStock.getQuantity());
        assertEquals(2, householdStock.getQuantity());
        assertThrows(UnsupportedOperationException.class, () -> cart.getProducts().clear());
    }

    /** A failed addition leaves the cart usable and checks cumulative quantities. */
    @Test void recoverAfterStockFailure() throws InsufficientStockException {
        Product product = new Grocery("maker", "food", 10, 8);
        Inventory inventory = new Inventory();
        inventory.addStockItem(new StockItem(product, 3));
        ShoppingCart cart = new ShoppingCart(new PromotionStub(product, 0));
        assertThrows(InsufficientStockException.class, () -> cart.addProduct(inventory, product, 4));
        assertTrue(cart.getProducts().isEmpty());
        cart.addProduct(inventory, product, 2);
        assertThrows(InsufficientStockException.class, () -> cart.addProduct(inventory, product, 2));
        assertEquals(2, cart.getProducts().size());
        cart.addProduct(inventory, product);
        assertEquals(3, cart.getProducts().size());
        Product unavailable = new Grocery("maker", "other", 1, 1);
        assertThrows(InsufficientStockException.class, () -> cart.addProduct(inventory, unavailable));
    }

    /** Invalid quantities are reported before stock availability is considered. */
    @Test void invalidQuantity() {
        ShoppingCart cart = new ShoppingCart(new PromotionStub(null, 0));
        Product product = new Grocery("maker", "food", 1, 1);
        Inventory inventory = new Inventory();
        assertThrows(InvalidQuantityException.class, () -> cart.addProduct(inventory, product, 0));
        assertThrows(InvalidQuantityException.class, () -> cart.addProduct(inventory, product, -1));
        assertTrue(cart.getProducts().isEmpty());
    }

    /** Checks no discount, full discount and malformed service responses. */
    @Test void discountBoundaries() throws InsufficientStockException {
        Product product = new Grocery("maker", "food", 10, 8);
        Inventory inventory = new Inventory();
        inventory.addStockItem(new StockItem(product, 1));
        ShoppingCart freeCart = new ShoppingCart(new PromotionStub(product, 1));
        freeCart.addProduct(inventory, product);
        assertEquals(0, freeCart.calculateTotal());
        ShoppingCart invalidCart = new ShoppingCart(new PromotionStub(product, Double.NaN));
        invalidCart.addProduct(inventory, product);
        assertThrows(IllegalArgumentException.class, () -> invalidCart.calculateTotal());
    }
}
