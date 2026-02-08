package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(null);
    }

    @Test
    public void testGetFoo11() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}