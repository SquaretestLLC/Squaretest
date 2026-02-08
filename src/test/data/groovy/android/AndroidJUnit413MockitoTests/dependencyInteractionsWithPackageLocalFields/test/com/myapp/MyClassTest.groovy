package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.sql.SQLException

import static org.junit.Assert.assertThrows

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
    void testGetAllUsersSync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
    }

    @Test
    void testStoreUserSync() {
        // Setup
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        def user = new User()

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_ThrowsIOException() {
        // Setup
        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
        })
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
        })
    }
}
