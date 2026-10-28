package lab4.cafe;

import java.util.List;
import java.util.Objects;

/** One customer's order: the items bought, and whether it is takeout. */
public record Order(String customer, List<MenuItem> items, boolean takeout) {
  public Order {
    Objects.requireNonNull(customer, "customer");
    items = List.copyOf(items);
  }

  /** Returns the total price of this order, in cents. */
  public int totalCents() {
    int total = 0;
    for (MenuItem item : items) {
      total += item.priceCents();
    }
    return total;
  }
}
