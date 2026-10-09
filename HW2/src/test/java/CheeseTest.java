import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Cheese. */
class CheeseTest {
    /** Checks inherited data and package amount. */
    @Test void storesData() {
        Cheese product = new Cheese("maker", "name", 3.5, 12);
        assertEquals("maker", product.getManufacturer());
        assertEquals("name", product.getName());
        assertEquals(3.5, product.getPrice());
        assertEquals(0, product.getAge());
        assertEquals(12, product.getOunces());
    }
    /** Rejects negative package amounts. */
    @Test void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Cheese("m", "n", 1, -1));
    }
}
