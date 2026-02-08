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
    void testDoSomething11() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
    }

    @Test
    void testDoSomething21() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0)

        // Verify the results
    }

    @Test
    void testDoSomething3() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0.0d)

        // Verify the results
    }

    @Test
    void testDoSomethingElse1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingElse()

        // Verify the results
    }
}