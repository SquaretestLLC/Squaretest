package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
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
