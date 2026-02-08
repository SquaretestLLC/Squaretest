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
    void testGetFoo1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}
