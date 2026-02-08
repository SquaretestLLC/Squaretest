package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockMetricService);
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo3() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo5() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo5_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo5_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo5("id"));
    }

    @Test
    void testGetFoo6() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo6("id"));
    }

    @Test
    void testGetFoo7() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricService).recordNullFoo("id");
    }

    @Test
    void testGetFoo7_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo7("id"));
        verify(mockMetricService).recordNullFoo("id");
    }

    @Test
    void testGetFoo77() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo77("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo77_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        assertThrows(NullPointerException.class, () -> myClassUnderTest.getFoo77("id"));
        verify(mockMetricService).recordNullFoo("id");
    }

    @Test
    void testGetFoo8() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo8_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo8("id"));
    }

    @Test
    void testGetFoo9() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo9("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo9_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.getFoo9("id"));
    }
}
