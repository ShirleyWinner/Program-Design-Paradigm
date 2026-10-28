package lab4.problem5;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lab4.cafe.Category;
import lab4.cafe.MenuItem;
import lab4.cafe.Order;

/** Reports with collect() and the Collectors class. */
public final class Reports {
  private Reports() { }

  /** The menu's item names, as one line: "Espresso, Latte, ...". */
  public static String menuLine(List<MenuItem> menu) {
    return menu.stream().map(MenuItem::name).collect(Collectors.joining(", "));
  }

  /** How many orders each customer placed, customers A to Z. Hint: TreeMap::new. */
  public static Map<String, Long> ordersPerCustomer(List<Order> orders) {
    return orders.stream()
            .collect(Collectors.groupingBy(
                    Order::customer,
                    TreeMap::new,
                    Collectors.counting()
            ));
  }

  /** The customers of big orders (true) and of the other orders (false), in order. */
  public static Map<Boolean, List<String>> bigOrderCustomers(List<Order> orders,
                                                             int thresholdCents) {
    return orders.stream()
            .collect(Collectors.partitioningBy(
                    order -> order.totalCents() >= thresholdCents,
                    Collectors.mapping(
                            Order::customer,
                            Collectors.toList()
                    )
            ));
  }

  /** If you have time: every item name that appears in any order, A to Z. Use flatMap. */
  public static Set<String> allItemNames(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 5");
  }

  /** If you have time: total revenue per category, in cents. Use flatMap and groupingBy. */
  public static Map<Category, Integer> revenueByCategory(List<Order> orders) {
    throw new UnsupportedOperationException("TODO: Problem 5");
  }
}
