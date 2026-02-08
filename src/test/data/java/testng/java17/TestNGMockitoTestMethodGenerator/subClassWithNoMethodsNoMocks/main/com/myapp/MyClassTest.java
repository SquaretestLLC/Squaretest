package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(null);
    }

    @Test
    void testGetItems1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetItems2() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
