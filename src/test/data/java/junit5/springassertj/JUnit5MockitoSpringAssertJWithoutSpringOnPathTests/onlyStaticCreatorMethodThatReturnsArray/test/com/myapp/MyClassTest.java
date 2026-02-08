package com.myapp;

import com.myapp.foos.MyFoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomething() {
        // Setup
        final MyFoo myFoo = null;

        // Run the test
        myClassUnderTest.doSomething(myFoo);

        // Verify the results
    }
}
