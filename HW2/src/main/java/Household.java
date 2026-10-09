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

    /** Evaluates a candidate against the original.
     * The Problem 2 Part C specification is needed to implement this policy.
     * @param original product to replace
     * @return whether substitution is allowed
     * @throws UnsupportedOperationException until the substitution policy is supplied
     */
    @Override
    public boolean isAcceptableSubstituteFor(Product original) {
        throw new UnsupportedOperationException("Problem 2 Part C substitution rules are required");
    }
}
