package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;

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

    @Test(expected = IOException.class)
    public void testDoDatabaseIO_ThrowsIOException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO();
    }

    @Test(expected = SQLException.class)
    public void testDoDatabaseIO_ThrowsSQLException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO();
    }

    @Test
    public void testCreateDatabaseConnection() throws Exception {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testCreateDatabaseConnection_ThrowsIOException() throws Exception {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection();
    }

    @Test(expected = SQLException.class)
    public void testCreateDatabaseConnection_ThrowsSQLException() throws Exception {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection();
    }
}
