package com.myapp;

import com.myapp.bases.FooService;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MyClassTest {

    @Test
    public void testGetData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetData_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getData("someValue"));
    }

    @Test
    public void testGetOtherData() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetOtherData_ThrowsIOException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getOtherData("otherValue"));
    }

    @Test
    public void testGetThingFromDatabase() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetThingFromDatabase_ThrowsSQLException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getThingFromDatabase("value"));
    }

    @Test
    public void testDoSomething() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar2() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar3() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertEquals("something", result);
    }
}
