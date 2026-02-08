package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testGetFooAndBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.setFooService(null);
        myClassUnderTest.setBarService(null);
        myClassUnderTest.setDefaultBarId("defaultBarId");

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}
