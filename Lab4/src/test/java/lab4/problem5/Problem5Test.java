package lab4.problem5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lab4.cafe.Category;
import lab4.cafe.SampleData;
import org.junit.jupiter.api.Test;

class Problem5Test {
  @Test
  void menuLine() {
    assertEquals("Espresso, Latte, Cappuccino, Green Tea, Chai, Lemonade, Croissant, Muffin",
        Reports.menuLine(SampleData.menu()));
  }

  @Test
  void ordersPerCustomer() {
    assertEquals("{Ana=2, Ben=2, Chen=1, Dana=1}",
        Reports.ordersPerCustomer(SampleData.orders()).toString());
  }

  @Test
  void bigOrderCustomers() {
    Map<Boolean, List<String>> result = Reports.bigOrderCustomers(SampleData.orders(), 800);
    assertEquals(List.of("Ana", "Chen", "Ben"), result.get(true));
    assertEquals(List.of("Ben", "Ana", "Dana"), result.get(false));
  }

  @Test
  void ifYouHaveTime_allItemNames() {
    assertEquals(Set.of("Cappuccino", "Chai", "Croissant", "Espresso", "Green Tea", "Latte",
        "Lemonade", "Muffin"), Reports.allItemNames(SampleData.orders()));
    assertEquals("Cappuccino", Reports.allItemNames(SampleData.orders()).iterator().next());
  }

  @Test
  void ifYouHaveTime_revenueByCategory() {
    assertEquals(Map.of(Category.COFFEE, 2075, Category.TEA, 800, Category.COLD_DRINK, 350,
        Category.PASTRY, 975), Reports.revenueByCategory(SampleData.orders()));
  }
}
