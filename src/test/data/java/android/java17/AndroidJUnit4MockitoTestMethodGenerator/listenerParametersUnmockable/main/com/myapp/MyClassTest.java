package com.myapp;

import org.junit.Before;
import org.junit.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomethingWithListener() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithListener(0);

        // Verify the results
    }

    @Test
    void testDoSomethingWithCallback() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithCallback(false);

        // Verify the results
    }
}
