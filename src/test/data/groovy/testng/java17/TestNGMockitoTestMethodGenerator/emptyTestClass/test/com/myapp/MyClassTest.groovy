package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.logging.Logger

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(new UserServiceAdapter(), MoreExecutors.newDirectExecutorService(),
                Logger.getLogger("name"), new Foo())
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
    void testGetUserWithIdAsync1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync1("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync2("userId")

        // Verify the results
    }
}