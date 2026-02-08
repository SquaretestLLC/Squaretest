package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", List.of("value"), "canonicalText",
                "defaultInitExpression", false);
    }

    @Test
    void testGetLongNamesRequiredForType1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType();

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    void testGetCanonicalTextUpper1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper();

        // Verify the results
        assertEquals("result", result);
    }
}