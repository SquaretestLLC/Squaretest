package com.myapp

import org.junit.Before
import org.junit.Test

import java.net.Socket

import static org.junit.Assert.assertArrayEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new int[]{}, new String[]{}, new Socket[][]{})
    }

    @Test
    void testCompareArray() {
        // Setup
        final Socket[][] sockets = new Socket[][]{}

        // Run the test
        myClassUnderTest.compareArray(new String[]{}, sockets)

        // Verify the results
    }

    @Test
    void testReturnTheArray() {
        // Setup
        final String[] expectedResult = new String[]{}

        // Run the test
        final String[] result = myClassUnderTest.returnTheArray()

        // Verify the results
        assertArrayEquals(expectedResult, result)
    }

    @Test
    void testReturnTheArrays() {
        // Setup
        final Socket[][] expectedResult = new Socket[][]{}

        // Run the test
        final Socket[][] result = myClassUnderTest.returnTheArrays()

        // Verify the results
        assertArrayEquals(expectedResult, result)
    }
}
