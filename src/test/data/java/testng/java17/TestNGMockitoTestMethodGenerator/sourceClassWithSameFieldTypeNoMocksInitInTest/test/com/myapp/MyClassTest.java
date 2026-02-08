package com.myapp;

import org.testng.annotations.Test;

public class MyClassTest {

    @Test
    public void testGetFooById1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}