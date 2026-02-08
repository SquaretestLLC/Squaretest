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

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetUpperFoo1() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo1_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo11() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getUpperFoo1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo11_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getUpperFoo1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo2() {
        assertThat(myClassUnderTest.getUpperFoo("key")).isEqualTo("")
    }

    @Test
    void testGetUpperFoo12() {
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("")
    }
}
