package com.myapp;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Future;

public class MyClassTest {

    @Test
    public void testGetAllUsersSync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
    }

    @Test
    public void testGetAllUsersAsync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
    }

    @Test
    public void testStoreUserSync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
    }

    @Test
    public void testStoreUserAsync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        final User user = new User();

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
    }

    @Test
    public void testGetUserWithIdSync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoSomethingThatThrowsSameExceptionAsDep_ThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_ThrowsSQLException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }
}
