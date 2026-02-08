package com.myapp;

import com.myapp.other.FooService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testGetSomething() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetSomething_FooServiceGetDataReturnsNull() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testGetSomething_FooServiceGetDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willThrow(IOException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }

    @Test
    public void testGetSomething_FooServiceGetOtherDataReturnsNull() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn(null);
        given(mockFooService.getThingFromDatabase("value")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getSomething("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testGetSomething_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willThrow(IOException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn(null);
        given(mockFooService.getThingFromDatabase("value")).willThrow(SQLException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }
}
