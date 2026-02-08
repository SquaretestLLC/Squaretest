package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
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

    @Test(expected = IOException.class)
    public void testGetData_ThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        myClassUnderTest.getData("someValue");
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

    @Test(expected = IOException.class)
    public void testGetOtherData_ThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        myClassUnderTest.getOtherData("otherValue");
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

    @Test(expected = SQLException.class)
    public void testGetThingFromDatabase_ThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        myClassUnderTest.getThingFromDatabase("value");
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
