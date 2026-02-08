package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList("value"), "canonicalText",
                "defaultInitExpression", false);
    }

    @Test
    public void testGetDisplayName() {
        assertThat(myClassUnderTest.getDisplayName()).isEqualTo("displayName");
    }

    @Test
    public void testGetCanonicalName() {
        assertThat(myClassUnderTest.getCanonicalName()).isEqualTo("canonicalName");
    }

    @Test
    public void testGetCanonicalNamesRequiredForType() {
        assertThat(myClassUnderTest.getCanonicalNamesRequiredForType()).isEqualTo(Arrays.asList("value"));
    }

    @Test
    public void testGetLongNamesRequiredForType() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType();

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    public void testGetCanonicalText() {
        assertThat(myClassUnderTest.getCanonicalText()).isEqualTo("canonicalText");
    }

    @Test
    public void testGetCanonicalTextUpper() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetDefaultInitExpression() {
        assertThat(myClassUnderTest.getDefaultInitExpression()).isEqualTo("defaultInitExpression");
    }

    @Test
    public void testIsCanBeMocked() {
        assertThat(myClassUnderTest.isCanBeMocked()).isFalse();
    }
}
