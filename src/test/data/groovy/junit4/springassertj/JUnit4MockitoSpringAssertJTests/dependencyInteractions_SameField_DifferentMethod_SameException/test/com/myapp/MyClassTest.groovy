package com.myapp

import com.myapp.other.FooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
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
    void testGetSomething() {
        // Setup
        when(mockFooService.getData("value")).thenReturn("result")
        when(mockFooService.getOtherData("value")).thenReturn("result")
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getSomething("value")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetSomething_FooServiceGetDataReturnsNull() {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null)
        when(mockFooService.getOtherData("value")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getSomething("value")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetSomething_FooServiceGetDataThrowsIOException() {
        // Setup
        when(mockFooService.getData("value")).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getSomething("value")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetSomething_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null)
        when(mockFooService.getOtherData("value")).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getSomething("value")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() {
        // Setup
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getSomething("value")
        }).isInstanceOf(SQLException.class)
    }
}
