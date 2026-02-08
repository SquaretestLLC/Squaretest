package com.myapp

import com.squaretest.supertypes.base.FooService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.io.IOException

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private FooService mockFooService

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testPerformGetUpData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
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
        final MyClass myClassUnderTest = new MyClass(mockFooService)
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
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testPerformGetUpData1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        assertEquals("result", myClassUnderTest.performGetUpData1("data"))
    }

    @Test
    void testGetFoo() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetFoo_FooServiceThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        final String result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetFoo2() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetFoo2_FooServiceThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        final String result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetUpperFoo() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetUpperFoo_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetUpperFoo_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetUpperFoo1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService)
        assertEquals("result", myClassUnderTest.getUpperFoo1("key"))
    }
}
