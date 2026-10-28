package lab4.cafe;

import java.util.Locale;

/** Formats prices for display. */
public final class Money {
  private Money() { }

  /** Formats a price in cents as dollars: 450 becomes "$4.50". */
  public static String format(int cents) {
    return String.format(Locale.ROOT, "$%d.%02d", cents / 100, cents % 100);
  }
}
