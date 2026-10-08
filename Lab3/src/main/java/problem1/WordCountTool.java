package problem1;

public class WordCountTool extends BaseTool {
  public WordCountTool() {
    super("word_count", "Counts the number of words in the input.");
  }

  @Override
  protected String execute(String input) {
    if (input.trim().isEmpty()) {
      return "0";
    }
    return Integer.toString(input.trim().split("\\s+").length);
  }
}
