package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        when(mockFooService.getFoos1("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos2_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(null);

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos3() {
        // Setup
        when(mockFooService.getFoos3("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordFoosFound("id");
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos4() {
        // Setup
        when(mockFooService.getFoos4("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordFoosFound("id");
    }

    @Test
    void testGetFoos4_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoos4("id")).thenReturn(null);

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos4("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos5() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos5_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(null);

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id");

        // Verify the results
        assertNull(result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos6() {
        // Setup
        when(mockFooService.getFoos5("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos5("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos7() {
        // Setup
        when(mockFooService.getFoos5("id")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("id");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordNoFoos("id");
    }

    @Test
    void testGetFoos7_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos5("id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordNoFoos("id");
    }
}
