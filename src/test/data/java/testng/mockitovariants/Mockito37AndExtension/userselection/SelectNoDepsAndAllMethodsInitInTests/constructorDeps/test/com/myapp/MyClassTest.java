package com.myapp;

import org.testng.annotations.Test;

public class MyClassTest {

    @Test
    public void testGetFooAndBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, "defaultBarId");

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}
