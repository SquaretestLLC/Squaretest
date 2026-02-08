package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingWithListener1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithListener(0)

        // Verify the results
    }

    @Test
    void testDoSomethingWithCallback1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithCallback(false)

        // Verify the results
    }
}