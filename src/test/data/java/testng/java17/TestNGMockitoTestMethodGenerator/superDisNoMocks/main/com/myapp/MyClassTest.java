package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;


class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    void testGetData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getData("someValue"));
    }

    @Test
    void testGetOtherData() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetOtherData_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getOtherData("otherValue"));
    }

    @Test
    void testGetThingFromDatabase() throws Exception {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getThingFromDatabase("value"));
    }

    @Test
    void testDoSomething() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testActivateBar() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testActivateBar2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testActivateBar3() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertEquals("something", result);
    }
}
