import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandModelTest {

    @Test
    public void testBrandModelConstructorAndGetters() {

        BrandModel brandModel =
                new BrandModel("Samsung", "RF28");

        assertEquals("Samsung", brandModel.getBrand());
        assertEquals("RF28", brandModel.getModel());
    }
}