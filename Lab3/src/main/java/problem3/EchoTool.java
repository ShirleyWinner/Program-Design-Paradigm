package problem3;

/** A tool that echoes its input back: handy for testing an agent. */
public class EchoTool extends AbstractTool {
  public EchoTool() {
    super("echo");
  }

  @Override
  protected String execute(String input) {
    return "echo: " + input;
  }
}
