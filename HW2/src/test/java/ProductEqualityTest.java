import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests value equality and inventory matching with separate product objects. */
class ProductEqualityTest {
    /** Clears shared static stock so each test starts independently. */
    @BeforeEach
    void resetStock() {
        Inventory.groceryStock.clear();
        Inventory.householdStock.clear();
    }

    /** Equal products obey symmetry, transitivity and the hash contract. */
    @Test void equalProducts() {
        Product first = new Beer("Brand", "Beer", 5, 12);
        Product second = new Beer("Brand", "Beer", 5, 12);
        Product third = new Beer("Brand", "Beer", 5, 12);
        assertNotSame(first, second);
        assertEquals(first, first);
        assertEquals(first, second);
        assertEquals(second, first);
        assertEquals(second, third);
        assertEquals(first, third);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, null);
        assertNotEquals(first, "Beer");
        assertNotEquals(first, new Grocery("Brand", "Beer", 5, 12));
        assertNotEquals(first, new Beer("Other", "Beer", 5, 12));
        assertNotEquals(first, new Beer("Brand", "Other", 5, 12));
        assertNotEquals(first, new Beer("Brand", "Beer", 6, 12));
        assertNotEquals(first, new Beer("Brand", "Beer", 5, 16));
        assertNotEquals(new Grocery("m", "n", 1, 0, 8), new Grocery("m", "n", 1, 18, 8));
        Product soap = new Household("Brand", "Soap", 5, 2);
        Product otherSoap = new Household("Brand", "Soap", 5, 2);
        assertEquals(soap, otherSoap);
        assertEquals(soap.hashCode(), otherSoap.hashCode());
        assertNotEquals(soap, new Household("Brand", "Soap", 5, 3));
    }

    /** Separately constructed equal products match stock and share a cart quantity limit. */
    @Test void matchesStockByValue() throws InsufficientStockException {
        Product stockedBeer = new Beer("Brand", "Beer", 5, 12);
        Product selectedBeer = new Beer("Brand", "Beer", 5, 12);
        StockItem stockItem = new StockItem(stockedBeer, 2);
        Inventory inventory = new Inventory();
        inventory.addStockItem(stockItem);
        ShoppingCart cart = new ShoppingCart(new ShoppingCartTest.PromotionStub(null, 0));
        cart.addProduct(inventory, selectedBeer);
        cart.addProduct(inventory, new Beer("Brand", "Beer", 5, 12));
        assertThrows(InsufficientStockException.class,
                () -> cart.addProduct(inventory, stockedBeer));
        assertEquals(2, cart.getProducts().size());
        assertEquals(2, stockItem.getQuantity());
        assertEquals(10, cart.calculateTotal());
        Product stockedSoap = new Household("Brand", "Soap", 3, 2);
        inventory.addStockItem(new StockItem(stockedSoap, 1));
        cart.addProduct(inventory, new Household("Brand", "Soap", 3, 2));
        assertEquals(13, cart.calculateTotal());
    }
}
