package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    @Test
    void testDoSomething() {
        // Setup
        def myClassUnderTest = new MyClass()
        def myFoo = null

        // Run the test
        myClassUnderTest.doSomething(myFoo)

        // Verify the results
    }
}
