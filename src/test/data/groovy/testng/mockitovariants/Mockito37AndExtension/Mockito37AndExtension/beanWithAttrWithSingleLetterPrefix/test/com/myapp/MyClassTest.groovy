package com.myapp

import com.myapp.foos.ObjectSummary
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
    void testDoSomething() {
        // Setup
        def objectSummary = new ObjectSummary()
        objectSummary.setETag("eTag")

        // Run the test
        myClassUnderTest.doSomething(objectSummary)

        // Verify the results
    }
}
