package lab4.problem2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import lab4.cafe.Category;
import lab4.cafe.MenuItem;
import lab4.cafe.SampleData;
import org.junit.jupiter.api.Test;

class Problem2Test {
  @Test
  void predicates() {
    assertTrue(BuiltIns.isCoffee().test(SampleData.LATTE));
    assertFalse(BuiltIns.isCoffee().test(SampleData.CHAI));
    assertTrue(BuiltIns.cheaperThan(400).test(SampleData.ESPRESSO));
    assertFalse(BuiltIns.cheaperThan(450).test(SampleData.LATTE));   // strictly below
  }

  @Test
  void functions() {
    assertEquals("Latte", BuiltIns.name().apply(SampleData.LATTE));
    assertEquals("Latte ($4.50)", BuiltIns.label().apply(SampleData.LATTE));
    assertEquals("Espresso ($3.00)", BuiltIns.label().apply(SampleData.ESPRESSO));
  }

  @Test
  void consumerAndSupplier() {
    List<String> names = new ArrayList<>();
    BuiltIns.addNameTo(names).accept(SampleData.CHAI);
    assertEquals(List.of("Chai"), names);
    assertEquals(new MenuItem("Pumpkin Latte", Category.COFFEE, 575), BuiltIns.dailySpecial().get());
  }

  @Test
  void combinedPredicates() {
    List<MenuItem> menu = SampleData.menu();
    assertEquals(List.of(SampleData.ESPRESSO), menu.stream().filter(BuiltIns.cheapCoffee()).toList());
    assertEquals(5, menu.stream().filter(BuiltIns.notCoffee()).count());
  }

  @Test
  void ifYouHaveTime_byPriceThenName() {
    assertEquals(List.of("Espresso", "Muffin", "Green Tea", "Lemonade", "Croissant", "Cappuccino",
        "Latte", "Chai"), SampleData.menu().stream().sorted(BuiltIns.byPriceThenName())
        .map(MenuItem::name).toList());
  }
}
