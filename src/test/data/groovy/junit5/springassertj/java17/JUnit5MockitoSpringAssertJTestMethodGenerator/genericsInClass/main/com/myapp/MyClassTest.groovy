package com.myapp

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class MyClassTest {

    private MyClass<T> myClassUnderTest

    @BeforeEach
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
