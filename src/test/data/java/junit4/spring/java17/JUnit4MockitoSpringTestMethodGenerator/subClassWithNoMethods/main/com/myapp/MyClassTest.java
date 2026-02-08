package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetItems1() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetItems2() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
