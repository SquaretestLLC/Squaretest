package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
