import java.util.Objects;

/** A customer with a name, age and personal shopping cart. */
public class Customer {
    private final String name;
    private final int age;
    private final ShoppingCart cart;

    /** Creates a customer and an empty cart.
     * @param name customer name
     * @param age nonnegative customer age
     * @param promotionService discount dependency for the cart
     * @throws IllegalArgumentException if age is negative
     */
    public Customer(String name, int age, PromotionService promotionService) {
        this.name = Objects.requireNonNull(name, "name");
        if (age < 0) {
            throw new IllegalArgumentException("Customer age cannot be negative");
        }
        this.age = age;
        this.cart = new ShoppingCart(promotionService);
    }

    /** @return customer name */
    public String getName() {
        return name;
    }

    /** @return customer age */
    public int getAge() {
        return age;
    }

    /** @return personal shopping cart */
    public ShoppingCart getCart() {
        return cart;
    }

    /** Adds one unit to the customer's cart.
     * @param inventory store inventory
     * @param product selected product
     * @throws InsufficientStockException if the product is unavailable
     */
    public void addProductToCart(Inventory inventory, Product product)
            throws InsufficientStockException {
        cart.addProduct(inventory, product);
    }

    /** Adds a specified quantity to the customer's cart.
     * @param inventory store inventory
     * @param product selected product
     * @param quantity positive quantity
     * @throws InvalidQuantityException if quantity is not positive
     * @throws InsufficientStockException if stock is insufficient
     */
    public void addProductToCart(Inventory inventory, Product product, int quantity)
            throws InsufficientStockException {
        cart.addProduct(inventory, product, quantity);
    }

    /** Processes this customer's cart using their recorded age.
     * @return receipt for the completed order
     */
    public Receipt processOrder() {
        return cart.processOrder(age);
    }
}
