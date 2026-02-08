package com.myapp;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testGetItems11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    public void testGetItems21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(List.of("value"), result);
    }
}