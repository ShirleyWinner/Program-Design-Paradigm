import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Products selected by a customer; adding products does not reserve or reduce stock. */
public class ShoppingCart {
    private final List<Product> products;
    private final PromotionService promotionService;

    /** Creates an empty cart using the supplied pricing dependency.
     * @param promotionService external discount service
     */
    public ShoppingCart(PromotionService promotionService) {
        this.promotionService = Objects.requireNonNull(promotionService, "promotionService");
        this.products = new ArrayList<>();
    }

    /** Adds one unit after checking stock.
     * @param inventory store inventory
     * @param product product to add
     * @throws InsufficientStockException if the product is unavailable
     */
    public void addProduct(Inventory inventory, Product product)
            throws InsufficientStockException {
        addProduct(inventory, product, 1);
    }

    /** Adds units only if stock covers both existing and newly requested units.
     * @param inventory store inventory
     * @param product product to add; equal product attributes match the stock entry
     * @param quantity positive quantity to add
     * @throws InvalidQuantityException if quantity is zero or negative
     * @throws InsufficientStockException if total cart quantity exceeds available stock
     */
    public void addProduct(Inventory inventory, Product product, int quantity)
            throws InsufficientStockException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero");
        }
        Objects.requireNonNull(inventory, "inventory");
        Objects.requireNonNull(product, "product");

        // Find the stock entry for the product in either grocery or household stock.
        StockItem matchingStock = null;
        for (StockItem stockItem : inventory.groceryStock) {
            if (stockItem.getProduct().equals(product)) {
                matchingStock = stockItem;
                break;
            }
        }
        if (matchingStock == null) {
            for (StockItem stockItem : inventory.householdStock) {
                if (stockItem.getProduct().equals(product)) {
                    matchingStock = stockItem;
                    break;
                }
            }
        }
        if (matchingStock == null) {
            throw new InsufficientStockException("Product is not stocked");
        }

        int quantityAlreadyInCart = 0;
        for (Product cartProduct : products) {
            if (cartProduct.equals(product)) {
                quantityAlreadyInCart++;
            }
        }
        // Subtraction avoids overflowing when a very large quantity is requested.
        int remainingQuantity = matchingStock.getQuantity() - quantityAlreadyInCart;
        if (quantity > remainingQuantity) {
            throw new InsufficientStockException("Not enough stock for this product");
        }

        for (int itemNumber = 0; itemNumber < quantity; itemNumber++) {
            products.add(product);
        }
    }

    /** Replaces one unit if the candidate stock can cover the resulting cart quantity.
     * @param original product to replace
     * @param substitute replacement product
     * @param availableQuantity candidate's current stock quantity
     * @return whether one unit was replaced
     */
    public boolean replaceOneProduct(Product original, Product substitute, int availableQuantity) {
        Objects.requireNonNull(original, "original");
        Objects.requireNonNull(substitute, "substitute");
        if (substitute.equals(original) || availableQuantity <= 0) {
            return false;
        }

        int originalIndex = -1;
        int substituteQuantityInCart = 0;
        for (int index = 0; index < products.size(); index++) {
            Product product = products.get(index);
            if (originalIndex == -1 && product.equals(original)) {
                originalIndex = index;
            }
            if (product.equals(substitute)) {
                substituteQuantityInCart++;
            }
        }
        if (originalIndex == -1 || substituteQuantityInCart >= availableQuantity) {
            return false;
        }
        products.set(originalIndex, substitute);
        return true;
    }

    /** Resolves substitutions, checks age, purchases eligible units and empties the cart.
     * Unavailable and age-restricted products are recorded as normal receipt outcomes.
     * @param inventory current store inventory
     * @param customerAge nonnegative age of the customer
     * @return receipt containing purchased and removed products
     * @throws IllegalArgumentException if age or a promotional discount is invalid
     */
    public Receipt processOrder(Inventory inventory, int customerAge) {
        Objects.requireNonNull(inventory, "inventory");
        if (customerAge < 0) {
            throw new IllegalArgumentException("Customer age cannot be negative");
        }

        List<StockItem> allStock = new ArrayList<>();
        allStock.addAll(inventory.getGroceryStock());
        allStock.addAll(inventory.getHouseholdStock());

        // 1. Find an original product or an acceptable substitute for each cart unit.
        List<StockItem> selectedStock = new ArrayList<>();
        List<Product> unavailableProducts = new ArrayList<>();
        for (Product original : products) {
            StockItem selectedItem = findStockFor(original, allStock, selectedStock);
            if (selectedItem == null) {
                unavailableProducts.add(original);
            } else {
                selectedStock.add(selectedItem);
            }
        }
        // 2. Remove age-restricted products and price only the products being purchased.
        List<Product> receivedProducts = new ArrayList<>();
        List<Product> ageRestrictedProducts = new ArrayList<>();
        List<StockItem> purchasedStock = new ArrayList<>();
        double totalPricePaid = 0;
        for (StockItem stockItem : selectedStock) {
            Product product = stockItem.getProduct();
            if (customerAge < product.getAge()) {
                ageRestrictedProducts.add(product);
                continue;
            }
            double discount = promotionService.getDiscountPercentage(product);
            if (!Double.isFinite(discount) || discount < 0 || discount > 1) {
                throw new IllegalArgumentException("Discount must be between 0 and 1");
            }
            totalPricePaid += product.getPrice() * (1 - discount);
            receivedProducts.add(product);
            purchasedStock.add(stockItem);
        }

        // 3. Deduct purchased units, create the receipt and empty the cart.
        for (StockItem stockItem : purchasedStock) {
            try {
                stockItem.purchase(1);
            } catch (InsufficientStockException exception) {
                // Stock was checked above; a failure here means inventory changed unexpectedly.
                throw new IllegalStateException("Inventory changed during order processing", exception);
            }
        }
        Receipt receipt = new Receipt(totalPricePaid, receivedProducts,
                unavailableProducts, ageRestrictedProducts);
        products.clear();
        return receipt;
    }

    /** Finds available original stock first, then an acceptable substitute.
     * @param original requested product
     * @param allStock current inventory entries
     * @param selectedStock entries already allocated, one entry per unit
     * @return matching stock entry, or null when neither option is available
     */
    private StockItem findStockFor(Product original, List<StockItem> allStock,
                                  List<StockItem> selectedStock) {
        for (StockItem stockItem : allStock) {
            if (stockItem.getProduct().equals(original)
                    && hasUnallocatedStock(stockItem, selectedStock)) {
                return stockItem;
            }
        }

        for (StockItem stockItem : allStock) {
            if (!hasUnallocatedStock(stockItem, selectedStock)) {
                continue;
            }
            Product candidate = stockItem.getProduct();
            if (!(candidate instanceof Substitutable)) {
                continue;
            }
            Substitutable substitute = (Substitutable) candidate;
            if (substitute.isAcceptableSubstituteFor(original)) {
                return stockItem;
            }
        }
        return null;
    }

    /** Checks whether this stock entry still has an unallocated unit.
     * @param stockItem inventory entry to check
     * @param selectedStock entries already allocated, one entry per unit
     * @return whether another unit can be allocated
     */
    private boolean hasUnallocatedStock(StockItem stockItem, List<StockItem> selectedStock) {
        int allocatedQuantity = 0;
        for (StockItem selectedItem : selectedStock) {
            // Compare stock entry identity, rather than the attributes of its product.
            if (selectedItem == stockItem) {
                allocatedQuantity++;
            }
        }
        return allocatedQuantity < stockItem.getQuantity();
    }

    /** @return unmodifiable snapshot containing one product entry per unit */
    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    /** Computes the total using current discounts from the supplied service.
     * @return discounted total cost
     * @throws IllegalArgumentException if the service returns an invalid discount
     */
    public double calculateTotal() {
        double totalCost = 0;
        for (Product product : products) {
            double discount = promotionService.getDiscountPercentage(product);
            if (!Double.isFinite(discount) || discount < 0 || discount > 1) {
                throw new IllegalArgumentException("Discount must be between 0 and 1");
            }
            double discountedPrice = product.getPrice() * (1 - discount);
            totalCost += discountedPrice;
        }
        return totalCost;
    }
}
