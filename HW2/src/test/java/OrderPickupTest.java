import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/** Tests pickup with stock changes after cart additions. */
class OrderPickupTest {
    /** Resets shared inventory. */
    @BeforeEach void resetStock() {
        Inventory.groceryStock.clear();
        Inventory.householdStock.clear();
    }

    /** Uses remaining originals, rejects invalid candidates and removes the excess unit. */
    @Test void groceryPickup() throws InsufficientStockException {
        Inventory inventory = new Inventory();
        Product original = new Cheese("A", "original", 5, 12);
        StockItem originalStock = new StockItem(original, 4);
        StockItem wrongType = new StockItem(new Beer("B", "wrong", 3, 14), 5);
        StockItem expensive = new StockItem(new Cheese("B", "expensive", 6, 14), 5);
        StockItem small = new StockItem(new Cheese("B", "small", 4, 10), 5);
        StockItem empty = new StockItem(new Cheese("B", "empty", 4, 14), 0);
        Product candidate = new Cheese("B", "candidate", 4, 14);
        StockItem candidateStock = new StockItem(candidate, 2);
        inventory.addStockItem(originalStock);
        inventory.addStockItem(wrongType);
        inventory.addStockItem(expensive);
        inventory.addStockItem(small);
        inventory.addStockItem(empty);
        inventory.addStockItem(candidateStock);
        ShoppingCart cart = new ShoppingCart(new ShoppingCartTest.PromotionStub(null, 0));
        cart.addProduct(inventory, original, 4);
        originalStock.purchase(3);
        Receipt receipt = cart.processOrder(inventory, 30);
        assertEquals(3, receipt.getReceivedProducts().size());
        assertEquals(original, receipt.getReceivedProducts().get(0));
        assertEquals(candidate, receipt.getReceivedProducts().get(1));
        assertEquals(candidate, receipt.getReceivedProducts().get(2));
        assertEquals(0, originalStock.getQuantity());
        assertEquals(0, candidateStock.getQuantity());
        assertEquals(5, wrongType.getQuantity());
        assertEquals(5, expensive.getQuantity());
        assertEquals(5, small.getQuantity());
        assertEquals(13, receipt.getTotalPricePaid());
    }

    /** Household equality boundaries are accepted and consecutive unavailable units are removed. */
    @Test void householdPickup() throws InsufficientStockException {
        Inventory inventory = new Inventory();
        Household original = new Household("A", "soap", 5, 2);
        Household candidate = new Household("B", "soap", 5, 2);
        assertTrue(candidate.isAcceptableSubstituteFor(original));
        assertFalse(new Household("B", "soap", 6, 2).isAcceptableSubstituteFor(original));
        assertFalse(new Household("B", "soap", 4, 1).isAcceptableSubstituteFor(original));
        assertFalse(candidate.isAcceptableSubstituteFor(null));
        assertFalse(candidate.isAcceptableSubstituteFor(new Grocery("B", "food", 1, 10)));
        StockItem originalStock = new StockItem(original, 3);
        StockItem candidateStock = new StockItem(candidate, 1);
        inventory.addStockItem(originalStock);
        inventory.addStockItem(candidateStock);
        ShoppingCart cart = new ShoppingCart(new ShoppingCartTest.PromotionStub(null, 0));
        cart.addProduct(inventory, original, 3);
        originalStock.purchase(3);
        Receipt receipt = cart.processOrder(inventory, 30);
        assertEquals(1, receipt.getReceivedProducts().size());
        assertEquals(candidate, receipt.getReceivedProducts().get(0));
        assertEquals(0, candidateStock.getQuantity());
    }

    /** Empty carts and fully available originals need no substitution. */
    @Test void availableOriginal() throws InsufficientStockException {
        Inventory inventory = new Inventory();
        ShoppingCart cart = new ShoppingCart(new ShoppingCartTest.PromotionStub(null, 0));
        cart.processOrder(inventory, 30);
        Product original = new Salmon("A", "salmon", 5, 12);
        StockItem stock = new StockItem(original, 1);
        StockItem cheaper = new StockItem(new Salmon("B", "salmon", 4, 14), 1);
        inventory.addStockItem(stock);
        inventory.addStockItem(cheaper);
        cart.addProduct(inventory, original);
        Receipt receipt = cart.processOrder(inventory, 30);
        assertEquals(original, receipt.getReceivedProducts().get(0));
        assertEquals(0, stock.getQuantity());
        assertEquals(1, cheaper.getQuantity());
    }
}
