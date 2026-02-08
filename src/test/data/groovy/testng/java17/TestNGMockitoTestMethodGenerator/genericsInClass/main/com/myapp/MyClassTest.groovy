package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertEquals

class MyClassTest {

    private MyClass<T> myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testGetSomething() {
        // Setup
        final T expectedResult = null

        // Run the test
        final T result = myClassUnderTest.getSomething()

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    void testPutSomething() {
        // Setup
        final T thingToPut = null

        // Run the test
        myClassUnderTest.putSomething(thingToPut)

        // Verify the results
    }

    @Test
    void testCountSomethings() {
        // Setup
        final int expectedResult = 0

        // Run the test
        final int result = myClassUnderTest.countSomethings()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
