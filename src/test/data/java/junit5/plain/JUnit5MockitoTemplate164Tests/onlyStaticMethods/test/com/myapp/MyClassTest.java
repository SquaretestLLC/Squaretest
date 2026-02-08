package com.myapp;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void testIsEmpty() {
        assertTrue(MyClass.isEmpty("cs"));
    }

    @Test
    void testTrim() {
        assertEquals("result", MyClass.trim("str"));
    }

    @Test
    void testTrimToNull() {
        assertEquals("result", MyClass.trimToNull("str"));
        assertNull(MyClass.trimToNull("str"));
    }

    @Test
    void testTrimToOptional() {
        assertEquals(Optional.of("value"), MyClass.trimToOptional("str"));
        assertEquals(Optional.empty(), MyClass.trimToOptional("str"));
    }

    @Test
    void testSplit() {
        assertArrayEquals(new String[]{"result"}, MyClass.split("str", "separatorChars"));
        assertArrayEquals(new String[]{}, MyClass.split("str", "separatorChars"));
    }

    @Test
    void testSplitAll() {
        // Setup
        final String[][] expectedResult = new String[][]{{"result"}};

        // Run the test
        final String[][] result = MyClass.splitAll(new String[]{"strs"}, "separatorChars");

        // Verify the results
        assertArrayEquals(expectedResult, result);
    }

    @Test
    void testReplaceFirst() {
        assertEquals("", MyClass.replaceFirst("text", "regex", "replacement"));
        assertThrows(PatternSyntaxException.class, () -> MyClass.replaceFirst("text", "regex", "replacement"));
    }

    @Test
    void testBinomialCoefficient() {
        assertEquals(0L, MyClass.binomialCoefficient(0, 0));
        assertThrows(IllegalArgumentException.class, () -> MyClass.binomialCoefficient(0, 0));
        assertThrows(ArithmeticException.class, () -> MyClass.binomialCoefficient(0, 0));
    }

    @Test
    void testDistance1() {
        assertEquals(0.0, MyClass.distance(new int[]{0}, new int[]{0}), 0.0001);
    }

    @Test
    void testDistance2() {
        assertEquals(0.0, MyClass.distance(new double[]{0.0}, new double[]{0.0}), 0.0001);
    }

    @Test
    void testMultMatrix() {
        // Setup
        final int[][] m1 = new int[][]{{0}};
        final int[][] m2 = new int[][]{{0}};
        final int[][] expectedResult = new int[][]{{0}};

        // Run the test
        final int[][] result = MyClass.multMatrix(m1, m2);

        // Verify the results
        assertArrayEquals(expectedResult, result);
    }
}
