package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.junit.Assert.*

@CompileStatic
class MyClassTest {

    @Test
    void testIsEmpty() {
        assertFalse(MyClass.isEmpty("cs"))
    }

    @Test
    void testTrim() {
        assert "result" == MyClass.trim("str")
    }

    @Test
    void testTrimToNull() {
        assert "result" == MyClass.trimToNull("str")
        assertNull(MyClass.trimToNull("str"))
    }

    @Test
    void testTrimToOptional() {
        assert Optional.of("value") == MyClass.trimToOptional("str")
        assert Optional.empty() == MyClass.trimToOptional("str")
    }

    @Test
    void testSplit() {
        assert ["result"] as String[] == MyClass.split("str", "separatorChars")
        assert [] as String[] == MyClass.split("str", "separatorChars")
    }

    @Test
    void testSplitAll() {
        // Setup
        def expectedResult = [] as String[][]

        // Run the test
        def result = MyClass.splitAll(["strs"] as String[], "separatorChars")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testReplaceFirst() {
        assert "" == MyClass.replaceFirst("text", "regex", "replacement")
    }

    @Test
    void testBinomialCoefficient() {
        assert 0L == MyClass.binomialCoefficient(0, 0)
    }

    @Test
    void testDistance1() {
        assertEquals(0.0d, MyClass.distance([0] as int[], [0] as int[]), 0.0001d)
    }

    @Test
    void testDistance2() {
        assertEquals(0.0d, MyClass.distance([0.0d] as double[], [0.0d] as double[]), 0.0001d)
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
        assert expectedResult == result
    }
}
