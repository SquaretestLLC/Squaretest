package com.myapp

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
    void testGetItems1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetItems2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetItems3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria", "otherData")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems3_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria", "otherData")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData")

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetItems4() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems4_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData")

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}
