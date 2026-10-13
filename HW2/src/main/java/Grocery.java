import java.util.Objects;
/** A concrete grocery product. */
public class Grocery extends Product implements Substitutable {
    private final double ounces;

    /** Creates an unrestricted product.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param ounces nonnegative package amount
     */
    public Grocery(String manufacturer, String name, double price, double ounces) {
        this(manufacturer, name, price, 0, ounces);
    }

    /** Creates a product with an explicit purchase age.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param minimumPurchaseAge minimum purchase age
     * @param ounces nonnegative package amount
     * @throws IllegalArgumentException if the package amount is invalid
     */
    public Grocery(String manufacturer, String name, double price, int minimumPurchaseAge, double ounces) {
        super(manufacturer, name, price, minimumPurchaseAge);
        if (ounces < 0 || !Double.isFinite(ounces)) {
            throw new IllegalArgumentException("Invalid ounces");
        }
        this.ounces = ounces;
    }

    /** @return package amount in ounces */
    public double getOunces() {
        return ounces;
    }

    /** Checks whether this candidate has the same type, no higher price and no lower weight.
     * @param original product to replace
     * @return whether this candidate meets the product conditions
     */
    @Override
    public boolean isAcceptableSubstituteFor(Product original) {
        if (original == null || getClass() != original.getClass()) {
            return false;
        }
        Grocery originalGrocery = (Grocery) original;
        return getPrice() <= originalGrocery.getPrice()
                && getOunces() >= originalGrocery.getOunces() && originalGrocery.getOunces() > 0;
    }

    /** Compares shared attributes and package weight.
     * @param object object to compare
     * @return whether both products have equal attributes
     */
    @Override
        public boolean equals(Object object) {
            if (!super.equals(object)) {
                return false;
            }
            Grocery otherProduct = (Grocery) object;
            return Double.compare(ounces, otherProduct.ounces) == 0;
        }

        /** @return hash derived from the attributes used by equals */
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Double.hashCode(ounces);
    }
}