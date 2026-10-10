import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/** Tests age checks, receipts, promotions and completed carts. */
class OrderReceiptTest {
    /** Clears shared stock for test isolation. */
    @BeforeEach void resetStock() {
        Inventory.groceryStock.clear();
        Inventory.householdStock.clear();
    }

    /** Normal business failures are returned alongside successful purchases. */
    @Test void mixedOrder() throws InsufficientStockException {
        Inventory inventory = new Inventory();
        Product original = new Cheese("A", "cheese", 5, 8);
        Product replacement = new Cheese("B", "cheese", 4, 10);
        Product unavailable = new Salmon("A", "salmon", 10, 12);
        Product beer = new Beer("A", "beer", 3, 12);
        StockItem originalStock = new StockItem(original, 2);
        StockItem replacementStock = new StockItem(replacement, 1);
        StockItem unavailableStock = new StockItem(unavailable, 1);
        StockItem beerStock = new StockItem(beer, 1);
        inventory.addStockItem(originalStock);
        inventory.addStockItem(replacementStock);
        inventory.addStockItem(unavailableStock);
        inventory.addStockItem(beerStock);
        Customer customer = new Customer("Alex", 20,
                new ShoppingCartTest.PromotionStub(replacement, 0.25));
        customer.addProductToCart(inventory, original, 2);
        customer.addProductToCart(inventory, unavailable);
        customer.addProductToCart(inventory, beer);
        originalStock.purchase(2);
        unavailableStock.purchase(1);
        Receipt receipt = customer.processOrder();
        assertEquals(3, receipt.getTotalPricePaid());
        assertEquals(1, receipt.getReceivedProducts().size());
        assertEquals(replacement, receipt.getReceivedProducts().get(0));
        assertEquals(2, receipt.getUnavailableProducts().size());
        assertEquals(original, receipt.getUnavailableProducts().get(0));
        assertEquals(unavailable, receipt.getUnavailableProducts().get(1));
        assertEquals(beer, receipt.getAgeRestrictedProducts().get(0));
        assertEquals(1, beerStock.getQuantity());
        assertEquals(0, replacementStock.getQuantity());
        assertTrue(customer.getCart().getProducts().isEmpty());
        Receipt emptyReceipt = customer.processOrder();
        assertEquals(0, emptyReceipt.getTotalPricePaid());
        assertTrue(emptyReceipt.getReceivedProducts().isEmpty());
        assertEquals(1, beerStock.getQuantity());
    }

    /** Age is checked against the resolved substitute, and the exact boundary is allowed. */
    @Test void resolvedAgeAndBoundary() throws InsufficientStockException {
        Inventory inventory = new Inventory();
        Product original = new Grocery("A", "item", 5, 0, 8);
        Product replacement = new Grocery("B", "item", 4, 21, 10);
        StockItem originalStock = new StockItem(original, 1);
        StockItem replacementStock = new StockItem(replacement, 1);
        inventory.addStockItem(originalStock);
        inventory.addStockItem(replacementStock);
        Customer young = new Customer("Young", 20, new ShoppingCartTest.PromotionStub(null, 0));
        young.addProductToCart(inventory, original);
        originalStock.purchase(1);
        Receipt rejected = young.processOrder();
        assertEquals(replacement, rejected.getAgeRestrictedProducts().get(0));
        assertTrue(rejected.getReceivedProducts().isEmpty());
        assertEquals(1, replacementStock.getQuantity());
        assertTrue(young.getCart().getProducts().isEmpty());
        Customer adult = new Customer("Adult", 21, new ShoppingCartTest.PromotionStub(null, 0));
        adult.addProductToCart(inventory, replacement);
        Receipt accepted = adult.processOrder();
        assertEquals(4, accepted.getTotalPricePaid());
        assertTrue(accepted.getAgeRestrictedProducts().isEmpty());
        assertEquals(0, replacementStock.getQuantity());
    }
}
