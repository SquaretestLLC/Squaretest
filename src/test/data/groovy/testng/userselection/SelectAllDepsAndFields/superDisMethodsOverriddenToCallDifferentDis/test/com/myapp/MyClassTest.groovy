package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.sql.SQLException

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result")
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData_FooServiceGetThingFromDatabaseThrowsSQLException() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class)
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1() {
        assert "result" == myClassUnderTest.performGetUpData1("data")
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo_FooServiceThrowsSQLException() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assert "result" == result
    }
}
