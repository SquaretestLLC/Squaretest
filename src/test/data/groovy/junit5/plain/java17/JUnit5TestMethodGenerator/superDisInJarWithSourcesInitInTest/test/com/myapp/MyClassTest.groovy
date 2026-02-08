package com.myapp

import com.squaretest.supertypes.base.FooService
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

    @BeforeEach
    void setUp() {
        initMocks(this)
    }

    @Test
    void testPerformGetUpData2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceGetDataThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceGetOtherDataThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData11() {
        def myClassUnderTest = new MyClass(mockFooService)
        assert "result" == myClassUnderTest.performGetUpData1("data")
    }

    @Test
    void testGetFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo_FooServiceThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo21() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo2_FooServiceThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo_FooServiceGetDataThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo_FooServiceGetOtherDataThrowsIOException1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("data")).thenReturn("result")
        when(mockFooService.getOtherData("data")).thenThrow(IOException.class)

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo11() {
        def myClassUnderTest = new MyClass(mockFooService)
        assert "result" == myClassUnderTest.getUpperFoo1("key")
    }
}