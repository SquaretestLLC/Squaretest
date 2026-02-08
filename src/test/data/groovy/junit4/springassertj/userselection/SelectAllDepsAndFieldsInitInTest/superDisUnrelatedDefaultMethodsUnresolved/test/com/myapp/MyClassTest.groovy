package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    @Test
    void testPerformGetUpData() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData_FooServiceThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1_FooServiceThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
