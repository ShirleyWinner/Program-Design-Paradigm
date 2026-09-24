/**
 * Represents a general appliance in the catalog.
 */
public abstract class Appliance extends CatalogItem {

    /**
     * Constructs an appliance.
     *
     * @param id the unique identifier of the appliance
     * @param manufacturingYear the manufacturing year
     * @param brandModel the brand and model
     * @param msrp the manufacturer's suggested retail price
     */
    public Appliance(String id,
                     Integer manufacturingYear,
                     BrandModel brandModel,
                     Double msrp) {

        super(id, manufacturingYear, brandModel, msrp);
    }
}
