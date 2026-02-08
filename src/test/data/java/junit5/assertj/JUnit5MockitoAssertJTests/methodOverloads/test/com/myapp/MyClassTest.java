package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomething1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
    }

    @Test
    void testDoSomething2() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0);

        // Verify the results
    }

    @Test
    void testDoSomething3() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0.0);

        // Verify the results
    }

    @Test
    void testDoSomethingElse() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingElse();

        // Verify the results
    }
}
