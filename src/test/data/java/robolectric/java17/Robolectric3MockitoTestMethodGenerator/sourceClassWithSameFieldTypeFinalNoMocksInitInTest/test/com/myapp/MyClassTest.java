package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testGetFooById1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null));

        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}