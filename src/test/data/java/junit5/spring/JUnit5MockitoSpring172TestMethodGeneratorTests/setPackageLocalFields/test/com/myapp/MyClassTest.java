package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
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
    private Logger theMockLogger;

    private ExecutorService directExecutorService;

    private MyClass myClass;

    @BeforeEach
    void setUp() {
        initMocks(this);
        directExecutorService = MoreExecutors.newDirectExecutorService();
        myClass = new MyClass();
        myClass.userServiceAdapter = new UserServiceAdapter();
        myClass.executorService = MoreExecutors.newDirectExecutorService();
        myClass.foo = mock(Foo.class);
        myClass.logger = mock(Logger.class);
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        // Run the test
        final List<User> result = myClass.getAllUsersSync();

        // Verify the results
        verify(myClass.logger).info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        // Run the test
        final Future<List<User>> result = myClass.getAllUsersAsync();

        // Verify the results
        verify(myClass.logger).info("getAllUsersAsync()");
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        // Run the test
        final User result = myClass.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync3() {
        // Setup
        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync("userId");

        // Verify the results
        verify(myClass.logger).info("getUserWithIdAsync()");
    }

    @Test
    void testGetUserWithIdAsync11() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(myClass.foo).submit((Callable<?>) any(Callable.class));

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync1("userId");

        // Verify the results
        verify(myClass.logger).info("getUserWithIdAsync1()");
    }

    @Test
    void testGetUserWithIdAsync1_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        final Future<User> userFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(myClass.foo.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync1("userId");

        // Verify the results
        verify(myClass.logger).info("getUserWithIdAsync1()");
    }

    @Test
    void testGetUserWithIdAsync21() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(myClass.foo).submit((Callable<?>) any(Callable.class));

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        verify(myClass.logger).info("getUserWithIdAsync2()");
    }

    @Test
    void testGetUserWithIdAsync2_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        final Future<User> userFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(myClass.foo.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        verify(myClass.logger).info("getUserWithIdAsync2()");
    }
}