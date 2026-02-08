package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass("someStringDep") {
            @Override
            public void doSomethingImportant() {

            }

            @Override
            protected Map<String, String> getSomethingSpecial() {
                return null
            }
        }
    }

    @Test
    void testDoSomething() {
        myClassUnderTest.doSomething()
    }

    @Test
    void testGetData() {
        // Setup
        def expectedResult = ["value": "value"]

        // Run the test
        def result = myClassUnderTest.getData()

        // Verify the results
        assert expectedResult == result
    }
}
