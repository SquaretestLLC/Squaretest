package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFooAndBar1() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, "defaultBarId")

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
