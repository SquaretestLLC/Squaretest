package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private Logger theMockLogger;

    private MyClass myClass;

    @Before
    public void setUp() {
        initMocks(this);
        myClass = new MyClass();
        myClass.foo = mock(Foo.class);
        myClass.logger = theMockLogger;
    }

    @Test
    public void testGetAllUsersSync1() {
        // Setup
        // Run the test
        final List<User> result = myClass.getAllUsersSync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync1() {
        // Setup
        // Run the test
        final Future<List<User>> result = myClass.getAllUsersAsync();

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()");
    }

    @Test
    public void testGetUserWithIdSync1() {
        // Setup
        // Run the test
        final User result = myClass.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync3() {
        // Setup
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
        }).when(myClass.foo).submit((Callable<?>) any(Callable.class));

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
        when(myClass.foo.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

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
        }).when(myClass.foo).submit((Callable<?>) any(Callable.class));

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
        when(myClass.foo.submit((Callable<?>) any(Callable.class))).thenReturn(userFuture);

        // Run the test
        final Future<User> result = myClass.getUserWithIdAsync2("userId");

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()");
    }
}