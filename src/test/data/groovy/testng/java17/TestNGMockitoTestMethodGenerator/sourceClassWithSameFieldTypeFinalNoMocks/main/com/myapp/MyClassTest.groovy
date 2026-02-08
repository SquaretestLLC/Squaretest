package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
