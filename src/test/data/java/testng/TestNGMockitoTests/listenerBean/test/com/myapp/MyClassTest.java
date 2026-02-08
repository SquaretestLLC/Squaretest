package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomething() {
        // Setup
        final FooListener mockFooListener = mock(FooListener.class);

        // Run the test
        myClassUnderTest.doSomething(mockFooListener);

        // Verify the results
    }
}
