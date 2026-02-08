package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testGetAllUsersSync() {
        // Setup
        final List<User> expectedResult = Arrays.asList();
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        final Future<List<User>> expectedResult = CompletableFuture.completedFuture(Arrays.asList());
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsyncWithExecutor() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(mockExecutor).execute(any(Runnable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList());

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
        verify(mockExecutor).execute(any(Runnable.class));
    }

    @Test
    void testStoreUserSync() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        final User user = new User();
        final Future<?> expectedResult = CompletableFuture.completedFuture(null);
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return CompletableFuture.completedFuture(null);
        }).when(mockExecutorService).submit(any(Runnable.class));

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        final User expectedResult = new User();
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        final Future<User> expectedResult = CompletableFuture.completedFuture(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expected = IOException.class)
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expected = SQLException.class)
    void testDoSomethingThatThrowsDifferentExceptionThanDep_ThrowsSQLException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
    }

    @Test
    void testCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test(expected = IOException.class)
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.call2MethodsThatThrow();
    }

    @Test(expected = IOException.class)
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        myClassUnderTest.call2MethodsThatThrow();
    }

    @Test
    void testSafeCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }
}
