package com.myapp;

import com.myapp.bases.FooService;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Test
    public void testPerformGetUpData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result");
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class);
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testPerformGetUpData1() {
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        assertEquals("result", myClassUnderTest.performGetUpData1("data"));
    }

    @Test
    public void testGetFoo() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo_FooServiceThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("data")).thenThrow(SQLException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("data")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertEquals("result", result);
    }
}
