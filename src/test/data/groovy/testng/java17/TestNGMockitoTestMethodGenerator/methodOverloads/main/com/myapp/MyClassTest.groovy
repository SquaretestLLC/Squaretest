package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

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
    void testDoSomething1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0)

        // Verify the results
    }

    @Test
    void testDoSomething2() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0.0)

        // Verify the results
    }

    @Test
    void testDoSomethingElse() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingElse()

        // Verify the results
    }
}
