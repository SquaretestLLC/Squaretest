package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricsAdapter mockMetricsAdapter;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockMetricsAdapter);
    }

    @Test
    void testGetSomething1() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn("result");
        when(mockFooService.getOtherData("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething1("theValue");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricsAdapter).recordCall0("theValue");
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
        verify(mockMetricsAdapter).recordCall4("theValue");
    }

    @Test
    void testGetSomething1_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething1("theValue");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricsAdapter).recordCall0("theValue");
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
        verify(mockMetricsAdapter).recordCall4("theValue");
    }

    @Test
    void testGetSomething1_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getSomething1("theValue"));
        verify(mockMetricsAdapter).recordCall0("theValue");
    }

    @Test
    void testGetSomething1_FooServiceGetOtherDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething1("theValue");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricsAdapter).recordCall0("theValue");
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
        verify(mockMetricsAdapter).recordCall3("theValue");
        verify(mockMetricsAdapter).recordCall4("theValue");
    }

    @Test
    void testGetSomething1_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getSomething1("theValue"));
        verify(mockMetricsAdapter).recordCall0("theValue");
        verify(mockMetricsAdapter).recordCall1("theValue");
    }

    @Test
    void testGetSomething1_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn("result");
        when(mockFooService.getOtherData("theValue")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("theValue")).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getSomething1("theValue"));
        verify(mockMetricsAdapter).recordCall0("theValue");
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
    }

    @Test
    void testGetSomething2() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething2("theValue");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall4("theValue");
    }

    @Test
    void testGetSomething2_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething2("theValue");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
        verify(mockMetricsAdapter).recordCall4("theValue");
    }

    @Test
    void testGetSomething2_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getSomething2("theValue"));
    }

    @Test
    void testGetSomething2_FooServiceGetOtherDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("theValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething2("theValue");

        // Verify the results
        assertEquals("result", result);
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
        verify(mockMetricsAdapter).recordCall3("theValue");
        verify(mockMetricsAdapter).recordCall4("theValue");
    }

    @Test
    void testGetSomething2_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn(null);
        when(mockFooService.getOtherData("theValue")).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getSomething2("theValue"));
        verify(mockMetricsAdapter).recordCall1("theValue");
    }

    @Test
    void testGetSomething2_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getData("theValue")).thenReturn("result");
        when(mockFooService.getOtherData("theValue")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("theValue")).thenThrow(SQLException.class);

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getSomething2("theValue"));
        verify(mockMetricsAdapter).recordCall1("theValue");
        verify(mockMetricsAdapter).recordCall2("theValue");
    }
}
