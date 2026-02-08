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
    void testGreetingWithPath() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPath(0L, model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithPrimitivePath() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPrimitivePath(0L, model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithDoublePath() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithDoublePath(0.0, model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithFloatPath() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithFloatPath(0.0f, model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting("nameParam", model);

        // Verify the results
        assertEquals("greeting", result);
    }
}
