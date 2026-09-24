/**
 * Represents a sofa in the furniture catalog.
 */
public class Sofa extends Furniture {

    private Float length;
    private Integer numberOfSeats;
    private UpholsteryType upholsteryType;

    /**
     * Constructs a sofa.
     *
     * @param id the unique identifier
     * @param manufacturingYear the manufacturing year
     * @param brandModel the brand and model
     * @param msrp the manufacturer's suggested retail price
     * @param length the length of the sofa
     * @param numberOfSeats the number of seats
     * @param upholsteryType the upholstery type
     */
    public Sofa(String id,
                Integer manufacturingYear,
                BrandModel brandModel,
                Double msrp,
                Float length,
                Integer numberOfSeats,
                UpholsteryType upholsteryType) {

        super(id, manufacturingYear, brandModel, msrp);

        this.length = length;
        this.numberOfSeats = numberOfSeats;
        this.upholsteryType = upholsteryType;
    }

    /**
     * Returns the length of the sofa.
     *
     * @return the sofa length
     */
    public Float getLength() {
        return length;
    }

    /**
     * Returns the number of seats.
     *
     * @return the number of seats
     */
    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Returns the upholstery type.
     *
     * @return the upholstery type
     */
    public UpholsteryType getUpholsteryType() {
        return upholsteryType;
    }
}
