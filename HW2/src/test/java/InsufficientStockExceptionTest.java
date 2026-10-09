import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests InsufficientStockException. */
class InsufficientStockExceptionTest {
    /** Confirms the exception is checked and preserves its message. */
    @Test void checkedException() {
        Exception exception = new InsufficientStockException("shortage");
        assertEquals("shortage", exception.getMessage());
        assertFalse(exception instanceof RuntimeException);
    }
}
