package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private Logger mockLogger;
    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void prepareTheTest() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(), mockLogger, mockFoo);
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
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testGetUserWithIdAsync1() {
        // Setup
        final Future<User> expectedResult = CompletableFuture.completedFuture(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockFoo).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync1("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testGetUserWithIdAsync2() {
        // Setup
        final Future<User> expectedResult = CompletableFuture.completedFuture(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(mockFoo).submit((Callable<?>) any(Callable.class));
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync2("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(mockLogger).info("getAllUsersAsync()");
    }
}
