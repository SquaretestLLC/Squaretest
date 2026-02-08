package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mock(UserServiceAdapter.class);
        myClassUnderTest.executorService = mock(ExecutorService.class);
        myClassUnderTest.logger = mock(Logger.class);
    }

    @Test
    public void testGetAllUsersSync1() {
        // Setup
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(myClassUnderTest.logger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(myClassUnderTest.logger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync1() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(myClassUnderTest.executorService).submit((Callable<?>) any(Callable.class));
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(myClassUnderTest.logger).info("getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(myClassUnderTest.executorService).submit((Callable<?>) any(Callable.class));
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(myClassUnderTest.logger).info("getAllUsersAsync()");
    }

    @Test
    public void testStoreUserSync1() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).putUser(any(User.class));
    }

    @Test
    public void testStoreUserAsync1() {
        // Setup
        final User user = new User();
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return CompletableFuture.completedFuture(null);
        }).when(myClassUnderTest.executorService).submit(any(Runnable.class));

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).putUser(any(User.class));
    }

    @Test
    public void testGetUserWithIdSync1() {
        // Setup
        when(myClassUnderTest.userServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync1() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(myClassUnderTest.executorService).submit((Callable<?>) any(Callable.class));
        when(myClassUnderTest.userServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(myClassUnderTest.logger).info("getUserWithIdAsync()");
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).doSomethingThatThrows();
    }

    @Test(expected = IOException.class)
    public void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(myClassUnderTest.userServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).doSomethingThatThrows();
    }

    @Test(expected = SQLException.class)
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(myClassUnderTest.userServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }
}