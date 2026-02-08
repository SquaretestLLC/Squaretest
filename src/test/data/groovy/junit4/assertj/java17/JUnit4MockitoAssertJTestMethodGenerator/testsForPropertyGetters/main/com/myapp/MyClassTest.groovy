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
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList(), "canonicalText", "defaultInitExpression", false)
    }

    @Test
    void testGetLongNamesRequiredForType() {
        // Setup
        final List<String> expectedResult = Arrays.asList()

        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType()

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    void testGetCanonicalTextUpper() {
        // Setup
        final String expectedResult = "result"

        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
