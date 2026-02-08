package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomethingCool() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool();

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
