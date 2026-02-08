package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNull

@CompileStatic
class MyClassTest {

    private MyClass<String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testGetSomething1() {
        assertNull(myClassUnderTest.getSomething())
    }

    @Test
    void testPutSomething1() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut")

        // Verify the results
    }

    @Test
    void testCountSomethings1() {
        assert 0 == myClassUnderTest.countSomethings()
    }
}