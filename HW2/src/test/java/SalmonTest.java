import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Salmon. */
class SalmonTest {
    /** Checks inherited data and package amount. */
    @Test void storesData() {
        Salmon product = new Salmon("maker", "name", 3.5, 12);
        assertEquals("maker", product.getManufacturer());
        assertEquals("name", product.getName());
        assertEquals(3.5, product.getPrice());
        assertEquals(0, product.getAge());
        assertEquals(12, product.getOunces());
    }
    /** Rejects negative package amounts. */
    @Test void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Salmon("m", "n", 1, -1));
    }
}
