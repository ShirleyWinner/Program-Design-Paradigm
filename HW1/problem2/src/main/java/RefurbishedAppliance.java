/**
 * Represents a refurbished appliance in the catalog.
 */
public class RefurbishedAppliance extends Appliance {

    private Integer usageHours;
    private Integer numberOfPreviousOwners;
    private Integer numberOfMinorCosmeticDefects;

    /**
     * Constructs a refurbished appliance.
     *
     * @param id the unique identifier
     * @param manufacturingYear the manufacturing year
     * @param brandModel the brand and model
     * @param msrp the manufacturer's suggested retail price
     * @param usageHours the number of hours the appliance has been used
     * @param numberOfPreviousOwners the number of previous owners
     * @param numberOfMinorCosmeticDefects the number of minor cosmetic defects
     */
    public RefurbishedAppliance(
            String id,
            Integer manufacturingYear,
            BrandModel brandModel,
            Double msrp,
            Integer usageHours,
            Integer numberOfPreviousOwners,
            Integer numberOfMinorCosmeticDefects) {

        super(id, manufacturingYear, brandModel, msrp);

        this.usageHours = usageHours;
        this.numberOfPreviousOwners = numberOfPreviousOwners;
        this.numberOfMinorCosmeticDefects =
                numberOfMinorCosmeticDefects;
    }

    /**
     * Returns the usage hours.
     *
     * @return the usage hours
     */
    public Integer getUsageHours() {
        return usageHours;
    }

    /**
     * Returns the number of previous owners.
     *
     * @return the number of previous owners
     */
    public Integer getNumberOfPreviousOwners() {
        return numberOfPreviousOwners;
    }

    /**
     * Returns the number of minor cosmetic defects.
     *
     * @return the number of minor cosmetic defects
     */
    public Integer getNumberOfMinorCosmeticDefects() {
        return numberOfMinorCosmeticDefects;
    }
}
