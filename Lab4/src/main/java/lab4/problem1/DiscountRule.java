package lab4.problem1;

/** A rule that turns a price (in cents) into a discounted price. */
@FunctionalInterface
public interface DiscountRule {
  /** Returns the discounted price, in cents; never negative. */
  int apply(int priceCents);

  /** Returns a rule that applies this rule first, and then the next rule. */
  default DiscountRule andThen(DiscountRule next) {
    return priceCents->next.apply(this.apply(priceCents));
  }
}
