package com.myapp

import com.myapp.other.FooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.sql.SQLException

import static org.mockito.BDDMockito.given

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
        given(mockFooService.getData("value")).willReturn("result")
        given(mockFooService.getOtherData("value")).willReturn("result")
        given(mockFooService.getThingFromDatabase("value")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getSomething("value")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetSomething_FooServiceGetDataReturnsNull() {
        // Setup
        given(mockFooService.getData("value")).willReturn(null)
        given(mockFooService.getOtherData("value")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.getSomething("value")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = IOException.class)
    void testGetSomething_FooServiceGetDataThrowsIOException() {
        // Setup
        given(mockFooService.getData("value")).willThrow(IOException.class)

        // Run the test
        myClassUnderTest.getSomething("value")
    }

    @Test(expected = IOException.class)
    void testGetSomething_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        given(mockFooService.getData("value")).willReturn(null)
        given(mockFooService.getOtherData("value")).willThrow(IOException.class)

        // Run the test
        myClassUnderTest.getSomething("value")
    }

    @Test(expected = SQLException.class)
    void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() {
        // Setup
        given(mockFooService.getThingFromDatabase("value")).willThrow(SQLException.class)

        // Run the test
        myClassUnderTest.getSomething("value")
    }
}
