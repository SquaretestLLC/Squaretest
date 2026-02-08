package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomething11() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething();

        // Verify the results
    }

    @Test
    public void testDoSomething21() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0);

        // Verify the results
    }

    @Test
    public void testDoSomething3() {
        // Setup
        // Run the test
        myClassUnderTest.doSomething(0.0);

        // Verify the results
    }

    @Test
    public void testDoSomethingElse1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingElse();

        // Verify the results
    }
}