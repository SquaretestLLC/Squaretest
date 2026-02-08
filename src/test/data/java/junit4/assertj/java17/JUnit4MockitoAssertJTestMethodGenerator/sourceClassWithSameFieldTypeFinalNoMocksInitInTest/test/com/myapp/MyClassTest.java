package com.myapp;

import org.junit.Test;

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