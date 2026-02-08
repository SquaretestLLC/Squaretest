package com.myapp;

import com.myapp.bases.FooService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

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
    public void testPerformGetUpData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("data")).thenReturn("result");
        when(mockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("data")).thenThrow(IOException.class);
        when(mockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("data")).thenReturn("result");
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
    }
}
