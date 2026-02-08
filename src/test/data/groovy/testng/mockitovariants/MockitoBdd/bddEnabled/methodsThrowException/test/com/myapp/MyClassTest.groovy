package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.sql.SQLException

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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

    @Test(expectedExceptions = [IOException.class])
    void testDoDatabaseIO_ThrowsIOException() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()
    }

    @Test(expectedExceptions = [SQLException.class])
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

    @Test(expectedExceptions = [IOException.class])
    void testCreateDatabaseConnection_ThrowsIOException() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()
    }

    @Test(expectedExceptions = [SQLException.class])
    void testCreateDatabaseConnection_ThrowsSQLException() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()
    }

    @Test
    void testGetStringFromDatabase() {
        assert "" == MyClass.getStringFromDatabase()
    }

    @Test(expectedExceptions = [SQLException.class])
    void testGetStringFromDatabase_ThrowsSQLException() {
        MyClass.getStringFromDatabase()
    }
}
