package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertThrows
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

    @Test
    void testGetFoo1_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getFoo1("id")
        })
    }

    @Test
    void testGetFoo1_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        assertThrows(NoSuchElementException.class, {
            myClassUnderTest.getFoo1("id")
        })
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

    @Test
    void testGetFoo2_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getFoo2("id")
        })
    }

    @Test
    void testGetFoo2_FooServiceThrowsRuntimeException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(RuntimeException.class)

        // Run the test
        assertThrows(RuntimeException.class, {
            myClassUnderTest.getFoo2("id")
        })
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

    @Test
    void testGetFoo3_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getFoo3("id")
        })
    }

    @Test
    void testGetFoo3_FooServiceThrowsNoSuchElementException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(NoSuchElementException.class)

        // Run the test
        assertThrows(NoSuchElementException.class, {
            myClassUnderTest.getFoo3("id")
        })
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

    @Test
    void testGetFoo4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.getFoo4("id")
        })
    }

    @Test
    void testGetFoo4_FooServiceThrowsRuntimeException() {
        // Setup
        when(mockFooService.getFoo2("id")).thenThrow(RuntimeException.class)

        // Run the test
        assertThrows(RuntimeException.class, {
            myClassUnderTest.getFoo4("id")
        })
    }
}
