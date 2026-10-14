package problem2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The specification of ConversationMemory and Message, as tests. Several fail
 * at first.
 */
class ConversationMemoryTest {
  private ConversationMemory memory;
  private Message hello;
  private Message hi;
  private Message help;

  @BeforeEach
  void setUp() {
    memory = new ConversationMemory(2);
    hello = new Message(Role.USER, "Hello");
    hi = new Message(Role.ASSISTANT, "Hi! How can I help?");
    help = new Message(Role.USER, "Count the words in my essay");
  }

  @Test
  void keepsMessagesInOrder() {
    memory.add(hello);
    memory.add(hi);
    assertEquals(List.of(hello, hi), memory.getMessages());
    assertEquals(hi, memory.getLast());
  }

  @Test
  void dropsTheOldestMessageWhenFull() {
    memory.add(hello);
    memory.add(hi);
    memory.add(help);
    assertEquals(2, memory.size());
    assertEquals(List.of(hi, help), memory.getMessages());
  }

  @Test
  void capacityMustBePositive() {
    assertThrows(IllegalArgumentException.class, () -> new ConversationMemory(0));
    assertThrows(IllegalArgumentException.class, () -> new ConversationMemory(-1));
  }

  @Test
  void nullMessagesAreRejected() {
    assertThrows(IllegalArgumentException.class, () -> memory.add(null));
    assertThrows(IllegalArgumentException.class, () -> new Message(null, "text"));
    assertThrows(IllegalArgumentException.class, () -> new Message(Role.USER, null));
  }

  @Test
  void getLastOnEmptyMemoryThrowsIllegalStateException() {
    assertThrows(IllegalStateException.class, () -> memory.getLast());
  }

  @Test
  void clientsCannotChangeTheMemoryThroughGetMessages() {
    memory.add(hello);
    memory.add(hi);
    List<Message> returned = memory.getMessages();
    try {
      returned.clear();   // either throw, or leave the memory unaffected
    } catch (UnsupportedOperationException expected) {
      // also fine
    }
    assertEquals(2, memory.size());
  }

  @Test
  void messagesWithTheSameContentAreEqual() {
    Message a = new Message(Role.USER, "Hello");
    Message b = new Message(Role.USER, "Hello");
    assertEquals(a, b);
    assertEquals(a.hashCode(), b.hashCode());
  }

  @Test
  void allFieldsArePrivateAndMessageFieldsAreFinal() {
    for (Class<?> c : new Class<?>[] {ConversationMemory.class, Message.class}) {
      for (Field f : c.getDeclaredFields()) {
        if (f.isSynthetic()) {
          // skip fields the compiler generates, e.g. for assert statements
          continue;
        }
        assertTrue(Modifier.isPrivate(f.getModifiers()),
            c.getSimpleName() + "." + f.getName() + " should be private");
      }
    }
    for (Field f : Message.class.getDeclaredFields()) {
      if (f.isSynthetic()) {
        continue;
      }
      assertTrue(Modifier.isFinal(f.getModifiers()), "Message." + f.getName() + " should be final");
    }
  }

  @Test
  void messageHasNoSetters() {
    for (Method m : Message.class.getMethods()) {
      assertTrue(!m.getName().startsWith("set"), "Message should be immutable: " + m.getName());
    }
  }
}
