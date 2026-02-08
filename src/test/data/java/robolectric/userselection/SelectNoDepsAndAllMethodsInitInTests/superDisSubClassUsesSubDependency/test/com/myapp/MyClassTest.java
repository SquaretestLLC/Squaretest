package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
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
