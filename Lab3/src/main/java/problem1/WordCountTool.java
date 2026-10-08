package problem1;

public class WordCountTool extends BaseTool {
  public WordCountTool() {
    super("word_count", "Counts the number of words in the input.");
  }

  @Override
  protected String execute(String input) {
    String trimmedInput = input.trim();
    if (trimmedInput.isEmpty()) {
      return "0";
    }
    return Integer.toString(trimmedInput.split("\\s+").length);
  }
}
