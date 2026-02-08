package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
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
}
