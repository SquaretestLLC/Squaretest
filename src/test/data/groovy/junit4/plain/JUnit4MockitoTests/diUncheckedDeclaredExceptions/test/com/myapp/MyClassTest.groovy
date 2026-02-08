package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetFoo1() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testGetFoo1_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFoo1("id")
    }

    @Test(expected = NoSuchElementException.class)
    void testGetFoo1_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        myClassUnderTest.getFoo1("id")
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Configure FooService.getFoo2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo2("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo2("id")

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testGetFoo2_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFoo2("id")
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo2_FooServiceThrowsRuntimeException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo2("id")
    }

    @Test
    void testGetFoo3() {
        // Setup
        // Configure FooService.getFoo1(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo1("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo3("id")

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testGetFoo3_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFoo3("id")
    }

    @Test(expected = NoSuchElementException.class)
    void testGetFoo3_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        myClassUnderTest.getFoo3("id")
    }

    @Test
    void testGetFoo4() {
        // Setup
        // Configure FooService.getFoo2(...).
        def fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(mockFooService.getFoo2("id")).thenReturn(fooData)

        // Run the test
        def result = myClassUnderTest.getFoo4("id")

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testGetFoo4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFoo4("id")
    }

    @Test(expected = RuntimeException.class)
    void testGetFoo4_FooServiceThrowsRuntimeException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(RuntimeException.class)

        // Run the test
        myClassUnderTest.getFoo4("id")
    }
}
