package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        myClassUnderTest = null /* TODO: construct the instance */;
    }

    @Test
    void testGetFullName() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = myClassUnderTest.getFullName();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFullNameWithSuffix() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = myClassUnderTest.getFullNameWithSuffix();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
