import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests StockItem. */
class StockItemTest {
    /** Verifies successful, exact-stock, zero and rejected purchases.
     * @throws InsufficientStockException if a valid purchase unexpectedly fails
     */
    @Test void purchaseBoundaries() throws InsufficientStockException {
        Product product = new Grocery("m", "n", 2, 5);
        StockItem stock = new StockItem(product, 3);
        assertSame(product, stock.getProduct());
        assertTrue(stock.hasEnoughStock(3));
        assertFalse(stock.hasEnoughStock(4));
        assertThrows(InsufficientStockException.class, () -> stock.purchase(4));
        assertEquals(3, stock.getQuantity());
        stock.purchase(0);
        assertEquals(3, stock.getQuantity());
        stock.purchase(1);
        assertEquals(2, stock.getQuantity());
        stock.purchase(2);
        assertEquals(0, stock.getQuantity());
        assertThrows(IllegalArgumentException.class, () -> stock.purchase(-1));
        assertEquals(0, stock.getQuantity());
        assertThrows(IllegalArgumentException.class, () -> new StockItem(product, -1));
        assertThrows(NullPointerException.class, () -> new StockItem(null, 1));
    }
}
