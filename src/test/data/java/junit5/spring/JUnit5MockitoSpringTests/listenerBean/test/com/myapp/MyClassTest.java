package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomething() {
        // Setup
        final FooListener mockFooListener = mock(FooListener.class);

        // Run the test
        myClassUnderTest.doSomething(mockFooListener);

        // Verify the results
    }
}
