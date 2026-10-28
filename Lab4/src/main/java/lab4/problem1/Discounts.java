package lab4.problem1;

/** Ways 2 and 3 of writing a DiscountRule, and an extension. */
public final class Discounts {
  private Discounts() { }

  /** Way 2: an anonymous class. One dollar (100 cents) off, but never below 0. */
  public static DiscountRule dollarOff() {
    return new DiscountRule() {
      @Override
      public int apply(int priceCents) {
        return Math.max(0, priceCents - 100);
      }
    };
  }

  /** Way 3: a lambda. Ten percent off, rounded down to a whole cent. */
  public static DiscountRule tenPercentOff() {
      return discountedPrice -> discountedPrice * 90 / 100;
  }

  /** If you have time: a function that RETURNS a rule. percentOff(25) is 25% off. */
  public static DiscountRule percentOff(int percent) {
    throw new UnsupportedOperationException("TODO: Problem 1");
  }
}

