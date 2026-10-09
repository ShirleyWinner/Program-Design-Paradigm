import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests customer data and cart delegation. */
class CustomerTest {
    /** Clears shared static stock so each test starts independently. */
    @BeforeEach
    void resetStock() {
        Inventory.groceryStock.clear();
        Inventory.householdStock.clear();
    }

    /** A customer owns a usable cart with the injected service. */
    @Test void ownsCart() throws InsufficientStockException {
        PromotionService stub = new ShoppingCartTest.PromotionStub(null, 0);
        Customer customer = new Customer("Alex", 20, stub);
        assertEquals("Alex", customer.getName());
        assertEquals(20, customer.getAge());
        Product product = new Grocery("maker", "food", 2, 8);
        Inventory inventory = new Inventory();
        inventory.addStockItem(new StockItem(product, 3));
        customer.addProductToCart(inventory, product);
        customer.addProductToCart(inventory, product, 2);
        assertEquals(6, customer.getCart().calculateTotal());
        assertThrows(IllegalArgumentException.class, () -> new Customer("Alex", -1, stub));
    }
}
