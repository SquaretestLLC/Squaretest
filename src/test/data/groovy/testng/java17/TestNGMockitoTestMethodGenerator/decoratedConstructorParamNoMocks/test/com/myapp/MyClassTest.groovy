package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(null)
    }

    @Test
    void testGetFoo11() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}