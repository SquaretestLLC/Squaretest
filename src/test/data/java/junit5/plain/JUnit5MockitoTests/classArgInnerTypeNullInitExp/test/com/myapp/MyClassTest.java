package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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
    void testGetFoo1() throws Exception {
        // Setup
        // Configure FooService.unwrap1(...).
        final Connection mockConnection = mock(Connection.class);
        when(mockFooService.unwrap1(Connection.class)).thenReturn(mockConnection);

        // Run the test
        final String result = myClassUnderTest.getFoo1();

        // Verify the results
        assertEquals("result", result);
        verify(mockConnection).close();
    }

    @Test
    void testGetFoo2() throws Exception {
        // Setup
        doReturn(Object.class).when(mockFooService).getTheClass1("id");
        when(mockFooService.unwrap1(Object.class)).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3() throws Exception {
        // Setup
        doReturn(Connection.class).when(mockFooService).getTheClass2("id");

        // Configure FooService.unwrap1(...).
        final Connection mockConnection = mock(Connection.class);
        when(mockFooService.unwrap1(Connection.class)).thenReturn(mockConnection);

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
        verify(mockConnection).close();
    }
}
