package com.myapp;

import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomething() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
    }

    @Test
    public void testDoSomething2() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething2();

        // Verify the results
    }

    @Test
    public void testDoSomething1() {
        myClassUnderTest.doSomething1();
    }
}
