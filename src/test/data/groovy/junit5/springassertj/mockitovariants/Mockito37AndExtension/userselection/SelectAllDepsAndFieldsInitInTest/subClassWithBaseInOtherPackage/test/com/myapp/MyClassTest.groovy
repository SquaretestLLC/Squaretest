package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

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
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData", "fourthData"))
                .thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems3_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData", "fourthData")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData")

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}
