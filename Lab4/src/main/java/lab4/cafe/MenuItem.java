package lab4.cafe;

import java.util.Objects;

/** One item on the café's menu. Prices are in cents, so arithmetic is exact. */
public record MenuItem(String name, Category category, int priceCents) {
  public MenuItem {
    Objects.requireNonNull(name, "name");
    Objects.requireNonNull(category, "category");
    if (priceCents < 0) {
      throw new IllegalArgumentException("price must not be negative");
    }
  }
}
