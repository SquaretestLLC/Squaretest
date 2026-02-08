package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.HashMap
import java.util.Map

import static org.testng.Assert.assertEquals

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
