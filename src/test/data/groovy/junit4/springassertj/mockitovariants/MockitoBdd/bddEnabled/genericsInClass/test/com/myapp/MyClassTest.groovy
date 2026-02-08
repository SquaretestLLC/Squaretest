package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass<String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testGetSomething() {
        assertThat(myClassUnderTest.getSomething()).isNull()
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
        assertThat(myClassUnderTest.countSomethings()).isEqualTo(0)
    }
}
