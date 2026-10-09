import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests the unchecked quantity exception. */
class InvalidQuantityExceptionTest {
    /** The exception preserves its message and is unchecked. */
    @Test void uncheckedException() {
        RuntimeException exception = new InvalidQuantityException("Invalid quantity");
        assertEquals("Invalid quantity", exception.getMessage());
    }
}
