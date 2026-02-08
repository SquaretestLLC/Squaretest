package com.myapp;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;


public class MyClassTest {

    @Test
    public void testGetData1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getData("someValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testGetData_ThrowsIOException1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        myClassUnderTest.getData("someValue");
    }

    @Test
    public void testGetOtherData1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testGetOtherData_ThrowsIOException1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        myClassUnderTest.getOtherData("otherValue");
    }

    @Test
    public void testGetThingFromDatabase1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value");

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testGetThingFromDatabase_ThrowsSQLException1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        myClassUnderTest.getThingFromDatabase("value");
    }

    @Test
    public void testDoSomething1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar1() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar2("something");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testActivateBar31() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.activateBar3("something");

        // Verify the results
        assertEquals("something", result);
    }
}