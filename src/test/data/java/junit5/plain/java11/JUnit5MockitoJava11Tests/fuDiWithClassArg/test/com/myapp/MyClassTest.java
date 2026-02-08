package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockFooService.getClassToUse("fooInfo")).thenReturn(Object.class);
        when(mockFooService.doSomethingWithClass(Object.class, "fooInfo")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("fooInfo");

        // Verify the results
        assertEquals("result", result);
    }
}
