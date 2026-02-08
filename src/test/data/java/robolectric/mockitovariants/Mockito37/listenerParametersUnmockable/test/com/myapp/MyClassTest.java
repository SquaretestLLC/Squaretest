package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingWithListener() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithListener(0);

        // Verify the results
    }

    @Test
    public void testDoSomethingWithCallback() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithCallback(false);

        // Verify the results
    }
}
