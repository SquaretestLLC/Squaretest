package com.myapp;

import com.squaretest.supertypes.base.FooService;
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
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
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
    void testGetFoo2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetUpperFoo1() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertEquals("result", myClassUnderTest.getUpperFoo1("key"));
    }
}
