package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testProcessFoo() {
        // Setup
        def foo = new Foo(new InnerFoo(null))

        // Run the test
        myClassUnderTest.processFoo(foo)

        // Verify the results
    }
}
