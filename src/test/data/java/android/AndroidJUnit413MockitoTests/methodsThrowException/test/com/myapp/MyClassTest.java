package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoDatabaseIO() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO();

        // Verify the results
    }

    @Test
    public void testDoDatabaseIO_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.doDatabaseIO());
    }

    @Test
    public void testDoDatabaseIO_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.doDatabaseIO());
    }

    @Test
    public void testSafeDoDatabaseIO() {
        // Setup
        // Run the test
        myClassUnderTest.safeDoDatabaseIO();

        // Verify the results
    }

    @Test
    public void testCreateDatabaseConnection() throws Exception {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection();

        // Verify the results
    }

    @Test
    public void testCreateDatabaseConnection_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, () -> MyClass.createDatabaseConnection());
    }

    @Test
    public void testCreateDatabaseConnection_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, () -> MyClass.createDatabaseConnection());
    }

    @Test
    public void testGetStringFromDatabase() throws Exception {
        assertEquals("", MyClass.getStringFromDatabase());
    }

    @Test
    public void testSafeGetStringFromDatabase() {
        assertEquals("", MyClass.safeGetStringFromDatabase());
    }
}
