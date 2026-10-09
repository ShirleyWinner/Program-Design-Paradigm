/** A salmon grocery product. */
public class Salmon extends Grocery {

    /** Creates a salmon product with minimum purchase age 0.
     * @param manufacturer manufacturer
     * @param name product name
     * @param price unit price
     * @param ounces package weight in ounces
     */
    public Salmon(String manufacturer, String name, double price, double ounces) {
        super(manufacturer, name, price, ounces);
    }
}
