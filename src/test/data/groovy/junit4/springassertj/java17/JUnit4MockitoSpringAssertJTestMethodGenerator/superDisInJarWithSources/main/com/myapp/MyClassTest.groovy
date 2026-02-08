package com.myapp

import com.squaretest.supertypes.base.FooService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import java.io.IOException

import static org.junit.Assert.assertEquals
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testPerformGetUpData() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testPerformGetUpData1() {
        assertEquals("result", myClassUnderTest.performGetUpData1("data"))
    }
}
