package problem1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Tool hierarchy. They won't compile until BaseTool and both
 * tools exist.
 */
class ToolTest {
  private Tool wordCount;
  private Tool uppercase;

  @BeforeEach
  void setUp() {
    wordCount = new WordCountTool();
    uppercase = new UppercaseTool();
  }

  @Test
  void toolsHaveNamesAndDescriptions() {
    assertEquals("word_count", wordCount.getName());
    assertEquals("uppercase", uppercase.getName());
    assertFalse(wordCount.getDescription().isBlank());
    assertFalse(uppercase.getDescription().isBlank());
  }

  @Test
  void wordCountCountsWords() {
    assertEquals("4", wordCount.run("the quick brown fox"));
    assertEquals("2", wordCount.run("  hello   agent  "));
    assertEquals("0", wordCount.run(""));
    assertEquals("0", wordCount.run("   "));
  }

  @Test
  void uppercaseConvertsToUpperCase() {
    assertEquals("HELLO AGENT", uppercase.run("hello agent"));
  }

  @Test
  void successfulRunsAreCounted() {
    assertEquals(0, wordCount.getCallCount());
    wordCount.run("one");
    wordCount.run("two words");
    assertEquals(2, wordCount.getCallCount());
    // each tool counts its own calls
    assertEquals(0, uppercase.getCallCount());
  }

  @Test
  void nullInputIsRejectedAndNotCounted() {
    assertThrows(IllegalArgumentException.class, () -> uppercase.run(null));
    assertEquals(0, uppercase.getCallCount());
  }

  @Test
  void baseToolRejectsBlankName() {
    assertThrows(IllegalArgumentException.class, () -> new BaseTool(" ", "description") {
      @Override
      protected String execute(String input) {
        return input;
      }
    });
  }

  @Test
  void bothToolsShareAnAbstractBaseClass() {
    assertTrue(Modifier.isAbstract(BaseTool.class.getModifiers()));
    assertTrue(BaseTool.class.isAssignableFrom(WordCountTool.class));
    assertTrue(BaseTool.class.isAssignableFrom(UppercaseTool.class));
  }

  @Test
  void allFieldsArePrivate() {
    for (Class<?> c : new Class<?>[] {BaseTool.class, WordCountTool.class, UppercaseTool.class}) {
      for (Field f : c.getDeclaredFields()) {
        if (f.isSynthetic()) {
          // skip fields the compiler generates, e.g. for assert statements
          continue;
        }
        assertTrue(Modifier.isPrivate(f.getModifiers()),
            c.getSimpleName() + "." + f.getName() + " should be private");
      }
    }
  }
}
