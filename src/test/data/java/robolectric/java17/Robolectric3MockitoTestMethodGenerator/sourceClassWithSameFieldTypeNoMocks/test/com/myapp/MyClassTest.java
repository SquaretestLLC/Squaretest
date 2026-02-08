package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null));
    }

    @Test
    public void testGetFooById1() {
        // Setup
        // Run the test
        final Foo result = myClassUnderTest.getFooById("id");

        // Verify the results
    }
}