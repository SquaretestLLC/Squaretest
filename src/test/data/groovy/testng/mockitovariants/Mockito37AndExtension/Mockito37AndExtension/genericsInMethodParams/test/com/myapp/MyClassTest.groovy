package com.myapp

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
