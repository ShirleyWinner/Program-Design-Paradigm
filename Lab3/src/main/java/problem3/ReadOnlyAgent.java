package problem3;

/**
 * An agent whose set of tools can't be changed, for example in a sandboxed
 * session. (Added by an AI coding agent, when asked to "make a read-only
 * agent".)
 */
public class ReadOnlyAgent extends Agent {
  @Override
  public void registerTool(AbstractTool tool) {
    throw new UnsupportedOperationException("This agent is read-only");
  }
}
