package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
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

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetItems1() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetItems2() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}
