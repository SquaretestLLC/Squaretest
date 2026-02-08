package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.mockito.Mock

import java.util.Arrays
import java.util.Collections
import java.util.List

import static org.testng.Assert.assertEquals
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private FooService mockFooService

    @BeforeMethod
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetItems1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn(Arrays.asList("value"))

        // Run the test
        final List<String> result = myClassUnderTest.getItems()

        // Verify the results
        assertEquals(Arrays.asList("value"), result)
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn(Collections.emptyList())

        // Run the test
        final List<String> result = myClassUnderTest.getItems()

        // Verify the results
        assertEquals(Collections.emptyList(), result)
    }

    @Test
    void testGetItems2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Arrays.asList("value"))

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertEquals(Arrays.asList("value"), result)
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Collections.emptyList())

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertEquals(Collections.emptyList(), result)
    }
}
