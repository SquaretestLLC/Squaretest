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
    void testDoSomethingWithListener() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithListener("someListener")

        // Verify the results
    }

    @Test
    void testDoSomethingWithCallback() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithCallback("theCallback")

        // Verify the results
    }
}
