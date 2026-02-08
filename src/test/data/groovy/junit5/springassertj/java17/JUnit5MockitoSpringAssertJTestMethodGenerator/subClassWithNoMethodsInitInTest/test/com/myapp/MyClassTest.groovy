package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testGetItems11() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetItems21() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}