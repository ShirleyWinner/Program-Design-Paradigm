import java.util.Objects;

/** A product with a manufacturer, name, price and minimum purchase age. */
public abstract class Product {
    private final String manufacturer;
    private final String name;
    private final double price;
    private final int minimumPurchaseAge;


    /** Creates an unrestricted product.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price nonnegative finite price
     */
    protected Product(String manufacturer, String name, double price) {
        this(manufacturer, name, price, 0);
    }


    /** Creates a product with an explicit minimum purchase age.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price nonnegative finite price
     * @param minimumPurchaseAge nonnegative minimum purchase age
     * @throws IllegalArgumentException if price or age is invalid
     */
    protected Product(String manufacturer, String name, double price, int minimumPurchaseAge) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be nonnegative");
        }
        if (minimumPurchaseAge < 0) {
            throw new IllegalArgumentException("Minimum purchase age cannot be negative");
        }
        this.manufacturer = manufacturer;
        this.name = name;
        this.price = price;
        this.minimumPurchaseAge = minimumPurchaseAge;
    }

    /** @return manufacturer */
    public String getManufacturer() {
        return manufacturer;
    }

    /** @return product name */
    public String getName() {
        return name;
    }

    /** @return unit price */
    public double getPrice() {
        return price;
    }

    /** @return minimum purchase age, not the customer's age */
    public int getAge() {
        return minimumPurchaseAge;
    }
}
