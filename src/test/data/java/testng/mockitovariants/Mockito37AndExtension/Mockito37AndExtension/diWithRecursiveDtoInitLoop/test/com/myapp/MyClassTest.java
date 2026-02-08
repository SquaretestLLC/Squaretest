package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
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
