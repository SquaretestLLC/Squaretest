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
    void testDoDatabaseIO1() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()

        // Verify the results
    }

    @Test(expectedExceptions = [IOException.class])
    void testDoDatabaseIO_ThrowsIOException1() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()
    }

    @Test(expectedExceptions = [SQLException.class])
    void testDoDatabaseIO_ThrowsSQLException1() {
        // Setup
        // Run the test
        myClassUnderTest.doDatabaseIO()
    }

    @Test
    void testSafeDoDatabaseIO1() {
        // Setup
        // Run the test
        myClassUnderTest.safeDoDatabaseIO()

        // Verify the results
    }

    @Test
    void testCreateDatabaseConnection1() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()

        // Verify the results
    }

    @Test(expectedExceptions = [IOException.class])
    void testCreateDatabaseConnection_ThrowsIOException1() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()
    }

    @Test(expectedExceptions = [SQLException.class])
    void testCreateDatabaseConnection_ThrowsSQLException1() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()
    }

    @Test
    void testGetStringFromDatabase1() {
        assert "" == MyClass.getStringFromDatabase()
    }

    @Test
    void testSafeGetStringFromDatabase1() {
        assert "" == MyClass.safeGetStringFromDatabase()
    }
}