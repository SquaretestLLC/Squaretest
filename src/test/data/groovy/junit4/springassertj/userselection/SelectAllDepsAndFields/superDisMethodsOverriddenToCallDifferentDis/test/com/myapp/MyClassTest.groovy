package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @Before
    void setUp() {
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
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData_FooServiceGetThingFromDatabaseThrowsSQLException() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class)
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result")
    }

    @Test
    void testGetFoo() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo_FooServiceThrowsSQLException() {
        // Setup
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
