package com.myapp;

import org.junit.jupiter.api.Test;

class MyClassTest {

    @Test
    void testGetFooAndBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.fooService = null;
        myClassUnderTest.barService = null;
        myClassUnderTest.defaultBarId = "defaultBarId";

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}
