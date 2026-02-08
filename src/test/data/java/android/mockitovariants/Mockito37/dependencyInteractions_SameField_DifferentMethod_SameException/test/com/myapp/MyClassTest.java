package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.other.FooService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testGetSomething() throws Exception {
        // Setup
        when(mockFooService.getData("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetSomething_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null);
        when(mockFooService.getOtherData("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testGetSomething_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("value")).thenThrow(IOException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }

    @Test
    public void testGetSomething_FooServiceGetOtherDataReturnsNull() throws Exception {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null);
        when(mockFooService.getOtherData("value")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testGetSomething_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null);
        when(mockFooService.getOtherData("value")).thenThrow(IOException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }

    @Test(expected = SQLException.class)
    public void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        when(mockFooService.getData("value")).thenReturn(null);
        when(mockFooService.getOtherData("value")).thenReturn(null);
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }
}
