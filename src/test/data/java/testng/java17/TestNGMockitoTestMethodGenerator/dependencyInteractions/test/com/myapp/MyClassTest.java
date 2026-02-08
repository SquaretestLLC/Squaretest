package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private Executor mockExecutor;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockExecutorService, mockExecutor, mockLogger, 0);
    }

    @Test
    public void testGetAllUsersSync1() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync1() {
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
    public void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
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
    public void testGetAllUsersAsyncWithExecutor1() {
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
    public void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
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
    public void testStoreUserSync1() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    public void testStoreUserAsync1() {
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
    public void testGetUserWithIdSync1() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

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
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()");
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }

    @Test
    public void testCall2MethodsThatThrow1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.call2MethodsThatThrow();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        myClassUnderTest.call2MethodsThatThrow();
    }

    @Test
    public void testSafeCall2MethodsThatThrow1() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException1() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();
    }
}