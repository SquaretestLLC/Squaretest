package com.myapp

import com.myapp.other.FooService
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import java.sql.SQLException

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
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
        assert "result" == result
    }

    @Test
    void testGetSomething_FooServiceGetDataReturnsNull() {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null)
        when(mockFooService.getOtherData("value")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getSomething("value")

        // Verify the results
        assert "result" == result
    }

    @Test(expected = IOException.class)
    void testGetSomething_FooServiceGetDataThrowsIOException() {
        // Setup
        when(mockFooService.getData("value")).thenThrow(IOException.class)

        // Run the test
        myClassUnderTest.getSomething("value")
    }

    @Test(expected = IOException.class)
    void testGetSomething_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null)
        when(mockFooService.getOtherData("value")).thenThrow(IOException.class)

        // Run the test
        myClassUnderTest.getSomething("value")
    }

    @Test(expected = SQLException.class)
    void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() {
        // Setup
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class)

        // Run the test
        myClassUnderTest.getSomething("value")
    }
}
