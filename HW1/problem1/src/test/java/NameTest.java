import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {

    @Test
    void testGetters() {
        Name name = new Name("John", "Michael", "Smith");

        assertEquals("John", name.getFirstName());
        assertEquals("Michael", name.getMiddleName());
        assertEquals("Smith", name.getLastName());
    }

    @Test
    void testEqualNames() {
        Name name1 = new Name("John", "Michael", "Smith");
        Name name2 = new Name("John", "Michael", "Smith");

        assertEquals(name1, name2);
    }

    @Test
    void testDifferentNames() {
        Name name1 = new Name("John", "Michael", "Smith");
        Name name2 = new Name("Jane", "Michael", "Smith");

        assertNotEquals(name1, name2);
    }
}