package com.myapp;

import org.junit.jupiter.api.Test;

class MyClassTest {

    @Test
    void testGetFooAndBar11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, "defaultBarId");

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}