package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.within

@CompileStatic
class MyClassTest {

    @Test
    void testIsEmpty() {
        assertThat(MyClass.isEmpty("cs")).isFalse()
    }

    @Test
    void testTrim() {
        assertThat(MyClass.trim("str")).isEqualTo("result")
    }

    @Test
    void testTrimToNull() {
        assertThat(MyClass.trimToNull("str")).isEqualTo("result")
        assertThat(MyClass.trimToNull("str")).isNull()
    }

    @Test
    void testTrimToOptional() {
        assertThat(MyClass.trimToOptional("str")).isEqualTo(Optional.of("value"))
        assertThat(MyClass.trimToOptional("str")).isEmpty()
    }

    @Test
    void testSplit() {
        assertThat(MyClass.split("str", "separatorChars")).isEqualTo(["result"] as String[])
        assertThat(MyClass.split("str", "separatorChars")).isEqualTo([] as String[])
    }

    @Test
    void testSplitAll() {
        // Setup
        def expectedResult = [] as String[][]

        // Run the test
        def result = MyClass.splitAll(["strs"] as String[], "separatorChars")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testReplaceFirst() {
        assertThat(MyClass.replaceFirst("text", "regex", "replacement")).isEqualTo("")
    }

    @Test
    void testBinomialCoefficient() {
        assertThat(MyClass.binomialCoefficient(0, 0)).isEqualTo(0L)
    }

    @Test
    void testDistance1() {
        assertThat(MyClass.distance([0] as int[], [0] as int[])).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testDistance2() {
        assertThat(MyClass.distance([0.0d] as double[], [0.0d] as double[])).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testMultMatrix() {
        // Setup
        def m1 = [] as int[][]
        def m2 = [] as int[][]
        def expectedResult = [] as int[][]

        // Run the test
        def result = MyClass.multMatrix(m1, m2)

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }
}
