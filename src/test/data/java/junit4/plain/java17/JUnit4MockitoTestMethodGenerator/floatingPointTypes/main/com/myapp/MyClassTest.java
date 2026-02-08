package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testAddDoubles() {
        // Setup
        final double expectedResult = 0.0;

        // Run the test
        final double result = myClassUnderTest.addDoubles(0.0, 0.0);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testAddBigDoubles() {
        // Setup
        final Double expectedResult = 0.0;

        // Run the test
        final Double result = myClassUnderTest.addBigDoubles(0.0, 0.0);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testAddFloats() {
        // Setup
        final float expectedResult = 0.0f;

        // Run the test
        final float result = myClassUnderTest.addFloats(0.0f, 0.0f);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testAddBigFloats() {
        // Setup
        final Float expectedResult = 0.0f;

        // Run the test
        final Float result = myClassUnderTest.addBigFloats(0.0f, 0.0f);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testStaticAddDoubles() {
        assertEquals(0.0, MyClass.staticAddDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testStaticAddBigDoubles() {
        assertEquals(0.0, MyClass.staticAddBigDoubles(0.0, 0.0), 0.0001);
    }

    @Test
    void testStaticAddFloats() {
        assertEquals(0.0f, MyClass.staticAddFloats(0.0f, 0.0f), 0.0001);
    }

    @Test
    void testStaticAddBigFloats() {
        assertEquals(0.0f, MyClass.staticAddBigFloats(0.0f, 0.0f), 0.0001);
    }
}
