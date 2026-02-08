package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFooAndBar1() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(null)
        myClassUnderTest.setBarService(null)
        myClassUnderTest.setDefaultBarId("defaultBarId")

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
