package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockNewFooService
    @Mock
    private FooService mockOldFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockNewFooService, mockOldFooService)
    }

    @Test
    void testGetFooData() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(new FooData("name", 0L))

        // Run the test
        def result = myClassUnderTest.getFooData(0)

        // Verify the results
    }

    @Test
    void testGetFooData_NewFooServiceReturnsNull() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null)
        when(mockOldFooService.getFooData(0L)).thenReturn(new FooData("name", 0L))

        // Run the test
        def result = myClassUnderTest.getFooData(0)

        // Verify the results
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData_NewFooServiceThrowsFooServiceException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData(0)
    }

    @Test(expectedExceptions = [FooNetworkException.class])
    void testGetFooData_NewFooServiceThrowsFooNetworkException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenThrow(FooNetworkException.class)

        // Run the test
        myClassUnderTest.getFooData(0)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testGetFooData_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null)
        when(mockOldFooService.getFooData(0L)).thenThrow(FooServiceException.class)

        // Run the test
        myClassUnderTest.getFooData(0)
    }

    @Test(expectedExceptions = [FooNetworkException.class])
    void testGetFooData_OldFooServiceThrowsFooNetworkException() {
        // Setup
        when(mockNewFooService.getFooData(0L)).thenReturn(null)
        when(mockOldFooService.getFooData(0L)).thenThrow(FooNetworkException.class)

        // Run the test
        myClassUnderTest.getFooData(0)
    }
}
