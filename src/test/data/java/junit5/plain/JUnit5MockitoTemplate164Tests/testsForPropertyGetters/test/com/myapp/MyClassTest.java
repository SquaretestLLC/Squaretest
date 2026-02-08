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
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList("value"), "canonicalText",
                "defaultInitExpression", false);
    }

    @Test
    void testGetLongNamesRequiredForType() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType();

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetCanonicalTextUpper() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper();

        // Verify the results
        assertEquals("result", result);
    }
}
