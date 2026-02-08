package com.myapp;

import org.testng.annotations.Test;

public class MyClassTest {

    @Test
    public void testGetFoo1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}
