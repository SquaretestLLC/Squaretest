package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFooAndBar1() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.fooService = null
        myClassUnderTest.barService = null
        myClassUnderTest.defaultBarId = "defaultBarId"

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
