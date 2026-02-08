package com.myapp;

import com.myapp.other.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetSomething() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("theValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetSomething_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("theValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetSomething_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getSomething("theValue"));
    }

    @Test
    void testGetSomething_FooServiceGetOtherDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("theValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetSomething_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getSomething("theValue"));
    }

    @Test
    void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("theValue")).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getSomething("theValue"));
    }
}
