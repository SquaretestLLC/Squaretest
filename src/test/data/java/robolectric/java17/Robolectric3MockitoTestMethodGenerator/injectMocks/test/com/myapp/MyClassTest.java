package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private UserServiceAdapter theMockUserServiceAdapter;
    @Mock
    private ExecutorService theMockExecutorService;
    @Mock
    private Logger theMockLogger;
    @Mock
    private Foo theMockFoo;

    private MyClass myClass;

    @Before
    public void setUp() {
        initMocks(this);
        myClass = new MyClass(theMockUserServiceAdapter, theMockExecutorService, theMockLogger, theMockFoo);
    }

    @Test
    public void testGetAllUsersSync1() {
        // Setup
        when(theMockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final List<User> result = myClass.getAllUsersSync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(theMockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClass.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(theMockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync1() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockExecutorService).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final Future<List<User>> result = myClass.getAllUsersAsync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockExecutorService).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClass.getAllUsersAsync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()");
    }

    @Test
    public void testGetUserWithIdSync1() {
        // Setup
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClass.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync3() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockExecutorService).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync()");
    }

    @Test
    public void testGetUserWithIdAsync11() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockFoo).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync1("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync1()");
    }

    @Test
    public void testGetUserWithIdAsync1_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        final Future<User> userFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(theMockFoo.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync1("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync1()");
    }

    @Test
    public void testGetUserWithIdAsync21() {
        // Setup
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).when(theMockFoo).submit((Callable<?>) any(Callable.class));
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()");
    }

    @Test
    public void testGetUserWithIdAsync2_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        final Future<User> userFuture = CompletableFuture.failedFuture(new Exception("message"));
        when(theMockFoo.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()");
    }
}