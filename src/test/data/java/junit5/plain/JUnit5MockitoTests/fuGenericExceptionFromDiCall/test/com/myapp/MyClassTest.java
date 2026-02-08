package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService<String, FooServiceException> mockFooService;
    @Mock
    private AltFooService<String, FooServiceException> mockAltFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass<String, FooServiceException> myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass<>(mockFooService, mockAltFooService, mockMetricService);
    }

    @Test
    void testGetFoo1() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceThrowsE() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(E.class);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_FooServiceThrowsE() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(E.class);

        // Run the test
        assertThrows(Exception.class, () -> myClassUnderTest.getFoo2("id"));
        verify(mockMetricService).recordException(eq("id"), any(Exception.class));
    }

    @Test
    void testGetFoo3() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_FooServiceThrowsE() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(E.class);

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordException(eq("id"), any(Exception.class));
    }

    @Test
    void testGetFoo4() throws Exception {
        // Setup
        when(mockAltFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4_AltFooServiceThrowsE() throws Exception {
        // Setup
        when(mockAltFooService.getFoo1("id")).thenThrow(E.class);

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }
}
