package com.myapp

import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = null /* TODO: construct the instance */
    }

    @Test
    void testGetFullName() {
        // Setup
        final String expectedResult = "result"

        // Run the test
        final String result = myClassUnderTest.getFullName()

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    void testGetFullNameWithSuffix() {
        // Setup
        final String expectedResult = "result"

        // Run the test
        final String result = myClassUnderTest.getFullNameWithSuffix()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
