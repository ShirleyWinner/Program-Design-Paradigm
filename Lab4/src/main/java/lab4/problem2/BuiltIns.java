package lab4.problem2;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lab4.cafe.Category;
import lab4.cafe.MenuItem;
import lab4.cafe.Money;

/** Java's built-in functional interfaces, over menu items. */
public final class BuiltIns {
  private BuiltIns() { }

  /** A Predicate: is the item a coffee? */
  public static Predicate<MenuItem> isCoffee() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** A Predicate: is the item's price strictly below the given number of cents? */
  public static Predicate<MenuItem> cheaperThan(int cents) {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** A Function: the item's name. Use a method reference. */
  public static Function<MenuItem, String> name() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** A Function: a label like "Latte ($4.50)". Hint: Money.format. */
  public static Function<MenuItem, String> label() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** A Consumer: adds each item's name to the given list. */
  public static Consumer<MenuItem> addNameTo(List<String> names) {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** A Supplier: today's special, a new "Pumpkin Latte" coffee for 575 cents. */
  public static Supplier<MenuItem> dailySpecial() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** Combine predicates: a coffee that costs less than $4.00. Use and(). */
  public static Predicate<MenuItem> cheapCoffee() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** Combine predicates: anything that is not a coffee. Use negate(). */
  public static Predicate<MenuItem> notCoffee() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }

  /** If you have time: a Comparator, by price, and then by name for equal prices. */
  public static Comparator<MenuItem> byPriceThenName() {
    throw new UnsupportedOperationException("TODO: Problem 2");
  }
}
