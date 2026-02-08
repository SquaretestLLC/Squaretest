package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    public void testGetData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getData("someValue"));
    }

    @Test
    public void testGetOtherData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetOtherData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getOtherData("otherValue"));
    }

    @Test
    public void testGetThingFromDatabase() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getThingFromDatabase("value"));
    }

    @Test
    public void testDoSomething() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar3() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertEquals("something", result);
    }
}
