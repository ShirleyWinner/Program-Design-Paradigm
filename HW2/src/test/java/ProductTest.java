import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests Product. */
class ProductTest {
    /** Checks explicit age and invalid values through a concrete subclass. */
    @Test void validatesData() {
        assertEquals(18, new Grocery("m", "n", 1, 18, 5).getAge());
        assertEquals(18, new Household("m", "n", 1, 18, 5).getAge());
        assertThrows(IllegalArgumentException.class, () -> new Grocery("m", "n", -1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Grocery("m", "n", Double.NaN, 5));
        assertThrows(IllegalArgumentException.class, () -> new Grocery("m", "n", 1, -1, 5));
        assertThrows(IllegalArgumentException.class, () -> new Grocery("m", "n", 1, Double.NaN));
        assertThrows(NullPointerException.class, () -> new Grocery(null, "n", 1, 5));
    }
}
