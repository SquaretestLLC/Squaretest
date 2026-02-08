package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
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

    @Test
    void testGetItems3() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
