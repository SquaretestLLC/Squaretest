package com.myapp

import com.squaretest.supertypes.base.FooService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FooService())
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testPerformGetUpData1() {
        assertEquals("result", myClassUnderTest.performGetUpData1("data"))
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetUpperFoo1() {
        assertEquals("result", myClassUnderTest.getUpperFoo1("key"))
    }
}
