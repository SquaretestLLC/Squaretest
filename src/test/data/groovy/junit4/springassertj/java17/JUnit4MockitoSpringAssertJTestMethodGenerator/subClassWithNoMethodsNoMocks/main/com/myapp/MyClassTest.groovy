package com.myapp

import org.junit.Before
import org.junit.Test

import java.util.Arrays
import java.util.List

import static org.junit.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(null)
    }

    @Test
    void testGetItems1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems()

        // Verify the results
        assertEquals(Arrays.asList("value"), result)
    }

    @Test
    void testGetItems2() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertEquals(Arrays.asList("value"), result)
    }
}
