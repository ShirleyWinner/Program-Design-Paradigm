package lab4.cafe;

import java.util.List;

/** The café's menu, and a morning's orders, used in every problem. */
public final class SampleData {
  public static final MenuItem ESPRESSO = new MenuItem("Espresso", Category.COFFEE, 300);
  public static final MenuItem LATTE = new MenuItem("Latte", Category.COFFEE, 450);
  public static final MenuItem CAPPUCCINO = new MenuItem("Cappuccino", Category.COFFEE, 425);
  public static final MenuItem GREEN_TEA = new MenuItem("Green Tea", Category.TEA, 325);
  public static final MenuItem CHAI = new MenuItem("Chai", Category.TEA, 475);
  public static final MenuItem LEMONADE = new MenuItem("Lemonade", Category.COLD_DRINK, 350);
  public static final MenuItem CROISSANT = new MenuItem("Croissant", Category.PASTRY, 375);
  public static final MenuItem MUFFIN = new MenuItem("Muffin", Category.PASTRY, 300);

  private SampleData() { }

  public static List<MenuItem> menu() {
    return List.of(ESPRESSO, LATTE, CAPPUCCINO, GREEN_TEA, CHAI, LEMONADE, CROISSANT, MUFFIN);
  }

  public static List<Order> orders() {
    return List.of(
        new Order("Ana", List.of(LATTE, CROISSANT), false),     // 825
        new Order("Ben", List.of(ESPRESSO), true),              // 300
        new Order("Chen", List.of(CHAI, MUFFIN, MUFFIN), true), // 1075
        new Order("Ana", List.of(GREEN_TEA), true),             // 325
        new Order("Dana", List.of(CAPPUCCINO, LEMONADE), false),// 775
        new Order("Ben", List.of(LATTE, LATTE), false));        // 900
  }
}
