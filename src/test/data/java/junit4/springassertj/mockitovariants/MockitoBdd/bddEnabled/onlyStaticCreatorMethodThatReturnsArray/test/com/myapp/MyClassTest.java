package com.myapp;

import com.myapp.foos.MyFoo;
import org.junit.Before;
import org.junit.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomething() {
        // Setup
        final MyFoo myFoo = null;

        // Run the test
        myClassUnderTest.doSomething(myFoo);

        // Verify the results
    }
}
