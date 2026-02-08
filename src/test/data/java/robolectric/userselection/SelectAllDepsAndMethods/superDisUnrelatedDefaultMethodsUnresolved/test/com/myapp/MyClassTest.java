package com.myapp;

import com.myapp.bases.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testPerformGetUpData() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData1() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData1_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetUpperFoo1() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo1_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo11() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo11_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo2() {
        assertEquals("", myClassUnderTest.getUpperFoo("key"));
    }

    @Test
    public void testGetUpperFoo12() {
        assertEquals("", myClassUnderTest.getUpperFoo1("key"));
    }
}
