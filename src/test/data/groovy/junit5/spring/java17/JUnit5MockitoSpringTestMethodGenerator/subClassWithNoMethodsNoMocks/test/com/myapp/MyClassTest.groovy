package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(null)
    }

    @Test
    void testGetItems11() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems21() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert ["value"] == result
    }
}