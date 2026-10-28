package lab4.problem4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import lab4.cafe.Category;
import lab4.cafe.MenuItem;
import lab4.cafe.Order;
import lab4.cafe.SampleData;
import org.junit.jupiter.api.Test;

class Problem4Test {
  @Test
  void mostExpensive() {
    assertEquals("Chen", Answers.mostExpensive(SampleData.orders()).map(Order::customer).orElse("?"));
    assertTrue(Answers.mostExpensive(List.of()).isEmpty());
  }

  @Test
  void biggestSpender() {
    assertEquals("Chen", Answers.biggestSpender(SampleData.orders()));
    assertEquals("nobody", Answers.biggestSpender(List.of()));
  }

  @Test
  void reduceGivesTheSameTotalAsSum() {
    assertEquals(4200, Answers.totalWithReduce(SampleData.orders()));
    assertEquals(0, Answers.totalWithReduce(List.of()));   // the identity
  }

  @Test
  void averageMayBeEmpty() {
    assertEquals(700.0, Answers.averageOrderCents(SampleData.orders()).getAsDouble(), 1e-9);
    assertTrue(Answers.averageOrderCents(List.of()).isEmpty());
  }

  @Test
  void ifYouHaveTime_cheapestIn() {
    assertEquals("Green Tea",
        Answers.cheapestIn(SampleData.menu(), Category.TEA).map(MenuItem::name).orElse("?"));
    assertFalse(Answers.cheapestIn(List.of(SampleData.LATTE), Category.PASTRY).isPresent());
  }
}
