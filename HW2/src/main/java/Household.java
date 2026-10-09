/** A concrete household product. */
public class Household extends Product implements Substitutable {
    private final int units;

    /** Creates an unrestricted product.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param units nonnegative package amount
     */
    public Household(String manufacturer, String name, double price, int units) {
        this(manufacturer, name, price, 0, units);
    }

    /** Creates a product with an explicit purchase age.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param minimumPurchaseAge minimum purchase age
     * @param units nonnegative package amount
     * @throws IllegalArgumentException if the package amount is invalid
     */
    public Household(String manufacturer, String name, double price, int minimumPurchaseAge, int units) {
        super(manufacturer, name, price, minimumPurchaseAge);
        if (units < 0) {
            throw new IllegalArgumentException("Invalid units");
        }
        this.units = units;
    }

    /** @return package amount in individual units */
    public int getUnits() {
        return units;
    }

    /** Checks type, price and package unit count against the original.
     * @param original product to replace
     * @return whether this candidate satisfies the product substitution rules
     */
    @Override
    public boolean isAcceptableSubstituteFor(Product original) {
        if (original == null || getClass() != original.getClass()) {
            return false;
        }
        Household originalHousehold = (Household) original;
        return getPrice() <= originalHousehold.getPrice()
                && getUnits() >= originalHousehold.getUnits();
    }

    /** Compares shared attributes and package unit count.
     * @param object object to compare
     * @return whether both products have equal attributes
     */
    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        }
        Household otherProduct = (Household) object;
        return units == otherProduct.units;
    }

    /** @return hash derived from the attributes used by equals */
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Integer.hashCode(units);
    }
}
