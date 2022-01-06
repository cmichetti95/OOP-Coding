import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /*
     * Constructor test.
     */
    @Test
    public final void testConstructorNoArgs() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest();
        Map<String, String> tempExpected = this.createFromArgsRef();
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test with args.
     */
    @Test
    public final void testConstructorWithArgs() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cats", "2", "dogs",
                "2");
        Map<String, String> tempExpected = this.createFromArgsRef("cats", "2",
                "dogs", "2");
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Add test to empty map.
     */
    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest();
        Map<String, String> tempExpected = this.createFromArgsRef("cats", "2");

        String key = "cats";
        String val = "2";
        temp.add(key, val);
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Add test to non-empty map.
     */
    @Test
    public final void testAddToNonEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cats", "2");
        Map<String, String> tempExpected = this.createFromArgsRef("cats", "2",
                "dogs", "2");

        String key = "dogs";
        String val = "2";
        temp.add(key, val);
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Remove test to empty map.
     */
    @Test
    public final void testRemoveToEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cats", "2");
        Map<String, String> tempExpected = this.createFromArgsRef();

        Pair<String, String> removed = temp.remove("cats");
        String key = removed.key();
        String val = removed.value();
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
        assertEquals("cats", key);
        assertEquals("2", val);
    }

    /*
     * Remove test to non-empty map.
     */
    @Test
    public final void testRemoveNonEmpty() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cats", "2", "dogs",
                "2");
        Map<String, String> tempExpected = this.createFromArgsRef("dogs", "2");

        Pair<String, String> removed = temp.remove("cats");
        String key = removed.key();
        String val = removed.value();
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
        assertEquals("cats", key);
        assertEquals("2", val);
    }

    /*
     * RemoveAny test.
     */
    public final void testRemoveAny() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cat", "1", "dog",
                "2");
        Map<String, String> tempExpected = this.createFromArgsRef("cat", "1",
                "dog", "2");

        Pair<String, String> capture = temp.removeAny();

        /*
         * Assert values match.
         */
        assertEquals(tempExpected.hasKey(capture.key()), true);
        tempExpected.remove(capture.key());
        assertEquals(tempExpected, temp);

    }

    /*
     * Value test case.
     */
    @Test
    public final void testValue() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cat", "1", "dog",
                "2");

        String testVal = temp.value("cat");
        String expectedVal = "1";

        /*
         * Assert values match.
         */

        assertEquals(expectedVal, testVal);
    }

    /*
     * HasKey test case (tests both).
     */
    @Test
    public final void testHasKey() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cat", "1", "dog",
                "2");

        boolean testVal = temp.hasKey("cat");
        boolean expectedVal = true;

        boolean testVal2 = temp.hasKey("bird");
        boolean expectedVal2 = false;

        /*
         * Assert values match.
         */

        assertEquals(expectedVal, testVal);
        assertEquals(expectedVal2, testVal2);
    }

    /*
     * Size test case non-zero.
     */
    @Test
    public final void testSizeZero() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest();

        int testSize = temp.size();
        int expectedSize = 0;

        /*
         * Assert values match.
         */

        assertEquals(expectedSize, testSize);
    }

    /*
     * Size test case non-zero.
     */
    @Test
    public final void testSizeNonZero() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("cat", "1", "dog",
                "2");

        int testSize = temp.size();
        int expectedSize = 2;

        /*
         * Assert values match.
         */

        assertEquals(expectedSize, testSize);
    }

}
