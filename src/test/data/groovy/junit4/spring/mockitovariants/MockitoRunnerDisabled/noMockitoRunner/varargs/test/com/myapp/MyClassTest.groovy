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
    void testDoSomethingCool() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings")

        // Verify the results
    }

    @Test
    void testDoSomethingInC() {
        assert "result" == myClassUnderTest.doSomethingInC()
    }
}
