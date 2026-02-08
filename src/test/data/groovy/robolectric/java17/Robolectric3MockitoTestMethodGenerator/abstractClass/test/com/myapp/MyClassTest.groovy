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
    void testDoSomething1() {
        myClassUnderTest.doSomething()
    }

    @Test
    void testGetData1() {
        // Setup
        def expectedResult = ["value": "value"]

        // Run the test
        def result = myClassUnderTest.getData()

        // Verify the results
        assert expectedResult == result
    }
}