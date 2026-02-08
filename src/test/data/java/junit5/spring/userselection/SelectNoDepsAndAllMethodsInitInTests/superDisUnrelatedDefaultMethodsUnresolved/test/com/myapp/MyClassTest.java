package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testPerformGetUpData() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo2() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertEquals("", myClassUnderTest.getUpperFoo("key"));
    }

    @Test
    void testGetUpperFoo12() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertEquals("", myClassUnderTest.getUpperFoo1("key"));
    }
}
