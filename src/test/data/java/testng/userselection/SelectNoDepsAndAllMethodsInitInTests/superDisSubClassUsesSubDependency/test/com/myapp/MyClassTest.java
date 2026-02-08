package com.myapp;

import com.myapp.bases.SubFooService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new SubFooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new SubFooService());

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }
}
