package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", List.of("value"), "canonicalText",
                "defaultInitExpression", false);
    }

    @Test
    public void testGetDisplayName() {
        assertEquals("displayName", myClassUnderTest.getDisplayName());
    }

    @Test
    public void testGetCanonicalName() {
        assertEquals("canonicalName", myClassUnderTest.getCanonicalName());
    }

    @Test
    public void testGetCanonicalNamesRequiredForType() {
        assertEquals(List.of("value"), myClassUnderTest.getCanonicalNamesRequiredForType());
    }

    @Test
    public void testGetCanonicalText() {
        assertEquals("canonicalText", myClassUnderTest.getCanonicalText());
    }

    @Test
    public void testGetDefaultInitExpression() {
        assertEquals("defaultInitExpression", myClassUnderTest.getDefaultInitExpression());
    }

    @Test
    public void testIsCanBeMocked() {
        assertFalse(myClassUnderTest.isCanBeMocked());
    }
}