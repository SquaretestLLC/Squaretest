package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Test
    public void testIsEmpty() {
        assertFalse(MyClass.isEmpty("cs"));
    }

    @Test
    public void testTrim() {
        assertEquals("result", MyClass.trim("str"));
    }

    @Test
    public void testTrimToNull() {
        assertEquals("result", MyClass.trimToNull("str"));
        assertNull(MyClass.trimToNull("str"));
    }

    @Test
    public void testTrimToOptional() {
        assertEquals(Optional.of("value"), MyClass.trimToOptional("str"));
        assertEquals(Optional.empty(), MyClass.trimToOptional("str"));
    }

    @Test
    public void testSplit() {
        assertArrayEquals(new String[]{"result"}, MyClass.split("str", "separatorChars"));
        assertArrayEquals(new String[]{}, MyClass.split("str", "separatorChars"));
    }

    @Test
    public void testSplitAll() {
        // Setup
        final String[][] expectedResult = new String[][]{{"result"}};

        // Run the test
        final String[][] result = MyClass.splitAll(new String[]{"strs"}, "separatorChars");

        // Verify the results
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testReplaceFirst() {
        assertEquals("", MyClass.replaceFirst("text", "regex", "replacement"));
    }

    @Test
    public void testBinomialCoefficient() {
        assertEquals(0L, MyClass.binomialCoefficient(0, 0));
    }

    @Test
    public void testDistance1() {
        assertEquals(0.0, MyClass.distance(new int[]{0}, new int[]{0}), 0.0001);
    }

    @Test
    public void testDistance2() {
        assertEquals(0.0, MyClass.distance(new double[]{0.0}, new double[]{0.0}), 0.0001);
    }

    @Test
    public void testMultMatrix() {
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
