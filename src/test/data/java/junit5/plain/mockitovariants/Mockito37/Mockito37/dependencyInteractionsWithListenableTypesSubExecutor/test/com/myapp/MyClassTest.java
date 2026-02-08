package com.myapp;

import com.google.common.util.concurrent.SettableFuture;
import com.myapp.exec.SubListeningExecutorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private SubListeningExecutorService mockExecutorService;
    @Mock
    private Logger mockLogger;
    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockExecutorService, mockLogger, mockFoo);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testGetAllUsersSync() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

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
    void testGetAllUsersAsync() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final SettableFuture settableFuture = SettableFuture.create();
            settableFuture.set(callable.call());
            return settableFuture;
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final SettableFuture settableFuture = SettableFuture.create();
            settableFuture.set(callable.call());
            return settableFuture;
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
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
        doAnswer(invocation -> {
            ((Runnable) invocation.getArguments()[0]).run();
            final SettableFuture settableFuture = SettableFuture.create();
            settableFuture.set(null);
            return settableFuture;
        }).when(mockExecutorService).submit(any(Runnable.class));

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final SettableFuture settableFuture = SettableFuture.create();
            settableFuture.set(callable.call());
            return settableFuture;
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()");
    }

    @Test
    void testDoSomethingWithCompletable() throws Exception {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockFoo).submitWithCompletable((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.doSomethingWithCompletable("userId");

        // Verify the results
    }

    @Test
    void testDoSomethingWithCompletable_FooReturnsFailure() throws Exception {
        // Setup
        // Configure Foo.submitWithCompletable(...).
        final CompletableFuture<User> userCompletableFuture = new CompletableFuture<>();
        userCompletableFuture.completeExceptionally(new Exception("message"));
        when(mockFoo.submitWithCompletable((Callable<?>) any(Callable.class))).thenReturn(userCompletableFuture);

        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.doSomethingWithCompletable("userId");

        // Verify the results
    }

    @Test
    void testDoSomethingWithCompletable_FooThrowsException() throws Exception {
        // Setup
        when(mockFoo.submitWithCompletable((Callable<?>) any(Callable.class))).thenThrow(Exception.class);

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.doSomethingWithCompletable("userId"));
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep());
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep());
    }
}
