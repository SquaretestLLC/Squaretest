package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetFoos1() {
        // Setup
        when(mockFooService.getOtherValue1("id")).thenReturn("id");

        // Run the test
        final String result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals("id", result);
        verify(mockMetricService).recordOtherValueIsString("id");
        verify(mockMetricService).recordOtherValueIsNotString("id");
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockFooService.getOtherValue1("id")).thenReturn("id");

        // Run the test
        final String result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals("id", result);
        verify(mockMetricService).recordOtherValueIsString("id");
        verify(mockMetricService).recordOtherValueIsNotString("id");
    }
}
