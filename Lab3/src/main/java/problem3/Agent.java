package problem3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A minimal AI agent: a set of tools, each registered under a unique name,
 * which the agent can call by name. (Extended by an AI coding agent.)
 */
public class Agent {
  private final List<ToolName> names = new ArrayList<>();
  private final Map<ToolName, AbstractTool> tools = new HashMap<>();

  /**
   * Registers a tool.
   *
   * @throws IllegalArgumentException if a tool with the same name is already registered
   */
  public void registerTool(AbstractTool tool) {
    if (names.contains(tool.getName())) {
      throw new IllegalArgumentException("Duplicate tool: " + tool.getName());
    }
    names.add(tool.getName());
    tools.put(tool.getName(), tool);
  }

  /** Returns true if a tool with the given name is registered. */
  public boolean hasTool(String name) {
    return tools.containsKey(new ToolName(name));
  }

  /**
   * Calls the tool with the given name on the given input, and returns its
   * result.
   *
   * @throws IllegalArgumentException if no tool with that name is registered
   */
  public String callTool(String name, String input) {
    AbstractTool tool = tools.get(new ToolName(name));
    if (tool == null) {
      throw new IllegalArgumentException("No tool named " + name);
    }
    return tool.run(input);
  }

  /** Returns the number of registered tools. */
  public int toolCount() {
    return names.size();
  }
}
