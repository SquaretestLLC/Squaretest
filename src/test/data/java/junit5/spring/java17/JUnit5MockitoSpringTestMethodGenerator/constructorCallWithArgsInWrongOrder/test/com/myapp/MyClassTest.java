package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ExecutorService mockUserServiceAdapter;
    @Mock
    private Logger mockFoo;
    @Mock
    private Foo mockLogger;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(new UserServiceAdapter(), mockUserServiceAdapter, mockFoo, mockLogger);
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(mockFoo).info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockUserServiceAdapter).submit((Callable<?>) any(Callable.class));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockFoo).info("getAllUsersAsync()");
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync3() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockUserServiceAdapter).submit((Callable<?>) any(Callable.class));

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync()");
    }

    @Test
    void testGetUserWithIdAsync11() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockLogger).submit((Callable<?>) any(Callable.class));

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync1("userId");

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync1()");
    }

    @Test
    void testGetUserWithIdAsync1_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        final Future<User> userFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(mockLogger.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync1("userId");

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync1()");
    }

    @Test
    void testGetUserWithIdAsync21() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockLogger).submit((Callable<?>) any(Callable.class));

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync2("userId");

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync2()");
    }

    @Test
    void testGetUserWithIdAsync2_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        final Future<User> userFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(mockLogger.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync2("userId");

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync2()");
    }
}