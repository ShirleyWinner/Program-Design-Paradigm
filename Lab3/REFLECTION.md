# Lab 3 Reflection: AI Coding Agents

Answer briefly: a few sentences per question is plenty.

## Question 1: Problem 1 (generating code: test-first vs. concept-first)

**1a.** Before running the tests, how confident were you (1-5) that each version would work?
What looked correct, and what looked suspicious?

**1b.** Which approach got to passing tests faster? Which produced code you understood better?

**1c.** Compare the two designs. Did the agent put the shared logic (name checks, call
counting) in `BaseTool`, or duplicate it? Did it make fields `private` and use `@Override`?
Did it follow your design guidance in the concept-first prompt, or ignore some of it?

**1d.** Which version would be easier to change if we added ten more tools? Why?

## Question 2: Problem 2 (reviewing an AI-generated ADT)

**2a.** List the problems YOU found in `ConversationMemory` and `Message`, before asking the agent.

**2b.** What did the agent find with your vague prompt? With your specific prompt?
What did it miss, or get wrong?

**2c.** For each suggestion the agent made, did you keep it, modify it, or reject it? Why?

| Suggestion | Keep / Modify / Reject | Why |
|---|---|---|
|  |  |  |

**2d.** In your own words: what is the representation invariant of `ConversationMemory`,
and how did the original code let clients break it?

## Question 3: Problem 3 (debugging what the agent added)

**3a.** How did you explore the code before fixing anything? Did the diagram help?

**3b.** Which bugs did you find and fix? Explain one in a sentence, in your own words.

**3c.** Did you find all the bugs in `bugs.md`? Did you use AI to understand, or to get quick fixes?

**3d.** (Stretch) Run `AgentSetup.main`. Is the bug in `installDefaultTools` or in
`ReadOnlyAgent`? Explain using the Liskov substitution principle.

## Question 4: Takeaways

**4a.** What surprised you most about working with an AI coding agent today?

**4b.** What is one thing you will always do before accepting AI-suggested code?

**4c.** Complete the sentence: "I will use AI to ______, but I will always ______."
