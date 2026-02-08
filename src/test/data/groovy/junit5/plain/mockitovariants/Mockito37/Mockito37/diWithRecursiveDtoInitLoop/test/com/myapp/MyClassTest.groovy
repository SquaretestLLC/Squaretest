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
