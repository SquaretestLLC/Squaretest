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

    private MyClass theClass;

    @BeforeEach
    void setUp() {
        initMocks(this);
        theClass = new MyClass(mockFooService);
    }

    @Test
    void testGetFooData1() {
        // Setup
        when(mockFooService.getFooData("id")).thenReturn("result");

        // Run the test
        final String result = theClass.getFooData("id");

        // Verify the results
        assertEquals("result", result);
    }
}