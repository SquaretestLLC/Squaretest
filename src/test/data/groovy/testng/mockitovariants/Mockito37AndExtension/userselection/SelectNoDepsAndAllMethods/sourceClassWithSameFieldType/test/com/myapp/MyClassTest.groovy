package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null))
    }

    @Test
    void testGetFooById() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
