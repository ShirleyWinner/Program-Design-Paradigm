package problem3;

/** The base class for the agent's tools. Every call to run() is counted. */
public abstract class AbstractTool {
  private final ToolName name;
  private int callCount = 0;

  protected AbstractTool(String name) {
    this.name = new ToolName(name);
  }

  public ToolName getName() {
    return name;
  }

  public int getCallCount() {
    return callCount;
  }

  /** Runs this tool on the given input, and counts the call. */
  public String run(String input) {
    callCount++;
    return execute(input);
  }

  /** Does this tool's actual work. */
  protected abstract String execute(String input);
}
