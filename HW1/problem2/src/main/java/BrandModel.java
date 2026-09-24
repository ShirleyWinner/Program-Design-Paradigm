/**
 * Represents the brand and model of a catalog item.
 */
public class BrandModel {

    private String brand;
    private String model;

    /**
     * Constructs a BrandModel with the specified brand and model.
     *
     * @param brand the brand of the item
     * @param model the model of the item
     */
    public BrandModel(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    /**
     * Returns the brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Returns the model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }
}
