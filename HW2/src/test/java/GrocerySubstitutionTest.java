import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/** Tests grocery rules and replacing cart entries through Grocery. */
class GrocerySubstitutionTest {
    /** Static inventory must be reset before each test. */
    @BeforeEach void resetStock() {
        Inventory.groceryStock.clear();
        Inventory.householdStock.clear();
    }

    /** Checks type, price, weight and inclusive boundaries. */
    @Test void candidateRules() {
        Grocery original = new Beer("A", "original", 5, 12);
        assertTrue(new Beer("B", "candidate", 5, 12).isAcceptableSubstituteFor(original));
        assertFalse(new Beer("B", "candidate", 6, 12).isAcceptableSubstituteFor(original));
        assertFalse(new Beer("B", "candidate", 4, 10).isAcceptableSubstituteFor(original));
        assertFalse(new Cheese("B", "candidate", 4, 14).isAcceptableSubstituteFor(original));
        assertFalse(original.isAcceptableSubstituteFor(null));
        assertFalse(original.isAcceptableSubstituteFor(new Household("A", "soap", 1, 1)));
    }

    /** Replaces only one unit, checks cumulative candidate quantity and preserves stock. */
    @Test void replacesInCart() throws InsufficientStockException {
        Grocery original = new Beer("A", "original", 5, 12);
        Grocery candidate = new Beer("B", "candidate", 4, 14);
        StockItem originalStock = new StockItem(original, 2);
        StockItem emptyStock = new StockItem(new Beer("C", "empty", 3, 14), 0);
        StockItem candidateStock = new StockItem(candidate, 1);
        Inventory inventory = new Inventory();
        inventory.addStockItem(originalStock);
        inventory.addStockItem(emptyStock);
        inventory.addStockItem(candidateStock);
        ShoppingCart cart = new ShoppingCart(new ShoppingCartTest.PromotionStub(null, 0));
        cart.addProduct(inventory, original, 2);
        assertTrue(original.replaceInCart(original, cart));
        assertEquals(candidate, cart.getProducts().get(0));
        assertEquals(original, cart.getProducts().get(1));
        assertEquals(9, cart.calculateTotal());
        assertFalse(original.replaceInCart(original, cart));
        assertEquals(1, candidateStock.getQuantity());
        assertEquals(2, originalStock.getQuantity());
        ShoppingCart emptyCart = new ShoppingCart(new ShoppingCartTest.PromotionStub(null, 0));
        assertFalse(original.replaceInCart(original, emptyCart));
    }
}
