package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(null);
    }

    @Test
    public void testGetItems1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems2() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems3() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
