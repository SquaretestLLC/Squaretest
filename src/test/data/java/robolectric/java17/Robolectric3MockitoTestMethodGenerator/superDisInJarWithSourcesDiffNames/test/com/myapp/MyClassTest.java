package com.myapp;

import com.squaretest.supertypes.base.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService theMockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(theMockFooService);
    }

    @Test
    public void testPerformGetUpData2() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");
        when(theMockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData_FooServiceGetDataThrowsIOException1() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenThrow(IOException.class);
        when(theMockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException1() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");
        when(theMockFooService.getOtherData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData11() {
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
    }

    @Test
    public void testGetFoo() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo2() throws Exception {
        // Setup
        when(theMockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo2_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getOtherData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");
        when(theMockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");
        when(theMockFooService.getOtherData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUpperFoo1() {
        assertEquals("result", myClassUnderTest.getUpperFoo1("key"));
    }
}