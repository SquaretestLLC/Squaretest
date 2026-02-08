package com.myapp

import org.junit.Before
import org.junit.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(null)
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}
