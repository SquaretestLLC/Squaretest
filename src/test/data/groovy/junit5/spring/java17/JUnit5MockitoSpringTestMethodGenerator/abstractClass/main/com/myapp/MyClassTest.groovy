package com.myapp

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.util.HashMap
import java.util.Map

import static org.junit.jupiter.api.Assertions.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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
