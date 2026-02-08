package com.myapp;

import org.testng.annotations.Test;

public class MyClassTest {

    @Test
    public void testDoSomething() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
    }

    @Test
    public void testDoSomething2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomething2();

        // Verify the results
    }

    @Test
    public void testDoSomething1() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.doSomething1();
    }
}
