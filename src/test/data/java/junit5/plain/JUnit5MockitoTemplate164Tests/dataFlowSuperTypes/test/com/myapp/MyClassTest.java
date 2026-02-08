package com.myapp;

import com.myapp.bases1.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testPerformGetUpData() throws Exception {
        // Setup
        when(mockFooService.getData("myClassPerformGetUpData1Param")).thenReturn("result");
        when(mockFooService.getOtherData("myClassPerformGetUpData1Param")).thenReturn("result");
        when(mockFooService.activateBar("myClassPerformGetUpData1Param")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("myClassPerformGetUpData1Param");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("myClassPerformGetUpData1Param")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData("myClassPerformGetUpData1Param");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("myClassPerformGetUpData1Param")).thenReturn("result");
        when(mockFooService.getOtherData("myClassPerformGetUpData1Param")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.performGetUpData("myClassPerformGetUpData1Param");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testPerformGetUpData1() {
        assertEquals("result", myClassUnderTest.performGetUpData1("myClassPerformGetUpData1Param"));
    }
}
