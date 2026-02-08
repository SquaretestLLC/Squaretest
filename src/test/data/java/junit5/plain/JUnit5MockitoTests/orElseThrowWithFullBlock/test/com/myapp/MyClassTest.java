package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData1_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData1("id"));
    }

    @Test
    void testGetFooData2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooData1("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooData1("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFooData2("id"));
        verify(mockMetricService).recordOrderNotFound("id");
    }
}
