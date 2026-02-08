package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo11() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo2() {
        assertEquals("", myClassUnderTest.getUpperFoo("key"));
    }

    @Test
    void testGetUpperFoo12() {
        assertEquals("", myClassUnderTest.getUpperFoo1("key"));
    }
}
