package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ExecutorService executorService2;
    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private ExecutorService executorService1;
    @Mock
    private Logger mockLogger;

    @InjectMocks
    private MyClass myClassUnderTest;

    @Before
    void setUp() {
        initMocks(this);
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
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(executorService1).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
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
        }).when(executorService2).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
    }
}
