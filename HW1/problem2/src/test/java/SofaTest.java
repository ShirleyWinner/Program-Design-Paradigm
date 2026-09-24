import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SofaTest {

    @Test
    public void testSofaConstructorAndGetters() {

        BrandModel brandModel =
                new BrandModel("IKEA", "KIVIK");

        Sofa sofa =
                new Sofa(
                        "F001",
                        2024,
                        brandModel,
                        999.99,
                        90.5f,
                        3,
                        UpholsteryType.FABRIC
                );

        assertEquals("F001", sofa.getId());
        assertEquals(2024, sofa.getManufacturingYear());
        assertEquals(brandModel, sofa.getBrandModel());
        assertEquals(999.99, sofa.getMsrp());

        assertEquals(90.5f, sofa.getLength());
        assertEquals(3, sofa.getNumberOfSeats());
        assertEquals(
                UpholsteryType.FABRIC,
                sofa.getUpholsteryType()
        );
    }
}