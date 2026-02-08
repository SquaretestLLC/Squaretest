package com.myapp

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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
