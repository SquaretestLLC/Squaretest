package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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

    @Test
    void testDoDatabaseIO_ThrowsIOException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doDatabaseIO()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testDoDatabaseIO_ThrowsSQLException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doDatabaseIO()
        }).isInstanceOf(SQLException.class)
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

    @Test
    void testCreateDatabaseConnection_ThrowsIOException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.createDatabaseConnection()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCreateDatabaseConnection_ThrowsSQLException1() {
        // Setup
        // Run the test
        assertThatThrownBy({
            MyClass.createDatabaseConnection()
        }).isInstanceOf(SQLException.class)
    }

    @Test
    void testGetStringFromDatabase1() {
        assertThat(MyClass.getStringFromDatabase()).isEqualTo("")
    }

    @Test
    void testSafeGetStringFromDatabase1() {
        assertThat(MyClass.safeGetStringFromDatabase()).isEqualTo("")
    }
}