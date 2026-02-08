package com.myapp

import com.myapp.bases.FooService
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
    void testPerformGetUpData() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenReturn("result")
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)
        when(mockFooService.getOtherData("data")).thenReturn("result")
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1() {
        def myClassUnderTest = new MyClass(mockFooService)
        assert "result" == myClassUnderTest.performGetUpData1("data")
    }
}
