package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
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
        assert ["value"] == result
    }

    @Test
    void testGetItems1_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetItems2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn(["value"])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems2_FooServiceReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getItems("bucketName", "criteria")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert [] == result
    }
}
