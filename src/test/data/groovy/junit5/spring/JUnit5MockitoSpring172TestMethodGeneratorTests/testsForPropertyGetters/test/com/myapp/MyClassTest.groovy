package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass("displayName", "canonicalName", ["value"], "canonicalText",
                "defaultInitExpression", false)
    }

    @Test
    void testGetLongNamesRequiredForType1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getLongNamesRequiredForType()

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetCanonicalTextUpper1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getCanonicalTextUpper()

        // Verify the results
        assert "result" == result
    }
}