package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceThrowsIOException() {
        // Setup
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1() {
        assert "result" == myClassUnderTest.performGetUpData1("data")
    }
}
