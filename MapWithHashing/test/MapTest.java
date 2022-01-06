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

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Constructor test with args.
     */
    @Test
    public final void testConstructorWithArgs() {
        /*
         * Set up variables, call method being tested.
         */
        Map<String, String> temp = this.createFromArgsTest("c", "cats", "d",
                "dogs");
        Map<String, String> tempExpected = this.createFromArgsRef("c", "cats",
                "d", "dogs");
        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat");
        /*
         * Call method under test
         */
        m.add("c", "cat");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat", "d",
                "dog");
        /*
         * Call method under test
         */
        m.add("d", "dog");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddSameValDiffKey() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat", "d",
                "cat");
        /*
         * Call method under test
         */
        m.add("d", "cat");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveToEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Pair<String, String> removed = m.remove("c");
        String key = removed.key();
        String val = removed.value();
        /*
         * Assert values match.
         */
        assertEquals(mExpected, m);
        assertEquals("c", key);
        assertEquals("cat", val);
    }

    @Test
    public final void testRemoveNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat", "d", "dog");
        Map<String, String> mExpected = this.createFromArgsRef("d", "dog");
        /*
         * Call method under test
         */
        Pair<String, String> removed = m.remove("c");
        String key = removed.key();
        String val = removed.value();
        /*
         * Assert values match.
         */
        assertEquals(mExpected, m);
        assertEquals("c", key);
        assertEquals("cat", val);
    }

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat", "d", "dog");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat", "d",
                "dog");
        /*
         * Call method under test
         */
        Pair<String, String> capture = m.removeAny();
        /*
         * Assert values match.
         */
        assertEquals(mExpected.hasKey(capture.key()), true);
        mExpected.remove(capture.key());
        assertEquals(mExpected, m);

    }

    @Test
    public final void testRemoveAnyToEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat");
        /*
         * Call method under test
         */
        Pair<String, String> capture = m.removeAny();
        /*
         * Assert values match.
         */
        assertEquals(mExpected.hasKey(capture.key()), true);
        mExpected.remove(capture.key());
        assertEquals(mExpected, m);

    }

    @Test
    public final void testValue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat");
        /*
         * Call method under test
         */
        String val = m.value("c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("cat", val);
    }

    @Test
    public final void testHasKeyFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat");
        /*
         * Call method under test
         */
        boolean f = m.hasKey("d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, f);
    }

    @Test
    public final void testHasKeyTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat");
        /*
         * Call method under test
         */
        boolean t = m.hasKey("c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, t);
    }

    @Test
    public final void testHasKeyEmptyMap() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean f = m.hasKey("d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, f);
    }

    @Test
    public final void testSizeZero() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int s = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(0, s);
    }

    @Test
    public final void testSizeNonZero() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("c", "cat");
        Map<String, String> mExpected = this.createFromArgsRef("c", "cat");
        /*
         * Call method under test
         */
        int s = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, s);
    }
}
