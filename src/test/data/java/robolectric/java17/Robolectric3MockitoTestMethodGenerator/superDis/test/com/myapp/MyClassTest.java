package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
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
    public void testGetData1() throws Exception {
        // Setup
        when(mockFooService.getData("someValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testGetData_FooServiceThrowsIOException1() throws Exception {
        // Setup
        when(mockFooService.getData("someValue")).thenThrow(IOException.class);

        // Run the test
        myClassUnderTest.getData("someValue");
    }

    @Test
    public void testGetOtherData1() throws Exception {
        // Setup
        when(mockFooService.getOtherData("otherValue")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testGetOtherData_FooServiceThrowsIOException1() throws Exception {
        // Setup
        when(mockFooService.getOtherData("otherValue")).thenThrow(IOException.class);

        // Run the test
        myClassUnderTest.getOtherData("otherValue");
    }

    @Test
    public void testGetThingFromDatabase1() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = SQLException.class)
    public void testGetThingFromDatabase_FooServiceThrowsSQLException1() throws Exception {
        // Setup
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class);

        // Run the test
        myClassUnderTest.getThingFromDatabase("value");
    }

    @Test
    public void testDoSomething1() {
        // Setup
        when(mockFooService.doSomething("purchaseId")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar1() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar21() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar31() {
        // Setup
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertEquals("something", result);
    }
}