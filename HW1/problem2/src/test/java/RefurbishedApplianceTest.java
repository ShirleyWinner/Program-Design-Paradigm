import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefurbishedApplianceTest {

    @Test
    public void testRefurbishedApplianceConstructorAndGetters() {

        BrandModel brandModel =
                new BrandModel("LG", "WM4000");

        RefurbishedAppliance appliance =
                new RefurbishedAppliance(
                        "A002",
                        2022,
                        brandModel,
                        799.99,
                        1200,
                        2,
                        3
                );

        assertEquals("A002", appliance.getId());
        assertEquals(2022, appliance.getManufacturingYear());
        assertEquals(brandModel, appliance.getBrandModel());
        assertEquals(799.99, appliance.getMsrp());

        assertEquals(1200, appliance.getUsageHours());
        assertEquals(2, appliance.getNumberOfPreviousOwners());
        assertEquals(3,
                appliance.getNumberOfMinorCosmeticDefects());
    }
}