import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    /*
     * Constructor test case.
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
     * Constructor test case.
     */
    @Test
    public final void testConstructorWithArgs() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");
        Set<String> tempExpected = this.createFromArgsRef("cat", "dog", "bird");

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

        String testString1 = temp.remove("bird");

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
        assertEquals("bird", testString1);

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
    public final void testContains() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");

        boolean check = temp.contains("cat");
        boolean check2 = temp.contains("snake");

        /*
         * Assert values match.
         */
        assertEquals(check, true);
        assertEquals(check2, false);

    }

    /*
     * Contains test case with ints.
     */
    @Test
    public final void testContainsInt() {
        /*
         * Set up variables, call method being tested.
         */
        Set<String> temp = this.createFromArgsTest("cat", "dog", "bird");

        boolean check = temp.contains("cat");
        boolean check2 = temp.contains("snake");

        /*
         * Assert values match.
         */
        assertEquals(check, true);
        assertEquals(check2, false);

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
        Set<String> temp2 = this.createFromArgsTest();

        int sizeTemp = temp.size();
        int sizeTemp2 = temp2.size();

        /*
         * Assert values match.
         */
        assertEquals(sizeTemp, 3);
        assertEquals(sizeTemp2, 0);

    }

}
