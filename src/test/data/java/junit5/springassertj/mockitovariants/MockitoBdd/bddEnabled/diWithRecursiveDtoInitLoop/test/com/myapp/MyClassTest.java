package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testProcessFoo() {
        // Setup
        final Foo foo = new Foo(new InnerFoo(null));

        // Run the test
        myClassUnderTest.processFoo(foo);

        // Verify the results
    }
}
