package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(null);
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}
