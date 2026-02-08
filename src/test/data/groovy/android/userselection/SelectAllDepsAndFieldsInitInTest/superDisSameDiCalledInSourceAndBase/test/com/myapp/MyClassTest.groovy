package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private FooService mockFooService

    @Before
    void setUp() {
        initMocks(this)
    }

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
