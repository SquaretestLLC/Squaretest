package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(Arrays.asList(), new HashMap<>(), new HashSet<>());
    }

    @Test
    void testGetMapEntry() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = myClassUnderTest.getMapEntry("customerKey");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
