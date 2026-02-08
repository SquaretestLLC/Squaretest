package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private Executor mockExecutor;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockExecutorService, mockExecutor, mockLogger, 0);
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsyncWithExecutor1() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    void testStoreUserSync1() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    void testStoreUserAsync1() {
        // Setup
        final User user = new User();
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return CompletableFuture.completedFuture(null);
        }).when(mockExecutorService).submit(any(Runnable.class));

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync1() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()");
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep());
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep());
    }

    @Test
    void testCall2MethodsThatThrow1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.call2MethodsThatThrow());
    }

    @Test
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.call2MethodsThatThrow());
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    void testSafeCall2MethodsThatThrow1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.safeCall2MethodsThatThrow());
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.safeCall2MethodsThatThrow());
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }
}