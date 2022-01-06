import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public final void testNoArgConstructor() {

        Stack<String> testStack = this.createFromArgsTest();
        Stack<String> expectedStack = this.createFromArgsRef();

        assertEquals(expectedStack, testStack);
    }

    @Test
    public final void testConstructorArgs() {

        Stack<String> testStack = this.createFromArgsTest("blue", "red",
                "purple");
        Stack<String> expectedStack = this.createFromArgsRef("blue", "red",
                "purple");

        assertEquals(expectedStack, testStack);
    }

    @Test
    public final void testPushOntoEmpty() {

        Stack<String> testStack = this.createFromArgsTest();
        Stack<String> expectedStack = this.createFromArgsRef("purple");
        String toPush = "purple";

        testStack.push(toPush);

        assertEquals(expectedStack, testStack);
    }

    @Test
    public final void testPushNonEmpty() {

        Stack<String> testStack = this.createFromArgsTest("blue");
        Stack<String> expectedStack = this.createFromArgsRef("purple", "blue");
        String toPush = "purple";

        testStack.push(toPush);

        assertEquals(expectedStack, testStack);
    }

    @Test
    public final void testPopToEmpty() {

        Stack<String> testStack = this.createFromArgsTest("blue");
        Stack<String> expectedStack = this.createFromArgsRef();

        String Popped = testStack.pop();

        assertEquals(expectedStack, testStack);
        assertEquals("blue", Popped);
    }

    @Test
    public final void testPopToNonEmpty() {

        Stack<String> testStack = this.createFromArgsTest("blue", "purple");
        Stack<String> expectedStack = this.createFromArgsRef("purple");

        String Popped = testStack.pop();

        assertEquals(expectedStack, testStack);
        assertEquals("blue", Popped);
    }

    @Test
    public final void testLengthEmpty() {

        Stack<String> testStack = this.createFromArgsTest();
        Stack<String> expectedStack = this.createFromArgsRef();

        int testLen = testStack.length();

        assertEquals(expectedStack, testStack);
        assertEquals(0, testLen);
    }

    @Test
    public final void testLengthNonEmpty() {

        Stack<String> testStack = this.createFromArgsTest("blue");
        Stack<String> expectedStack = this.createFromArgsRef("blue");

        int testLen = testStack.length();

        assertEquals(expectedStack, testStack);
        assertEquals(1, testLen);
    }

}
