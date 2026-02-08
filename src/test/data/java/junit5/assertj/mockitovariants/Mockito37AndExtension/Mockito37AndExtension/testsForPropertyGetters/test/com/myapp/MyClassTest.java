package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", Arrays.asList("value"), "canonicalText",
                "defaultInitExpression", false);
    }

    @Test
    void testGetDisplayName() {
        assertThat(myClassUnderTest.getDisplayName()).isEqualTo("displayName");
    }

    @Test
    void testGetCanonicalName() {
        assertThat(myClassUnderTest.getCanonicalName()).isEqualTo("canonicalName");
    }

    @Test
    void testGetCanonicalNamesRequiredForType() {
        assertThat(myClassUnderTest.getCanonicalNamesRequiredForType()).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetLongNamesRequiredForType() {
        // Setup
        // Run the test
        final List<String> result = myClassUnderTest.getLongNamesRequiredForType();

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetCanonicalText() {
        assertThat(myClassUnderTest.getCanonicalText()).isEqualTo("canonicalText");
    }

    @Test
    void testGetCanonicalTextUpper() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getCanonicalTextUpper();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetDefaultInitExpression() {
        assertThat(myClassUnderTest.getDefaultInitExpression()).isEqualTo("defaultInitExpression");
    }

    @Test
    void testIsCanBeMocked() {
        assertThat(myClassUnderTest.isCanBeMocked()).isFalse();
    }
}
