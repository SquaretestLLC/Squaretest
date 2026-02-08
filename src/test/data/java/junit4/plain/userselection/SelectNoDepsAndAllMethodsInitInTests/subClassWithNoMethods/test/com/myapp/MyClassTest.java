package com.myapp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testGetItems1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
