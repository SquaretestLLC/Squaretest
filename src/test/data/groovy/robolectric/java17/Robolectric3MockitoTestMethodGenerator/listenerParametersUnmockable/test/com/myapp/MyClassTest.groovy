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