# Lab 3 Reflection: AI Coding Agents

Answer briefly: a few sentences per question is plenty.

## Question 1: Problem 1 (generating code: test-first vs. concept-first)

**1a.** Before running the tests, how confident were you (1-5) that each version would work?
What looked correct, and what looked suspicious?

For the test-first version, I was 4 out of 5 confident. The null check and call counting looked right. But I wasn’t sure if it would count words correctly when the input was empty or had extra spaces.
For the concept-first version, I was also 4 out of 5 confident. Using trimmedInput made the code easier to read. But it removed the description check, so I wondered if a null or blank description could cause problems.

**1b.** Which approach got to passing tests faster? Which produced code you understood better?

The test-first approach got to passing tests faster because the tests showed what the code needed to do. I understood the concept-first code better because the design explanation helped me see how BaseTool and the subclasses worked together.

**1c.** Compare the two designs. Did the agent put the shared logic (name checks, call
counting) in `BaseTool`, or duplicate it? Did it make fields `private` and use `@Override`?
Did it follow your design guidance in the concept-first prompt, or ignore some of it?

Both versions put the name checks and call counting in BaseTool, so the subclasses didn’t repeat that code. All fields were private, and @Override was used where needed. The concept-first version followed the main design guidance and kept run() final, so subclasses couldn’t skip the checks or counting. The two designs were almost the same.

**1d.** Which version would be easier to change if we added ten more tools? Why?

Both versions put the name checks and call counting in BaseTool, so the subclasses did not repeat that code. All fields were private, and both versions used @Override when implementing or overriding methods.
The concept-first version followed the guidance about sharing logic and keeping fields private. It also kept run() final, so subclasses could not skip the null check or counting. The concept-first removed the description check and made the word-counting code a little easier to read.

## Question 2: Problem 2 (reviewing an AI-generated ADT)

**2a.** List the problems YOU found in `ConversationMemory` and `Message`, before asking the agent.

ConversationMemory:
1. messages and capacity were public, so other code could change them directly.
2. getMessages() returned the original list, so other code could change it without checking the capacity.
3. It did not reject zero or negative capacity.
4. It allowed null messages.
5. getLast() did not check whether the memory was empty.

Message:
1. role and text were public, so other code could change them directly.
2. setText() allowed a message to change after it was stored.
3. The constructor did not check for null values.
4. It did not override equals() or hashCode(), so messages with the same content were not treated as equal.

**2b.** What did the agent find with your vague prompt? With your specific prompt?
What did it miss, or get wrong?

With my vague prompt, the agent found the public fields and missing null checks. With my specific prompt, it also found that getMessages() exposed the original list and that Message could still be changed. It missed equals() and hashCode() at first. I didn’t notice any wrong suggestions.

**2c.** For each suggestion the agent made, did you keep it, modify it, or reject it? Why?

| Suggestion | Keep / Modify / Reject | Why |
| --- | --- | --- |
| Make all fields private | Keep | Other code should not change them directly. |
| Check that capacity is positive | Keep | Zero or negative capacity does not make sense. |

**2d.** In your own words: what is the representation invariant of `ConversationMemory`,
and how did the original code let clients break it?

The memory should have a positive capacity, contain no null messages, and never go over its capacity. It should also keep messages in order.
The original code let other code change the list and capacity directly, so these rules could easily be broken.


## Question 3: Problem 3 (debugging what the agent added)

**3a.** How did you explore the code before fixing anything? Did the diagram help?

I read the classes and followed how the methods called each other. The diagram helped me understand the inheritance.

**3b.** Which bugs did you find and fix? Explain one in a sentence, in your own words.

I fixed the wrong equals() method, the missing hashCode(), and the skipped call counting. For example, LoggingEchoTool.run() skipped the parent method, so its calls were never counted.

**3c.** Did you find all the bugs in `bugs.md`? Did you use AI to understand, or to get quick fixes?

After checking bugs.md, I found that I had missed the ReadOnlyAgent issue. I used AI to understand why the bugs happened, then checked the fixes myself.

**3d.** (Stretch) Run `AgentSetup.main`. Is the bug in `installDefaultTools` or in
`ReadOnlyAgent`? Explain using the Liskov substitution principle.

The bug is in ReadOnlyAgent. installDefaultTools() expects an Agent to allow tool registration, but ReadOnlyAgent throws an exception. This breaks the Liskov substitution principle because it cannot replace Agent without breaking the expected behavior.

## Question 4: Takeaways

**4a.** What surprised you most about working with an AI coding agent today?

I was surprised that AI could write code so quickly but still make mistakes that were easy to miss.

**4b.** What is one thing you will always do before accepting AI-suggested code?

I will always read the code and run the tests before accepting it.

**4c.** Complete the sentence: "I will use AI to ______, but I will always ______."

I will use AI to help me write and understand code, but I will always check its work myself.