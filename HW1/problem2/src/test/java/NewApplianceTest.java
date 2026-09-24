import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewApplianceTest {

    @Test
    public void testNewApplianceConstructorAndGetters() {

        BrandModel brandModel =
                new BrandModel("Samsung", "RF28");

        NewAppliance appliance =
                new NewAppliance(
                        "A001",
                        2025,
                        brandModel,
                        1299.99,
                        8
                );

        assertEquals("A001", appliance.getId());
        assertEquals(2025, appliance.getManufacturingYear());
        assertEquals(brandModel, appliance.getBrandModel());
        assertEquals(1299.99, appliance.getMsrp());
        assertEquals(8,
                appliance.getAvailableUnitsWithin50Miles());
    }
}