package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetItems1() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetItems2() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetItems3() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData", "fourthData"))
                .thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getItems("bucketName", "criteria", "otherData", "thirdData", "fourthData")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData")

        // Verify the results
        assert [] == result
    }
}
