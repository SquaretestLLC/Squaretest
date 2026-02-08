package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(null)
        myClassUnderTest.setBarService(null)
        myClassUnderTest.setDefaultBarId("defaultBarId")
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
