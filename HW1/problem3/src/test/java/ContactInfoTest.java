import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoTest {

    @Test
    public void testConstructorAndGetters() {

        Name name = new Name("John", "Smith");

        ContactInfo contactInfo = new ContactInfo(
                name,
                "100 Farm Road",
                "206-555-1234",
                "john@example.com"
        );

        assertEquals(name, contactInfo.getName());
        assertEquals(
                "100 Farm Road",
                contactInfo.getAddress()
        );

        assertEquals(
                "206-555-1234",
                contactInfo.getPhoneNumber()
        );

        assertEquals(
                "john@example.com",
                contactInfo.getEmailAddress()
        );
    }
}