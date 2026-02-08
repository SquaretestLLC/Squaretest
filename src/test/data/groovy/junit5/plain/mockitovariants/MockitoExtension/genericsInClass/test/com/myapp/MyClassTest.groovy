package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertNull

@CompileStatic
class MyClassTest {

    private MyClass<String> myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testGetSomething() {
        assertNull(myClassUnderTest.getSomething())
    }

    @Test
    void testPutSomething() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut")

        // Verify the results
    }

    @Test
    void testCountSomethings() {
        assert 0 == myClassUnderTest.countSomethings()
    }
}
