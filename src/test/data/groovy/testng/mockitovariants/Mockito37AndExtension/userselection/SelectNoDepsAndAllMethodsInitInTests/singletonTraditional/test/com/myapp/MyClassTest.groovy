package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        def result = MyClass.getInstance()

        // Verify the results
    }

    @Test
    void testCreateNewConnection() {
        def myClassUnderTest = MyClass.getInstance()
        assertNull(myClassUnderTest.createNewConnection())
    }
}
