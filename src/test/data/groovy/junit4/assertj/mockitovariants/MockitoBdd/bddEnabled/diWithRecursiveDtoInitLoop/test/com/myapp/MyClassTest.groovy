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
