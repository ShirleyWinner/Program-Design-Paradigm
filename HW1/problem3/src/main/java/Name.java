/**
 * Represents a member's name.
 */
public class Name {

    private String firstName;
    private String lastName;

    /**
     * Constructs a Name.
     *
     * @param firstName the member's first name
     * @param lastName the member's last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
}
