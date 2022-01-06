import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Connor Michetti and Nick Folino
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /*
     * No arg constructor test.
     */
    @Test
    public final void testNoArgConstructor() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest();
        Set<String> tempExpected = this.createFromArgsRef();

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Test constructor with args.
     */
    @Test
    public final void testArgConstructor() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Add test case.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest();
        Set<String> tempExpected = this.createFromArgsRef("cat");

        temp.add("cat");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);

    }

    /*
     * Add test case.
     */
    @Test
    public final void testAddToNonEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog");

        temp.add("dog");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);

    }

    /*
     * Add test case.
     */
    @Test
    public final void testAddToMultiple() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog", "bird",
                "snake");

        temp.add("snake");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);

    }

    /*
     * Remove test case.
     */
    @Test
    public final void testRemoveToEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat");
        Set<String> tempExpected = this.createFromArgsRef();

        String testString1 = temp.remove("cat");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
        assertEquals("cat", testString1);

    }

    /*
     * Remove test case.
     */
    @Test
    public final void testRemove() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog");

        String tempStr = temp.remove("bird");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
        assertEquals("bird", tempStr);

    }

    /*
     * RemoveAny test case.
     */
    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog", "bird");

        String capture = temp.removeAny();

        /*
         * Assert values match.
         */
        assertEquals(tempExpected.contains(capture), true);
        tempExpected.remove(capture);
        assertEquals(tempExpected, temp);

    }

    /*
     * RemoveAny test case.
     */
    @Test
    public final void testRemoveAnyToEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat");
        Set<String> tempExpected = this.createFromArgsRef();

        String capture = temp.removeAny();

        /*
         * Assert values match.
         */
        assertEquals("cat", capture);
        assertEquals(tempExpected, temp);

    }

    /*
     * Contains test case.
     */
    @Test
    public final void testContainsTrue() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog", "bird");

        boolean check = temp.contains("cat");

        /*
         * Assert values match.
         */
        assertEquals(true, check);
        assertEquals(tempExpected, temp);

    }

    /*
     * Contains test case.
     */
    @Test
    public final void testContainsFalse() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog", "bird");

        boolean check = temp.contains("snake");

        /*
         * Assert values match.
         */
        assertEquals(false, check);
        assertEquals(tempExpected, temp);

    }

    /*
     * Contains test case.
     */
    @Test
    public final void testContainsEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest();
        Set<String> tempExpected = this.createFromArgsRef();

        boolean check = temp.contains("snake");

        /*
         * Assert values match.
         */
        assertEquals(false, check);
        assertEquals(tempExpected, temp);

    }

    /*
     * Size test case.
     */
    @Test
    public final void testSize() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog", "bird");

        int sizeTemp = temp.size();

        /*
         * Assert values match.
         */
        assertEquals(3, sizeTemp);
        assertEquals(tempExpected, temp);

    }

    /*
     * Size test case.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest();
        Set<String> tempExpected = this.createFromArgsRef();

        int sizeTemp = temp.size();

        /*
         * Assert values match.
         */
        assertEquals(0, sizeTemp);
        assertEquals(tempExpected, temp);

    }

}
