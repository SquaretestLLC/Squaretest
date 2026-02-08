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
        mockFooListener.setName("name");
        mockFooListener.setId("id");

        // Run the test
        myClassUnderTest.doSomething(mockFooListener);

        // Verify the results
    }
}
