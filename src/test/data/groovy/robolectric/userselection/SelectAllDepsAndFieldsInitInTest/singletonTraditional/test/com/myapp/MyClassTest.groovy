package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertNull

@CompileStatic
@RunWith(RobolectricTestRunner.class)
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
