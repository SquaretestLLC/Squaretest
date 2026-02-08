package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private Logger mockLogger;
    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockUserServiceAdapter,
                MoreExecutors.listeningDecorator(MoreExecutors.newDirectExecutorService()), mockLogger, mockFoo);
    }

    @Test
    public void testGetAllUsersSync() {
        // Setup
        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
    }

    @Test
    public void testGetAllUsersAsync() {
        // Setup
        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
    }

    @Test
    public void testStoreUserSync() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
    }

    @Test
    public void testStoreUserAsync() {
        // Setup
        final User user = new User();

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
    }

    @Test
    public void testGetUserWithIdSync() {
        // Setup
        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync() {
        // Setup
        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
    }

    @Test
    public void testDoSomethingWithCompletable() {
        // Setup
        // Run the test
        final Future<User> result = myClassUnderTest.doSomethingWithCompletable("userId");

        // Verify the results
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testDoSomethingThatThrowsSameExceptionAsDep_ThrowsIOException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
    }

    @Test(expected = SQLException.class)
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_ThrowsSQLException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }
}
