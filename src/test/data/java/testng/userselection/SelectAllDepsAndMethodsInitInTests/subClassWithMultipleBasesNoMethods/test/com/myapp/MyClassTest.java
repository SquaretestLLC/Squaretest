package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetItems1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetItems2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetItems3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName", "criteria", "otherData")).thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems3_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName", "criteria", "otherData")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetItems4() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData"))
                .thenReturn(Arrays.asList("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testGetItems4_FooServiceReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria", "otherData", "thirdData");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
