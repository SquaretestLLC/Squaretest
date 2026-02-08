package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private Logger mockLogger;

    @Test
    public void testGetAllUsersSync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockExecutorService).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    public void testStoreUserSync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    public void testStoreUserAsync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
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
    public void testGetUserWithIdSync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync() {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
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
    public void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep())
                .isInstanceOf(IOException.class);
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter;
        myClassUnderTest.executorService = mockExecutorService;
        myClassUnderTest.logger = mockLogger;
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep())
                .isInstanceOf(SQLException.class);
    }
}
