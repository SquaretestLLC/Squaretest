package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(null)
    }

    @Test
    void testGetItems1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems3() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems4() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData")

        // Verify the results
        assert ["value"] == result
    }
}
