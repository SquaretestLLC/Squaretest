package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private AuthService mockAuthService;
    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockAuthService, mockFooService, mockMetricService);
    }

    @Test
    void testGetFoos1() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos1_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos1("userId", "criteria"));
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos1("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(new int[]{0});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos2_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos2("userId", "criteria"));
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos2("criteria")).thenReturn(new int[]{});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos2("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos3() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("criteria")).thenReturn(new double[]{0.0});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos3_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos3("userId", "criteria"));
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("criteria")).thenReturn(new double[]{});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos3("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos4() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos4("criteria")).thenReturn(new long[]{0L});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoos4_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos4("userId", "criteria"));
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos4("criteria")).thenReturn(new long[]{});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos4("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos5() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordItem1("id");
    }

    @Test
    void testGetFoos5_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos5("userId", "criteria"));
    }

    @Test
    void testGetFoos5_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos5("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos6() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordItem1("id");
        verify(mockMetricService).recordMultipleItems1("criteria");
    }

    @Test
    void testGetFoos6_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos6("userId", "criteria"));
    }

    @Test
    void testGetFoos6_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos6("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos7() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordItem1("id");
    }

    @Test
    void testGetFoos7_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos7("userId", "criteria"));
    }

    @Test
    void testGetFoos7_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos1("criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getFoos7("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFoos8() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("criteria")).thenReturn(new double[]{0.0});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos8("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordMin(0.0);
        verify(mockMetricService).recordMax(0.0);
        verify(mockMetricService).recordAverage(0.0);
        verify(mockMetricService).recordSum(0.0);
    }

    @Test
    void testGetFoos8_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos8("userId", "criteria"));
    }

    @Test
    void testGetFoos8_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("criteria")).thenReturn(new double[]{});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos8("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordSum(0.0);
    }

    @Test
    void testGetFoos9() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("criteria")).thenReturn(new double[]{0.0});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos9("userId", "criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
        verify(mockMetricService).recordMin(0.0);
        verify(mockMetricService).recordMax(0.0);
        verify(mockMetricService).recordAverage(0.0);
        verify(mockMetricService).recordSum(0.0);
    }

    @Test
    void testGetFoos9_AuthServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Collections.emptyList());

        // Run the test
        assertThrows(NotAuthorizedException.class, () -> myClassUnderTest.getFoos9("userId", "criteria"));
    }

    @Test
    void testGetFoos9_FooServiceReturnsNoItems() {
        // Setup
        when(mockAuthService.getAllowedIds1()).thenReturn(Arrays.asList("value"));
        when(mockFooService.getFoos3("criteria")).thenReturn(new double[]{});

        // Run the test
        final List<String> result = myClassUnderTest.getFoos9("userId", "criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockMetricService).recordSum(0.0);
    }
}
