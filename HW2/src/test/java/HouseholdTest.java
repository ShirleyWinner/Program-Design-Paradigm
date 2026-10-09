import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Household. */
class HouseholdTest {
    /** Checks inherited data and package amount. */
    @Test void storesData() {
        Household product = new Household("maker", "name", 3.5, 12);
        assertEquals("maker", product.getManufacturer());
        assertEquals("name", product.getName());
        assertEquals(3.5, product.getPrice());
        assertEquals(0, product.getAge());
        assertEquals(12, product.getUnits());
    }
    /** Rejects negative package amounts. */
    @Test void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Household("m", "n", 1, -1));
    }
}
