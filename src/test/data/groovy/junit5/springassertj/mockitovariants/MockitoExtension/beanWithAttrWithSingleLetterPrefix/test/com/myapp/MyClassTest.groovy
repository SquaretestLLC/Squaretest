package com.myapp

import com.myapp.foos.ObjectSummary
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
    void testDoSomething() {
        // Setup
        def objectSummary = new ObjectSummary()
        objectSummary.setETag("eTag")

        // Run the test
        myClassUnderTest.doSomething(objectSummary)

        // Verify the results
    }
}
