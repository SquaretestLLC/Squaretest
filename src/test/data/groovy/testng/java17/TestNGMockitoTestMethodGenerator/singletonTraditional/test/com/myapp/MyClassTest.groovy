package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = MyClass.getInstance()
    }

    @Test
    void testGetInstance1() {
        // Setup
        // Run the test
        def result = MyClass.getInstance()

        // Verify the results
    }

    @Test
    void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}