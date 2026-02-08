package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void testGetCanonicalText() {
        assertEquals("canonicalText", myClassUnderTest.getCanonicalText());
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
