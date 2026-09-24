/**
 * Represents a new appliance in the catalog.
 */
public class NewAppliance extends Appliance {

    private Integer availableUnitsWithin50Miles;

    /**
     * Constructs a new appliance.
     *
     * @param id the unique identifier
     * @param manufacturingYear the manufacturing year
     * @param brandModel the brand and model
     * @param msrp the manufacturer's suggested retail price
     * @param availableUnitsWithin50Miles the number of matching units
     *                                    available within 50 miles
     */
    public NewAppliance(String id,
                        Integer manufacturingYear,
                        BrandModel brandModel,
                        Double msrp,
                        Integer availableUnitsWithin50Miles) {

        super(id, manufacturingYear, brandModel, msrp);

        this.availableUnitsWithin50Miles =
                availableUnitsWithin50Miles;
    }

    /**
     * Returns the number of matching units available within 50 miles.
     *
     * @return the number of available units
     */
    public Integer getAvailableUnitsWithin50Miles() {
        return availableUnitsWithin50Miles;
    }
}
