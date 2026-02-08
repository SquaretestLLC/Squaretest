package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingWithListener1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithListener(0);

        // Verify the results
    }

    @Test
    public void testDoSomethingWithCallback1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithCallback(false);

        // Verify the results
    }
}