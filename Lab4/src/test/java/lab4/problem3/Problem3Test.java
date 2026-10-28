package lab4.problem3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import lab4.cafe.SampleData;
import org.junit.jupiter.api.Test;

class Problem3Test {
  @Test
  void namesCheaperThanAreSortedByPrice() {
    assertEquals(List.of("Espresso", "Muffin", "Green Tea", "Lemonade", "Croissant"),
        Pipelines.namesCheaperThan(SampleData.menu(), 400));
    assertEquals(List.of(), Pipelines.namesCheaperThan(SampleData.menu(), 100));
  }

  @Test
  void countTakeout() {
    assertEquals(3, Pipelines.countTakeout(SampleData.orders()));
  }

  @Test
  void totalRevenue() {
    assertEquals(4200, Pipelines.totalRevenueCents(SampleData.orders()));
    assertEquals(0, Pipelines.totalRevenueCents(List.of()));
  }

  @Test
  void ifYouHaveTime_customersWhoOrdered() {
    assertEquals(List.of("Ana", "Ben"),
        Pipelines.customersWhoOrdered(SampleData.orders(), SampleData.LATTE));
    assertEquals(List.of("Chen"),
        Pipelines.customersWhoOrdered(SampleData.orders(), SampleData.MUFFIN));
  }
}
