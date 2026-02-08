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
    private FooService<String> mockFooService;
    @Mock
    private ExceptionService<Throwable> mockExceptionService;
    @Mock
    private MetricService mockMetricService;

    private MyClass<String, Throwable> myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass<>(mockFooService, mockExceptionService, mockMetricService);
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockExceptionService.createException1("id")).thenReturn(new Exception("message"));

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo1() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testSafeGetFoo1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);
        when(mockExceptionService.createException1("id")).thenReturn(new Exception("message"));

        // Run the test
        final String result = myClassUnderTest.safeGetFoo1("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordException(eq("id"), any(Throwable.class));
    }

    @Test
    void testStoreFoo1_ThrowsFooAlreadyExistsException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo1("id", "theFoo"));
    }

    @Test
    void testStoreFoo1_FooServiceGetFoo1ReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(null);

        // Run the test
        myClassUnderTest.storeFoo1("id", "theFoo");

        // Verify the results
        verify(mockFooService).storeFoo1("theFoo");
    }

    @Test
    void testThrowThEx1() {
        assertThrows(E.class, () -> myClassUnderTest.throwThEx1("id"));
    }

    @Test
    void testDoSomething1() {
        assertThrows(E.class, () -> myClassUnderTest.doSomething1("id"));
    }

    @Test
    void testDoSomething2() {
        // Setup
        when(mockExceptionService.createException1("id")).thenReturn(new Exception("message"));

        // Run the test
        myClassUnderTest.doSomething2("id");

        // Verify the results
        verify(mockMetricService).recordException(eq("id"), any(Throwable.class));
    }
}
