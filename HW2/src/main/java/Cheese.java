/** A cheese grocery product. */
public class Cheese extends Grocery {

    /** Creates a cheese product with minimum purchase age 0.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param ounces package weight in ounces
     */
    public Cheese(String manufacturer, String name, double price, double ounces) {
        super(manufacturer, name, price, ounces);
    }
}
