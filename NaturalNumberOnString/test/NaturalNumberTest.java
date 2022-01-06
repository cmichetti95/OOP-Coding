import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Connor Michetti and Nick Folino.
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /*
     * Constructor test with no argument.
     */
    @Test
    public final void testNoArg() {
        /*
         * Set up variables, call method being tested.
         */
        NaturalNumber temp = this.constructorTest();
        NaturalNumber tempExpected = this.constructorRef();

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test created from int that equals 0.
     */
    @Test
    public final void testConstructFromIntZero() {
        /*
         * Set up variables, call method being tested.
         */
        int i = 0;
        NaturalNumber temp = this.constructorTest(i);
        NaturalNumber tempExpected = this.constructorRef(i);

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test created from int.
     */
    @Test
    public final void testConstructFromInt() {
        /*
         * Set up variables, call method being tested.
         */
        int i = 12;
        NaturalNumber temp = this.constructorTest(i);
        NaturalNumber tempExpected = this.constructorRef(i);

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test created from string that equals 0.
     */
    @Test
    public final void testConstructFromStringZero() {
        /*
         * Set up variables, call method being tested.
         */
        String i = "0";
        NaturalNumber temp = this.constructorTest(i);
        NaturalNumber tempExpected = this.constructorRef(i);

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test created from string.
     */
    @Test
    public final void testConstructFromStringNonZero() {
        /*
         * Set up variables, call method being tested.
         */
        String i = "12";
        NaturalNumber temp = this.constructorTest(i);
        NaturalNumber tempExpected = this.constructorRef(i);

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test created from a NN that equals 0.
     */
    @Test
    public final void testConstructFromNNZero() {
        /*
         * Set up variables, call method being tested.
         */
        NaturalNumber zero = new NaturalNumber1L(0);
        NaturalNumber temp = this.constructorTest(zero);
        NaturalNumber tempExpected = this.constructorRef(zero);

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test created from a NN.
     */
    @Test
    public final void testConstructFromNNNonZero() {
        /*
         * Set up variables, call method being tested.
         */
        NaturalNumber zero = new NaturalNumber1L(12);
        NaturalNumber temp = this.constructorTest(zero);
        NaturalNumber tempExpected = this.constructorRef(zero);

        /*
         * Assert values match.
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * Constructor test with a string past the int limit.
     */
    @Test
    public final void testStringConstructorPastMaxInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber temp = this.constructorTest("98765432123456789");
        NaturalNumber tempExpected = this.constructorRef("98765432123456789");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(tempExpected, temp);
    }

    /*
     * MultiplyBy10 test with zero.
     */
    @Test
    public final void testMultiplyByTenWithZeroNoAdd() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * MultiplyBy10 test with just the addition portion applying.
     */
    @Test
    public final void testMultiplyByTenWithZeroPlusFive() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(5);
        /*
         * Call method under test
         */
        n.multiplyBy10(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * MultiplyBy10 test with single digit.
     */
    @Test
    public final void testMultiplyByTenWithOnePlusNine() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(19);
        /*
         * Call method under test
         */
        n.multiplyBy10(9);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * MultiplyBy10 test with single digit.
     */
    @Test
    public final void testMultiplyByTenWithOneDigitPlusZero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(2);
        NaturalNumber nExpected = this.constructorRef(20);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * MultiplyBy10 test past the int limit.
     */
    @Test
    public final void testMultiplyByTenPastIntLimit() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("2147483647");
        NaturalNumber nExpected = this.constructorRef("21474836470");
        /*
         * Call method under test
         */
        n.multiplyBy10(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * DivideBy10 test with just zero.
     */
    @Test
    public final void testDivideByTenWithZero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(0, remainder);
    }

    /*
     * DivideBy10 test with just a single digit.
     */
    @Test
    public final void testDivideByTenWithNonZeroOneDigit() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(8);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(8, remainder);
    }

    /*
     * DivideBy10 test with a double digit resulting in a remainder.
     */
    @Test
    public final void testDivideByTenWithRemainder() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(18);
        NaturalNumber nExpected = this.constructorRef(1);
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(8, remainder);
    }

    /*
     * DivideBy10 test with a double digit resulting in no remainder.
     */
    @Test
    public final void testDivideByTenWithNoRemainder() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(20);
        NaturalNumber nExpected = this.constructorRef(2);
        /*
         * Call method under test
         */
        int remainder = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(0, remainder);
    }

    /*
     * isZero test for true.
     */
    @Test
    public final void testIsZeroTrue() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        boolean check = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertTrue(check);
    }

    /*
     * isZero test for false.
     */
    @Test
    public final void testIsZeroFalse() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(9);
        NaturalNumber nExpected = this.constructorRef(9);
        /*
         * Call method under test
         */
        boolean check = n.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertTrue(!check);
    }
}
