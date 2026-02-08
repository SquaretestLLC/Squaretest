package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.sql.SQLException

import static org.testng.Assert.assertThrows

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

    @Test
    void testDoDatabaseIO_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doDatabaseIO()
        })
    }

    @Test
    void testDoDatabaseIO_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.doDatabaseIO()
        })
    }

    @Test
    void testCreateDatabaseConnection() {
        // Setup
        // Run the test
        MyClass.createDatabaseConnection()

        // Verify the results
    }

    @Test
    void testCreateDatabaseConnection_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, {
            MyClass.createDatabaseConnection()
        })
    }

    @Test
    void testCreateDatabaseConnection_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, {
            MyClass.createDatabaseConnection()
        })
    }
}
