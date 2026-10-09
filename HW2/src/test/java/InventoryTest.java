import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Inventory. */
class InventoryTest {
    /** Checks all grocery subtypes, household classification and changing value.
     * @throws InsufficientStockException if a valid purchase unexpectedly fails
     */
    @Test void categoriesAndValue() throws InsufficientStockException {
        Inventory inventory = new Inventory();
        assertEquals(0, inventory.getTotalRetailValue());
        StockItem grocery = new StockItem(new Grocery("m", "g", 2, 5), 3);
        inventory.addStockItem(grocery);
        inventory.addStockItem(new StockItem(new Beer("m", "b", 4, 12), 1));
        inventory.addStockItem(new StockItem(new Salmon("m", "s", 5, 12), 1));
        inventory.addStockItem(new StockItem(new Cheese("m", "c", 3, 12), 1));
        inventory.addStockItem(new StockItem(new Household("m", "h", 10, 2), 2));
        assertEquals(4, inventory.getGroceryStock().size());
        assertEquals(1, inventory.getHouseholdStock().size());
        assertEquals(38, inventory.getTotalRetailValue());
        grocery.purchase(1);
        assertEquals(36, inventory.getTotalRetailValue());
        assertThrows(IllegalArgumentException.class, () -> inventory.addStockItem(grocery));
        assertThrows(UnsupportedOperationException.class, () -> inventory.getGroceryStock().clear());
        assertThrows(NullPointerException.class, () -> inventory.addStockItem(null));
        Product unsupported = new Product("m", "n", 1) { };
        assertThrows(IllegalArgumentException.class,
            () -> inventory.addStockItem(new StockItem(unsupported, 1)));
    }
}
