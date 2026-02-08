package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
        // Configure FooService.getFoo(...).
        final Response spyResponse = spy(Response.ok("body").build());
        when(mockFooService.getFoo("fooId")).thenReturn(spyResponse);

        // Run the test
        final String result = myClassUnderTest.getFoo("fooId");

        // Verify the results
        assertEquals("result", result);
        verify(spyResponse).close();
    }

    @Test
    void testGetFoo_FooServiceReturnsError() {
        // Setup
        // Configure FooService.getFoo(...).
        final Response spyResponse = Response.serverError().build();
        when(mockFooService.getFoo("fooId")).thenReturn(spyResponse);

        // Run the test
        final String result = myClassUnderTest.getFoo("fooId");

        // Verify the results
        assertEquals("result", result);
        verify(spyResponse).close();
    }
}
