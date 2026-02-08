package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomething() {
        // Setup
        def myFoo = null

        // Run the test
        myClassUnderTest.doSomething(myFoo)

        // Verify the results
    }
}
