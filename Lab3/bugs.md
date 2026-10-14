# Problem 3: the expected bugs

**Don't open this file until you have done your own analysis.** Then check your findings
against this list.

1. **`ToolName.equals(ToolName)` is an overload, not an override.** `Agent.registerTool`
   calls `names.contains(...)`, and `List.contains` calls `equals(Object)`, so it never
   sees this method. Two different `ToolName` objects with the same text are never "equal",
   and duplicate tools are accepted. Fix: declare `equals(Object o)`, with `@Override`.
   (Lecture 4: overload resolution uses the argument's declared type, `Object`.)

2. **`ToolName` overrides `equals` but not `hashCode`.** `Agent` stores tools in a
   `HashMap<ToolName, AbstractTool>`. Equal names must have equal hash codes, or the map
   can't find a tool by a new `ToolName` with the same text, so `hasTool` and `callTool`
   fail. Fix: override `hashCode()`, using the same field as `equals`.

3. **`LoggingEchoTool.run` overrides `run` without calling `super.run(input)`.** The call
   counting lives in `AbstractTool.run`, so a `LoggingEchoTool`'s calls are never counted.
   Fix: call `super.run(input)` after logging. Better design: make `AbstractTool.run`
   `final` (so subclasses can't skip the counting), and add logging by overriding
   `execute` instead.

4. **(Stretch) `ReadOnlyAgent` violates the Liskov substitution principle.** `Agent`'s
   contract says `registerTool` registers a tool; `ReadOnlyAgent` throws instead. So code
   written against `Agent`, like `AgentSetup.installDefaultTools`, breaks when handed a
   `ReadOnlyAgent` (run `AgentSetup.main`). The bug is in `ReadOnlyAgent`, not in
   `installDefaultTools`. Fix: don't make `ReadOnlyAgent` a subtype of `Agent`; use
   composition. A `ReadOnlyAgent` holds an `Agent` and exposes only `hasTool`, `callTool`,
   and `toolCount`.
