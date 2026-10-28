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
    return item->item.category()==Category.COFFEE;}

  /** A Predicate: is the item's price strictly below the given number of cents? */
  public static Predicate<MenuItem> cheaperThan(int cents) {
    return item->item.priceCents()<cents;
  }

  /** A Function: the item's name. Use a method reference. */
  public static Function<MenuItem, String> name() {
    return MenuItem::name;
  }

  /** A Function: a label like "Latte ($4.50)". Hint: Money.format. */
  public static Function<MenuItem, String> label() {
    return item->item.name()+" ( "+Money.format(item.priceCents())+")";
  }

  /** A Consumer: adds each item's name to the given list. */
  public static Consumer<MenuItem> addNameTo(List<String> names) {
    return item->names.add(item.name());
  }

  /** A Supplier: today's special, a new "Pumpkin Latte" coffee for 575 cents. */
  public static Supplier<MenuItem> dailySpecial() {
    return () -> new MenuItem(
            "Pumpkin Latte",
            Category.COFFEE,
            575
    );
  }

  /** Combine predicates: a coffee that costs less than $4.00. Use and(). */
  public static Predicate<MenuItem> cheapCoffee() {
    return isCoffee().and(cheaperThan(400));
  }

  /** Combine predicates: anything that is not a coffee. Use negate(). */
  public static Predicate<MenuItem> notCoffee() {
    return isCoffee().negate();
  }

  /** If you have time: a Comparator, by price, and then by name for equal prices. */
  public static Comparator<MenuItem> byPriceThenName() {
    return Comparator.comparing(MenuItem::name).thenComparing(MenuItem::priceCents);
  }
}
