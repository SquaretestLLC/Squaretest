package com.myapp;

import org.junit.Test;

public class MyClassTest {

    @Test
    public void testGetFoo11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}