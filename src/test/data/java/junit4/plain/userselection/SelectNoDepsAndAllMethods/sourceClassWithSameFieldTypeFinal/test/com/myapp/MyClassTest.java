package com.myapp;

import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null));
    }

    @Test
    public void testGetFooById() {
        // Setup
        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}
