package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
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
        assertThat(result).isEqualTo(expectedResult)
    }
}
