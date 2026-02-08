package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testPerformGetUpData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("data")).thenReturn("result");
        when(mockFooService.getOtherData("data")).thenReturn("result");
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("data")).thenThrow(IOException.class);
        when(mockFooService.getOtherData("data")).thenReturn("result");
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("data")).thenReturn("result");
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class);
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
    }
}
