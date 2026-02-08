package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Test
    public void testDoSomething() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
    }

    @Test
    public void testDoSomething2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomething2();

        // Verify the results
    }

    @Test
    public void testDoSomething1() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.doSomething1();
    }
}
