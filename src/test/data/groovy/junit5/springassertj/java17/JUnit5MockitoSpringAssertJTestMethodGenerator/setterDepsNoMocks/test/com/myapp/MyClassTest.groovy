package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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