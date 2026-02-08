package com.myapp;

import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.setFooService(null);
        myClassUnderTest.setBarService(null);
        myClassUnderTest.setDefaultBarId("defaultBarId");
    }

    @Test
    public void testGetFooAndBar11() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}