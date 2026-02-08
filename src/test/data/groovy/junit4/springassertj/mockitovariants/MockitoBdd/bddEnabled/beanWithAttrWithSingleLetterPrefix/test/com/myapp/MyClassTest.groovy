package com.myapp

import com.myapp.foos.ObjectSummary
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
    void testDoSomething() {
        // Setup
        def objectSummary = new ObjectSummary()
        objectSummary.setETag("eTag")

        // Run the test
        myClassUnderTest.doSomething(objectSummary)

        // Verify the results
    }
}
