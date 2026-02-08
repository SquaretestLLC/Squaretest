package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class MyClassTest {

    @Test
    void testGetFooById() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
