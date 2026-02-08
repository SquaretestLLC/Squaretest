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
    void testDoSomething() {
        // Setup
        def myFoo = null

        // Run the test
        myClassUnderTest.doSomething(myFoo)

        // Verify the results
    }
}
