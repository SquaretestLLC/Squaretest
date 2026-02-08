package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
