package lab4.problem4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import lab4.cafe.Category;
import lab4.cafe.MenuItem;
import lab4.cafe.Order;
import lab4.problem2.BuiltIns;

/** Questions that might have no answer: Optional, and reduce. */
public final class Answers {
  private Answers() { }

  /** The order with the highest total, or an empty Optional if there are no orders. */
  public static Optional<Order> mostExpensive(List<Order> orders) {
    return orders.stream().max(Comparator.comparingInt(Order::totalCents));
  }

  /** The customer of the most expensive order, or "nobody". Use mostExpensive. */
  public static String biggestSpender(List<Order> orders) {
    return mostExpensive(orders).map(Order::customer).orElse("nobody");
  }

  /** The total revenue, in cents, computed with reduce (not sum). */
  public static int totalWithReduce(List<Order> orders) {
    return orders.stream().map(Order::totalCents).reduce(0, Integer::sum);
  }

  /** The average order total, in cents; empty if there are no orders. */
  public static OptionalDouble averageOrderCents(List<Order> orders) {
    return orders.stream().mapToInt(Order::totalCents).average();
  }

  /** If you have time: the cheapest item in a category, if the category has any items. */
  public static Optional<MenuItem> cheapestIn(List<MenuItem> menu, Category category) {
    return menu.stream().min(Comparator.comparing(MenuItem::priceCents));
  }
}
