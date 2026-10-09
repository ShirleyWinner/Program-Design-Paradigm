/** A beer grocery product. */
public class Beer extends Grocery {

    /** Creates a beer product with minimum purchase age 21.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param ounces package weight in ounces
     */
    public Beer(String manufacturer, String name, double price, double ounces) {
        super(manufacturer, name, price, 21, ounces);
    }
}
