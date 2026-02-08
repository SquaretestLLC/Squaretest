package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    private MyClass<String> myClassUnderTest

    @BeforeMethod
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
