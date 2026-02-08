package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new UserServiceAdapter(), MoreExecutors.newDirectExecutorService(),
                Logger.getLogger("name"), new Foo());
    }

    @Test
    void testGetAllUsersSync() {
        // Setup
        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync1() {
        // Setup
        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync1("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync2() {
        // Setup
        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync2("userId");

        // Verify the results
    }
}