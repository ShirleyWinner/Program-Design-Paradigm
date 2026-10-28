package lab4.problem3;

import java.util.Comparator;
import java.util.List;
import lab4.cafe.MenuItem;
import lab4.cafe.Order;

/** First stream pipelines. Write each method as one pipeline: no loops. */
public final class Pipelines {
  private Pipelines() { }

  /** The names of the items cheaper than the given price, cheapest first (ties by name). */
  public static List<String> namesCheaperThan(List<MenuItem> menu, int cents) {
    throw new UnsupportedOperationException("TODO: Problem 3");
  }

  /** How many orders are takeout. */
  public static long countTakeout(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 3");
  }

  /** The total revenue of all orders, in cents. Hint: mapToInt, then sum. */
  public static int totalRevenueCents(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 3");
  }

  /** If you have time: the customers who ordered the given item, no repeats, A to Z. */
  public static List<String> customersWhoOrdered(List<Order> orders, MenuItem item) {
    throw new UnsupportedOperationException("TODO: Problem 3");
  }
}
