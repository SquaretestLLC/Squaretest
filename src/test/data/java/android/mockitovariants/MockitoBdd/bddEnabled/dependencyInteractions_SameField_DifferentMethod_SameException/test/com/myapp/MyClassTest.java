package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.other.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
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

    @Test(expected = IOException.class)
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

    @Test(expected = IOException.class)
    public void testGetSomething_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willThrow(IOException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }

    @Test(expected = SQLException.class)
    public void testGetSomething_FooServiceGetThingFromDatabaseThrowsSQLException() throws Exception {
        // Setup
        given(mockFooService.getData("value")).willReturn(null);
        given(mockFooService.getOtherData("value")).willReturn(null);
        given(mockFooService.getThingFromDatabase("value")).willThrow(SQLException.class);

        // Run the test
        myClassUnderTest.getSomething("value");
    }
}
