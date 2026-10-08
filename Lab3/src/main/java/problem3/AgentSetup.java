package problem3;

import java.util.List;

/** Sets up agents with the default tools. */
public final class AgentSetup {
  private AgentSetup() { }

  /** Registers the default tools on the given agent. */
  public static void installDefaultTools(Agent agent) {
    agent.registerTool(new EchoTool());
  }

  public static void main(String[] args) {
    List<Agent> agents = List.of(new Agent(), new ReadOnlyAgent());
    for (Agent agent : agents) {
      installDefaultTools(agent);
      System.out.println(agent.getClass().getSimpleName() + ": " + agent.toolCount() + " tool(s)");
    }
  }
}
