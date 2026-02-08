package com.myapp;

import com.squaretest.supertypes.base.FooService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService theMockFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(theMockFooService);
    }

    @Test
    void testPerformGetUpData() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");
        when(theMockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenThrow(IOException.class);
        when(theMockFooService.getOtherData("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getData("data")).thenReturn("result");
        when(theMockFooService.getOtherData("data")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData1() {
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
    }
}
