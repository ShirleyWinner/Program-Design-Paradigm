/**
 * Represents a general furniture item in the catalog.
 */
public abstract class Furniture extends CatalogItem {

    /**
     * Constructs a furniture item.
     *
     * @param id the unique identifier of the furniture
     * @param manufacturingYear the manufacturing year
     * @param brandModel the brand and model
     * @param msrp the manufacturer's suggested retail price
     */
    public Furniture(String id,
                     Integer manufacturingYear,
                     BrandModel brandModel,
                     Double msrp) {

        super(id, manufacturingYear, brandModel, msrp);
    }
}
