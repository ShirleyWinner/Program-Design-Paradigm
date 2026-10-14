package problem3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgentTest {
  private Agent agent;

  @BeforeEach
  void setUp() {
    agent = new Agent();
  }

  @Test
  void echoToolEchoes() {
    assertEquals("echo: hi", new EchoTool().run("hi"));
  }

  @Test
  void toolNamesWithTheSameTextAreEqual() {
    Object a = new ToolName("echo");
    Object b = new ToolName("echo");
    assertEquals(a, b);
    assertEquals(a.hashCode(), b.hashCode());
  }

  @Test
  void duplicateToolNamesAreRejected() {
    agent.registerTool(new EchoTool());
    assertThrows(IllegalArgumentException.class, () -> agent.registerTool(new EchoTool()));
    assertEquals(1, agent.toolCount());
  }

  @Test
  void registeredToolsCanBeFoundAndCalledByName() {
    agent.registerTool(new EchoTool());
    assertTrue(agent.hasTool("echo"));
    assertEquals("echo: hi", agent.callTool("echo", "hi"));
  }

  @Test
  void loggingEchoToolLogsAndCountsEveryCall() {
    LoggingEchoTool tool = new LoggingEchoTool();
    assertEquals("echo: a", tool.run("a"));
    tool.run("b");
    assertEquals(List.of("a", "b"), tool.getLog());
    assertEquals(2, tool.getCallCount());
  }
}
