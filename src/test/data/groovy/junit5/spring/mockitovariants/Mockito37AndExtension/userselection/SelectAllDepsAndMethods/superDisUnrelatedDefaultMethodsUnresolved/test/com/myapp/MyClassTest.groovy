package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.junit.jupiter.api.Assertions.assertNull
import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetUpperFoo1() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo1_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo11() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getUpperFoo1("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo11_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getUpperFoo1("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo2() {
        assert "" == myClassUnderTest.getUpperFoo("key")
    }

    @Test
    void testGetUpperFoo12() {
        assert "" == myClassUnderTest.getUpperFoo1("key")
    }
}
