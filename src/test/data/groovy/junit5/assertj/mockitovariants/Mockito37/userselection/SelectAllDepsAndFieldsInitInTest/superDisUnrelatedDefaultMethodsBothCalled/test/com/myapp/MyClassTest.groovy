package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result")
    }
}
