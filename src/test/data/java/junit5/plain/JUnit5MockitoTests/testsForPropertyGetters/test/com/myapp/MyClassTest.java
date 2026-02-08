package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList("value"), "canonicalText",
                "defaultInitExpression", false);
    }

    @Test
    void testGetDisplayName() {
        assertEquals("displayName", myClassUnderTest.getDisplayName());
    }

    @Test
    void testGetCanonicalName() {
        assertEquals("canonicalName", myClassUnderTest.getCanonicalName());
    }

    @Test
    void testGetCanonicalNamesRequiredForType() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.getCanonicalNamesRequiredForType());
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
    void testGetCanonicalText() {
        assertEquals("canonicalText", myClassUnderTest.getCanonicalText());
    }

    @Test
    void testGetCanonicalTextUpper() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInitExpression() {
        assertEquals("defaultInitExpression", myClassUnderTest.getDefaultInitExpression());
    }

    @Test
    void testIsCanBeMocked() {
        assertFalse(myClassUnderTest.isCanBeMocked());
    }
}
