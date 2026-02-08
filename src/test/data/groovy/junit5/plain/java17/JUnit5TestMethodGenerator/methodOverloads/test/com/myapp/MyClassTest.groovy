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