package com.myapp

import org.junit.Before
import org.junit.Test

import java.util.HashMap
import java.util.Map

import static org.junit.Assert.assertEquals

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
        // Setup
        // Run the test
        myClassUnderTest.doSomething()

        // Verify the results
    }

    @Test
    void testGetData() {
        // Setup
        final Map<String, String> expectedResult = new HashMap<>()

        // Run the test
        final Map<String, String> result = myClassUnderTest.getData()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
