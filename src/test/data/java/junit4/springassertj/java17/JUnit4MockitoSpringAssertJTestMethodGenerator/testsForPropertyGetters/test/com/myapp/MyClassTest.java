package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", List.of("value"), "canonicalText",
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
        assertThat(myClassUnderTest.getCanonicalNamesRequiredForType()).isEqualTo(List.of("value"));
    }

    @Test
    public void testGetLongNamesRequiredForType1() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType();

        // Verify the results
        assertThat(result).isEqualTo(List.of("value"));
    }

    @Test
    public void testGetCanonicalText() {
        assertThat(myClassUnderTest.getCanonicalText()).isEqualTo("canonicalText");
    }

    @Test
    public void testGetCanonicalTextUpper1() {
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