package com.myapp;

import com.myapp.foos.MyFoo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
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
