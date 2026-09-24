/**
 * Represents the contact information of a cooperative member.
 */
public class ContactInfo {

    private Name name;
    private String address;
    private String phoneNumber;
    private String emailAddress;

    /**
     * Constructs contact information.
     *
     * @param name the member's name
     * @param address the member's address
     * @param phoneNumber the member's phone number
     * @param emailAddress the member's email address
     */
    public ContactInfo(Name name,
                       String address,
                       String phoneNumber,
                       String emailAddress) {

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the member's name.
     *
     * @return the member's name
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }
}