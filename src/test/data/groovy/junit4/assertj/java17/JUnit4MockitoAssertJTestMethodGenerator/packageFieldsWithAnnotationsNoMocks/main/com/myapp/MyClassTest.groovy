package com.myapp

import org.junit.Before
import org.junit.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.fooService = null
        myClassUnderTest.barService = null
        myClassUnderTest.defaultBarId = "defaultBarId"
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
