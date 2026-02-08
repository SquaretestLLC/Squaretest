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
    void testGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testPostGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.postGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testPutGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.putGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testDeleteGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.deleteGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testPatchGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.patchGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testOptionsGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.optionsGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testHeadGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.headGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testTraceGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.traceGreeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting1("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testPostGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.postGreeting1("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testPutGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.putGreeting1("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testDeleteGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.deleteGreeting1("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testPatchGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.patchGreeting1("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }
}
