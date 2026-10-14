package problem1;

/**
 * A tool that an AI agent can call. A tool takes a text input, and returns a
 * text result. The agent reads each tool's name and description to decide which
 * tool to call.
 */
public interface Tool {
  /** Returns this tool's name, such as "word_count". Never null or blank. */
  String getName();

  /**
   * Returns a one-sentence description, telling the agent when to use this
   * tool.
   */
  String getDescription();

  /**
   * Runs this tool on the given input, and returns its result.
   *
   * @throws IllegalArgumentException if input is null
   */
  String run(String input);

  /** Returns how many times run() has completed successfully on this tool. */
  int getCallCount();
}
