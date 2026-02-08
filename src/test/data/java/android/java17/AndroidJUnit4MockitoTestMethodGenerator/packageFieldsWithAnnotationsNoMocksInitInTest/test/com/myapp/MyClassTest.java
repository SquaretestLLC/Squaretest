package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Test
    public void testGetFooAndBar11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.fooService = null;
        myClassUnderTest.barService = null;
        myClassUnderTest.defaultBarId = "defaultBarId";

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}