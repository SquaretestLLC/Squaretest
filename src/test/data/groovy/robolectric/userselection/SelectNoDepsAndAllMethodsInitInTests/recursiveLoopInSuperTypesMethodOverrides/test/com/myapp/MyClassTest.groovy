package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testDoSomething() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
    }

    @Test
    void testDoSomething2() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.doSomething2()

        // Verify the results
    }

    @Test
    void testDoSomething1() {
        def myClassUnderTest = new MyClass()
        myClassUnderTest.doSomething1()
    }
}
