package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
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
    private FooService theMockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(theMockFooService)
    }

    @Test
    void testGetItems11() {
        // Setup
        when(theMockFooService.getItems("bucketName")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems1() {
        // Setup
        when(theMockFooService.getItems("bucketName")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assertThat(result).isEqualTo([])
    }

    @Test
    void testGetItems21() {
        // Setup
        when(theMockFooService.getItems("bucketName", "criteria")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems1() {
        // Setup
        when(theMockFooService.getItems("bucketName", "criteria")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assertThat(result).isEqualTo([])
    }
}