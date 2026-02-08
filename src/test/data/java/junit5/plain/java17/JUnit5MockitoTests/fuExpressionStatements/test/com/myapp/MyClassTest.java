package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
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
    void testGetFoos1() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final String result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoos1_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoos1("id"));
    }

    @Test
    void testGetFoos2() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        final String result = myClassUnderTest.getFoos2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoos2_FooServiceGetStartingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoos2("id"));
    }

    @Test
    void testGetFoos2_FooServiceGetNextValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoos2("id"));
    }

    @Test
    void testValidateFoo1() {
        // Setup
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.of(0));

        // Run the test
        myClassUnderTest.validateFoo1("id");

        // Verify the results
    }

    @Test
    void testValidateFoo1_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getNextValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.validateFoo1("id"));
    }

    @Test
    void testValidateFoo2() throws Exception {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.of(0));
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.of(0));

        // Run the test
        myClassUnderTest.validateFoo2("id");

        // Verify the results
        verify(mockMetricService).recordAfterSupplier("id");
        verify(mockMetricService).recordStartOfElseBlock("id");
        verify(mockMetricService).recordEndOfElseBlock("id");
        verify(mockMetricService).recordEndOfMethod("id");
    }

    @Test
    void testValidateFoo2_FooServiceGetStartingValue2ReturnsAbsent() {
        // Setup
        when(mockFooService.getStartingValue2("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.validateFoo2("id"));
    }

    @Test
    void testValidateFoo2_FooServiceGetStartingValue4ReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.validateFoo2("id"));
        verify(mockMetricService).recordStartOfElseBlock("id");
    }

    @Test
    void testValidateFoo2_FooServiceGetStartingValue4ThrowsFooServiceException() throws Exception {
        // Setup
        when(mockFooService.getStartingValue4("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.validateFoo2("id"));
        verify(mockMetricService).recordStartOfElseBlock("id");
    }
}
