package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
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