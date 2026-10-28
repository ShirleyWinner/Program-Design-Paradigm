package lab4.problem4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import lab4.cafe.Category;
import lab4.cafe.MenuItem;
import lab4.cafe.Order;

/** Questions that might have no answer: Optional, and reduce. */
public final class Answers {
  private Answers() { }

  /** The order with the highest total, or an empty Optional if there are no orders. */
  public static Optional<Order> mostExpensive(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 4");
  }

  /** The customer of the most expensive order, or "nobody". Use mostExpensive. */
  public static String biggestSpender(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 4");
  }

  /** The total revenue, in cents, computed with reduce (not sum). */
  public static int totalWithReduce(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 4");
  }

  /** The average order total, in cents; empty if there are no orders. */
  public static OptionalDouble averageOrderCents(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 4");
  }

  /** If you have time: the cheapest item in a category, if the category has any items. */
  public static Optional<MenuItem> cheapestIn(List<MenuItem> menu, Category category) {
    throw new UnsupportedOperationException("TODO: Problem 4");
  }
}
