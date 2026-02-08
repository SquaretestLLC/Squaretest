package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, new HashMap<>());
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockFooService.getFoo1("fooId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1("fooId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("fooId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo1("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOtherFoo1() {
        // Setup
        when(mockFooService.getFoo1("fooId")).thenReturn("result");
        when(mockFooService.getOtherThing1("fooId")).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.getOtherFoo1("fooId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOtherFoo1_FooServiceGetFoo1ReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("fooId")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getOtherFoo1("fooId");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetOtherFoo1_FooServiceGetOtherThing1ReturnsAbsent() {
        // Setup
        when(mockFooService.getFoo1("fooId")).thenReturn("result");
        when(mockFooService.getOtherThing1("fooId")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.getOtherFoo1("fooId");

        // Verify the results
        assertNull(result);
    }
}
