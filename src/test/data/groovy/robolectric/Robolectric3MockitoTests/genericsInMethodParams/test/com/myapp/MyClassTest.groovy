package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingWithList() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingWithList(["value"])

        // Verify the results
    }

    @Test
    void testDoSomethingWithMultimap() {
        // Setup
        def theMap = ["value": ["value"]]

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(theMap)

        // Verify the results
    }

    @Test
    void testStaticDoSomethingWithList() {
        assert "result" == MyClass.staticDoSomethingWithList(["value"])
    }
}
