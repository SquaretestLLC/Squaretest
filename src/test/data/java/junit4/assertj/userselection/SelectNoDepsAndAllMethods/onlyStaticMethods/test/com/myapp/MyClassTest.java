package com.myapp;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class MyClassTest {

    @Test
    public void testIsEmpty() {
        assertThat(MyClass.isEmpty("cs")).isFalse();
    }

    @Test
    public void testTrim() {
        assertThat(MyClass.trim("str")).isEqualTo("result");
    }

    @Test
    public void testTrimToNull() {
        assertThat(MyClass.trimToNull("str")).isEqualTo("result");
        assertThat(MyClass.trimToNull("str")).isNull();
    }

    @Test
    public void testTrimToOptional() {
        assertThat(MyClass.trimToOptional("str")).isEqualTo(Optional.of("value"));
        assertThat(MyClass.trimToOptional("str")).isEmpty();
    }

    @Test
    public void testSplit() {
        assertThat(MyClass.split("str", "separatorChars")).isEqualTo(new String[]{"result"});
        assertThat(MyClass.split("str", "separatorChars")).isEqualTo(new String[]{});
    }

    @Test
    public void testSplitAll() {
        // Setup
        final String[][] expectedResult = new String[][]{{"result"}};

        // Run the test
        final String[][] result = MyClass.splitAll(new String[]{"strs"}, "separatorChars");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testReplaceFirst() {
        assertThat(MyClass.replaceFirst("text", "regex", "replacement")).isEqualTo("");
    }

    @Test
    public void testBinomialCoefficient() {
        assertThat(MyClass.binomialCoefficient(0, 0)).isEqualTo(0L);
    }

    @Test
    public void testDistance1() {
        assertThat(MyClass.distance(new int[]{0}, new int[]{0})).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testDistance2() {
        assertThat(MyClass.distance(new double[]{0.0}, new double[]{0.0})).isEqualTo(0.0, within(0.0001));
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
        assertThat(result).isEqualTo(expectedResult);
    }
}
