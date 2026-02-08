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
    private UserServiceAdapter theMockUserServiceAdapter;
    @Mock
    private Logger theMockLogger;
    @Mock
    private Foo theMockFoo;

    private MyClass myClass;

    @BeforeMethod
    void setUp() {
        initMocks(this);
        myClass = new MyClass(theMockUserServiceAdapter, MoreExecutors.newDirectExecutorService(), theMockLogger, theMockFoo);
    }

    @Test
    void testGetAllUsersSync() {
        myClass = new MyClass(null, null, null, null);
        // Setup
        final List<User> expectedResult = Arrays.asList();
        when(theMockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList());

        // Run the test
        final List<User> result = myClass.getAllUsersSync();

        // Verify the results
        assertEquals(expectedResult, result);
        verify(theMockLogger).info("msg");
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        final Future<List<User>> expectedResult = CompletableFuture.completedFuture(Arrays.asList());
        when(theMockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList());

        // Run the test
        final Future<List<User>> result = myClass.getAllUsersAsync();

        // Verify the results
        assertEquals(expectedResult, result);
        verify(theMockLogger).info("msg");
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        final User expectedResult = new User();
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClass.getUserWithIdSync("userId");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        final Future<User> expectedResult = CompletableFuture.completedFuture(new User());
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(theMockLogger).info("msg");
    }

    @Test
    void testGetUserWithIdAsync1() {
        // Setup
        final Future<User> expectedResult = CompletableFuture.completedFuture(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockFoo).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync1("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(theMockLogger).info("msg");
    }

    @Test
    void testGetUserWithIdAsync2() {
        // Setup
        final Future<User> expectedResult = CompletableFuture.completedFuture(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockFoo).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        assertEquals(expectedResult, result);
        verify(theMockLogger).info("msg");
    }
}
