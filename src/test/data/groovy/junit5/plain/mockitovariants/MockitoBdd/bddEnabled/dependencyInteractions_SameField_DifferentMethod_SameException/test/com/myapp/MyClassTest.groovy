package com.myapp

import com.myapp.other.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
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

    @Test
    void testGetSomething_FooServiceGetDataThrowsIOException() {
        // Setup
        given(mockFooService.getData("value")).willThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getSomething("value")
        })
    }

    @Test
    void testGetSomething_FooServiceGetOtherDataThrowsIOException() {
        // Setup
        given(mockFooService.getData("value")).willReturn(null)
        given(mockFooService.getOtherData("value")).willThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getSomething("value")
        })
    }

    @Test
    void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() {
        // Setup
        given(mockFooService.getThingFromDatabase("value")).willThrow(SQLException.class)

        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.getSomething("value")
        })
    }
}
