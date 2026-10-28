package lab4.problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Problem1Test {
  @Test
  void halfPriceRoundsDown() {
    assertEquals(225, new HalfPrice().apply(450));
    assertEquals(112, new HalfPrice().apply(225));
  }

  @Test
  void dollarOffNeverGoesBelowZero() {
    assertEquals(350, Discounts.dollarOff().apply(450));
    assertEquals(0, Discounts.dollarOff().apply(50));
  }

  @Test
  void tenPercentOff() {
    assertEquals(405, Discounts.tenPercentOff().apply(450));
  }

  @Test
  void andThenAppliesTheRulesInOrder() {
    DiscountRule tenThenDollar = Discounts.tenPercentOff().andThen(Discounts.dollarOff());
    DiscountRule dollarThenTen = Discounts.dollarOff().andThen(Discounts.tenPercentOff());
    assertEquals(350, tenThenDollar.apply(500));   // 500 -> 450 -> 350
    assertEquals(360, dollarThenTen.apply(500));   // 500 -> 400 -> 360: order matters!
  }

  @Test
  void ifYouHaveTime_percentOffReturnsARule() {
    assertEquals(300, Discounts.percentOff(25).apply(400));
    assertEquals(400, Discounts.percentOff(0).apply(400));
  }
}
