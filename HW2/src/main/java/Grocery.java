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
