package problem1;

public abstract class BaseTool implements Tool {
  private final String name;
  private final String description;
  private int callCount;

  protected BaseTool(String name, String description) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Tool name must not be null or blank.");
    }
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException("Tool description must not be null or blank.");
    }
    this.name = name;
    this.description = description;
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final String getDescription() {
    return description;
  }

  @Override
  public final String run(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input must not be null.");
    }

    String result = execute(input);
    callCount++;
    return result;
  }

  @Override
  public final int getCallCount() {
    return callCount;
  }

  protected abstract String execute(String input);
}
