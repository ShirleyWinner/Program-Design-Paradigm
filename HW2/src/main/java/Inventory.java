import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Grocery and household stock entries maintained in separate lists. */
public class Inventory {
    private final List<StockItem> groceryStock = new ArrayList<>();
    private final List<StockItem> householdStock = new ArrayList<>();

    /** Creates an empty inventory. */
    public Inventory() {
    }

    /** Adds a new entry to the appropriate category; this does not replenish existing entries.
     * @param stockItem new stock entry
     * @throws IllegalArgumentException if the category is unsupported or the entry is already tracked
     */
    public void addStockItem(StockItem stockItem) {
        Objects.requireNonNull(stockItem, "stockItem");
        if (groceryStock.contains(stockItem) || householdStock.contains(stockItem)) {
            throw new IllegalArgumentException("Stock entry already tracked");
        }
        Product product = stockItem.getProduct();
        if (product instanceof Grocery) {
            groceryStock.add(stockItem);
        } else if (product instanceof Household) {
            householdStock.add(stockItem);
        } else {
            throw new IllegalArgumentException("Unsupported product category");
        }
    }

    /** @return unmodifiable snapshot of grocery entries */
    public List<StockItem> getGroceryStock() {
        return List.copyOf(groceryStock);
    }

    /** @return unmodifiable snapshot of household entries */
    public List<StockItem> getHouseholdStock() {
        return List.copyOf(householdStock);
    }

    /** @return total current retail value across both categories */
    public double getTotalRetailValue() {
        double totalRetailValue = 0;

        for (StockItem stockItem : groceryStock) {
            double price = stockItem.getProduct().getPrice();
            int quantity = stockItem.getQuantity();
            totalRetailValue += price * quantity;
        }

        for (StockItem stockItem : householdStock) {
            double price = stockItem.getProduct().getPrice();
            int quantity = stockItem.getQuantity();
            totalRetailValue += price * quantity;
        }

        return totalRetailValue;
    }
}
