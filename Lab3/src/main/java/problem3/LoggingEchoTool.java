package problem3;

import java.util.ArrayList;
import java.util.List;

/**
 * An EchoTool that also logs every input it receives.
 * (Added by an AI coding agent, when asked to "add logging to the echo tool".)
 */
public class LoggingEchoTool extends EchoTool {
  private final List<String> log = new ArrayList<>();

  @Override
  public String run(String input) {
    log.add(input);
    return execute(input);
  }

  public List<String> getLog() {
    return List.copyOf(log);
  }
}
