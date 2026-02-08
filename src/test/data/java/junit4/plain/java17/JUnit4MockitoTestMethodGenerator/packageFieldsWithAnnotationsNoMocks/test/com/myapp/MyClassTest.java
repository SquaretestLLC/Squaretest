package com.myapp;

import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.fooService = null;
        myClassUnderTest.barService = null;
        myClassUnderTest.defaultBarId = "defaultBarId";
    }

    @Test
    public void testGetFooAndBar11() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}