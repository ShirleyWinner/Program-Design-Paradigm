package problem3;

/**
 * A tool's name, as a value object. Two ToolNames are equal if their text is
 * equal.
 */
public final class ToolName {
  private final String value;

  public ToolName(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("A tool name must not be blank");
    }
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public boolean equals(ToolName other) {
    return other != null && value.equals(other.value);
  }

  @Override
  public String toString() {
    return value;
  }
}
