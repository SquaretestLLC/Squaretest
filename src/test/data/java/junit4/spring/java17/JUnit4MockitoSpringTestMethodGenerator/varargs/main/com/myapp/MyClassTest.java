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
    void testDoSomethingCool() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings");

        // Verify the results
    }

    @Test
    void testDoSomethingCoolWithChars() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCoolWithChars('a');

        // Verify the results
    }

    @Test
    void testDoSomethingCoolWithTables() {
        // Setup
        final int[][] theTables = new int[][]{};

        // Run the test
        myClassUnderTest.doSomethingCoolWithTables(theTables);

        // Verify the results
    }

    @Test
    void testDoSomethingInC() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = myClassUnderTest.doSomethingInC();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
