package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
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
