package com.myapp

import org.junit.jupiter.api.Test

class MyClassTest {

    @Test
    void testGetFooAndBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(null)
        myClassUnderTest.setBarService(null)
        myClassUnderTest.setDefaultBarId("defaultBarId")

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
