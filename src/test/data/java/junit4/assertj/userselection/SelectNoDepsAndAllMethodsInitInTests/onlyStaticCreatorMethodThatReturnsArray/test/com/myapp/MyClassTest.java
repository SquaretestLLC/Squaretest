package com.myapp;

import com.myapp.foos.MyFoo;
import org.junit.Test;

public class MyClassTest {

    @Test
    public void testDoSomething() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final MyFoo myFoo = null;

        // Run the test
        myClassUnderTest.doSomething(myFoo);

        // Verify the results
    }
}
