package com.myapp

import org.junit.Before
import org.junit.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null))
    }

    @Test
    void testGetFooById() {
        // Setup
        // Run the test
        final Foo result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
