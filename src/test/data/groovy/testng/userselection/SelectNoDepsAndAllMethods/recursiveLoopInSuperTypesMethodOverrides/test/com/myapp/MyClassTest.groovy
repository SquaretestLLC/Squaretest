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
    void testDoSomething() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
    }

    @Test
    void testDoSomething2() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething2()

        // Verify the results
    }

    @Test
    void testDoSomething1() {
        myClassUnderTest.doSomething1()
    }
}
