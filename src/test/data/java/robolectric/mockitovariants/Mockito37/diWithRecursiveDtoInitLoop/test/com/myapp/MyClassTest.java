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
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testProcessFoo() {
        // Setup
        final Foo foo = new Foo(new InnerFoo(null));

        // Run the test
        myClassUnderTest.processFoo(foo);

        // Verify the results
    }
}
