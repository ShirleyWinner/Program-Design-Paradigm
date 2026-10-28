package lab4.problem1;

import static java.lang.Math.round;

/** Way 1: a named class. Half price, rounded down to a whole cent. */
public class HalfPrice implements DiscountRule {
  @Override
  public int apply(int priceCents) {
    return priceCents / 2;
  }
}

