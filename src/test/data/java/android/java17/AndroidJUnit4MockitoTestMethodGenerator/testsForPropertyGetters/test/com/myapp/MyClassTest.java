package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@SmallTest
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
    public void testGetLongNamesRequiredForType1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType();

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    public void testGetCanonicalText() {
        assertEquals("canonicalText", myClassUnderTest.getCanonicalText());
    }

    @Test
    public void testGetCanonicalTextUpper1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper();

        // Verify the results
        assertEquals("result", result);
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