package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(null)
        myClassUnderTest.setBarService(null)
        myClassUnderTest.setDefaultBarId("defaultBarId")
    }

    @Test
    void testGetFooAndBar11() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}