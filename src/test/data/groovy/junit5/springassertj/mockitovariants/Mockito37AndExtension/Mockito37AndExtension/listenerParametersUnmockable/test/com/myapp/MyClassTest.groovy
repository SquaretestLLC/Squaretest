package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingWithListener() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithListener(0)

        // Verify the results
    }

    @Test
    void testDoSomethingWithCallback() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithCallback(false)

        // Verify the results
    }
}
