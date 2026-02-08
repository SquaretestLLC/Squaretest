package com.myapp;

import com.myapp.bases.FooService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
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
    public void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }
}
