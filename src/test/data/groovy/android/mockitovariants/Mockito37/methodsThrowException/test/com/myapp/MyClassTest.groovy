package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.sql.SQLException

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoDatabaseIO() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testDoDatabaseIO_ThrowsIOException() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()
    }

    @Test(expected = SQLException.class)
    void testDoDatabaseIO_ThrowsSQLException() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()
    }

    @Test
    void testCreateDatabaseConnection() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testCreateDatabaseConnection_ThrowsIOException() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()
    }

    @Test(expected = SQLException.class)
    void testCreateDatabaseConnection_ThrowsSQLException() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()
    }
}
