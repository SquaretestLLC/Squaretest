package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@CompileStatic
@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    @Test
    void testPerformGetUpData() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
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
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class)
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        def myClassUnderTest = new MyClass(mockFooService)
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result")
    }

    @Test
    void testGetFoo() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo_FooServiceThrowsSQLException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class)

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isNull()
    }

    @Test
    void testGetFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.activateBar("data")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }
}
