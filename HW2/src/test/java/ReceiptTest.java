import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Tests receipt snapshots. */
class ReceiptTest {
    /** Receipt lists cannot be changed by modifying their source or returned lists. */
    @Test void storesIndependentSummary() {
        Product product = new Cheese("A", "cheese", 3, 8);
        List<Product> received = new ArrayList<>();
        received.add(product);
        Receipt receipt = new Receipt(3, received, List.of(product), List.of(product));
        received.clear();
        assertEquals(3, receipt.getTotalPricePaid());
        assertEquals(List.of(product), receipt.getReceivedProducts());
        assertEquals(List.of(product), receipt.getUnavailableProducts());
        assertEquals(List.of(product), receipt.getAgeRestrictedProducts());
        assertThrows(UnsupportedOperationException.class, () -> receipt.getReceivedProducts().clear());
        assertThrows(UnsupportedOperationException.class, () -> receipt.getUnavailableProducts().clear());
        assertThrows(UnsupportedOperationException.class, () -> receipt.getAgeRestrictedProducts().clear());
    }
}
