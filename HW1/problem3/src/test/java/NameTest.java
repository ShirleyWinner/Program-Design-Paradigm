import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTest {

    @Test
    public void testConstructorAndGetters() {

        Name name = new Name("John", "Smith");

        assertEquals("John", name.getFirstName());
        assertEquals("Smith", name.getLastName());
    }
}