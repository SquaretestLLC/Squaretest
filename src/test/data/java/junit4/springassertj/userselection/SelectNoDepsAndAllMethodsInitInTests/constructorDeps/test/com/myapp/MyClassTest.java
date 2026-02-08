package com.myapp;

import org.junit.Test;

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
