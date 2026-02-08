package com.myapp

import org.testng.annotations.Test

class MyClassTest {

    @Test
    void testGetFooById() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null))

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
