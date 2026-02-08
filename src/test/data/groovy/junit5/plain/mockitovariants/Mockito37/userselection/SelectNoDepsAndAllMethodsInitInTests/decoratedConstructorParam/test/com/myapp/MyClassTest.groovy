package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}
