package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", ["value"], "canonicalText",
                "defaultInitExpression", false)
    }

    @Test
    void testGetDisplayName() {
        assertThat(myClassUnderTest.getDisplayName()).isEqualTo("displayName")
    }

    @Test
    void testGetCanonicalName() {
        assertThat(myClassUnderTest.getCanonicalName()).isEqualTo("canonicalName")
    }

    @Test
    void testGetCanonicalNamesRequiredForType() {
        assertThat(myClassUnderTest.getCanonicalNamesRequiredForType()).isEqualTo(["value"])
    }

    @Test
    void testGetLongNamesRequiredForType1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getLongNamesRequiredForType()

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetCanonicalText() {
        assertThat(myClassUnderTest.getCanonicalText()).isEqualTo("canonicalText")
    }

    @Test
    void testGetCanonicalTextUpper1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getCanonicalTextUpper()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetDefaultInitExpression() {
        assertThat(myClassUnderTest.getDefaultInitExpression()).isEqualTo("defaultInitExpression")
    }

    @Test
    void testIsCanBeMocked() {
        assertThat(myClassUnderTest.isCanBeMocked()).isFalse()
    }
}