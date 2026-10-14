package problem1;

import java.util.Locale;

public class UppercaseTool extends BaseTool {
  public UppercaseTool() {
    super("uppercase", "Converts the input to uppercase.");
  }

  @Override
  protected String execute(String input) {
    return input.toUpperCase(Locale.ROOT);
  }
}
