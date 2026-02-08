package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(null);
    }

    @Test
    void testGetFoo11() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
    }
}