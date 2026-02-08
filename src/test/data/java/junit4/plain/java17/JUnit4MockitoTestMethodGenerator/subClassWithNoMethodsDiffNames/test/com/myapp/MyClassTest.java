package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService theMockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(theMockFooService);
    }

    @Test
    public void testGetItems11() {
        // Setup
        when(theMockFooService.getItems("bucketName")).thenReturn(List.of("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    public void testGetItems1_FooServiceReturnsNoItems1() {
        // Setup
        when(theMockFooService.getItems("bucketName")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetItems21() {
        // Setup
        when(theMockFooService.getItems("bucketName", "criteria")).thenReturn(List.of("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(List.of("value"), result);
    }

    @Test
    public void testGetItems2_FooServiceReturnsNoItems1() {
        // Setup
        when(theMockFooService.getItems("bucketName", "criteria")).thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = myClassUnderTest.getItems("criteria");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}