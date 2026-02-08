package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testGetAllUsersSync() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
    }

    @Test
    void testStoreUserSync() {
        // Setup
        def myClassUnderTest = new MyClass()
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        def myClassUnderTest = new MyClass()
        def user = new User()

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_ThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_ThrowsSQLException() {
        // Setup
        def myClassUnderTest = new MyClass()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
        }).isInstanceOf(SQLException.class)
    }
}
