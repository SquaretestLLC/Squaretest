package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
