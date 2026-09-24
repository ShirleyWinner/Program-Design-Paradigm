/**
 * Represents a general item in the retailer's catalog.
 * Every catalog item has an ID, manufacturing year,
 * brand and model, and MSRP.
 */
public abstract class CatalogItem {

    private String id;
    private Integer manufacturingYear;
    private BrandModel brandModel;
    private Double msrp;

    /**
     * Constructs a catalog item.
     *
     * @param id the unique identifier of the item
     * @param manufacturingYear the year the item was manufactured
     * @param brandModel the brand and model of the item
     * @param msrp the manufacturer's suggested retail price
     */
    public CatalogItem(String id,
                       Integer manufacturingYear,
                       BrandModel brandModel,
                       Double msrp) {

        this.id = id;
        this.manufacturingYear = manufacturingYear;
        this.brandModel = brandModel;
        this.msrp = msrp;
    }

    /**
     * Returns the item's ID.
     *
     * @return the item ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the manufacturing year.
     *
     * @return the manufacturing year
     */
    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    /**
     * Returns the brand and model.
     *
     * @return the BrandModel
     */
    public BrandModel getBrandModel() {
        return brandModel;
    }

    /**
     * Returns the MSRP.
     *
     * @return the MSRP
     */
    public Double getMsrp() {
        return msrp;
    }
}
