package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.bases.FooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("someValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testGetData_FooServiceThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getData("someValue")).thenThrow(IOException.class);

        // Run the test
        myClassUnderTest.getData("someValue");
    }

    @Test
    public void testGetOtherData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getOtherData("otherValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testGetOtherData_FooServiceThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getOtherData("otherValue")).thenThrow(IOException.class);

        // Run the test
        myClassUnderTest.getOtherData("otherValue");
    }

    @Test
    public void testGetThingFromDatabase() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = SQLException.class)
    public void testGetThingFromDatabase_FooServiceThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class);

        // Run the test
        myClassUnderTest.getThingFromDatabase("value");
    }

    @Test
    public void testDoSomething() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.doSomething("purchaseId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockFooService);
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertEquals("something", result);
    }
}
