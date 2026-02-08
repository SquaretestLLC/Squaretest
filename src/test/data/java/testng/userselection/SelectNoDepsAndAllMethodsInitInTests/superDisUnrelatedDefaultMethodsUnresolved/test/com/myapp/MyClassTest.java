package com.myapp;

import com.myapp.bases.FooService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testPerformGetUpData() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo2() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertEquals("", myClassUnderTest.getUpperFoo("key"));
    }

    @Test
    public void testGetUpperFoo12() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertEquals("", myClassUnderTest.getUpperFoo1("key"));
    }
}
