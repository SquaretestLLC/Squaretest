package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private Logger mockLogger;
    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter,
                MoreExecutors.listeningDecorator(MoreExecutors.newDirectExecutorService()), mockLogger, mockFoo);
    }

    @Test
    public void testGetAllUsersSync() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        then(mockLogger).should().info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
    }

    @Test
    public void testStoreUserSync() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        then(mockUserServiceAdapter).should().putUser(any(User.class));
    }

    @Test
    public void testStoreUserAsync() {
        // Setup
        final User user = new User();

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        then(mockUserServiceAdapter).should().putUser(any(User.class));
    }

    @Test
    public void testGetUserWithIdSync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        then(mockLogger).should().info("getUserWithIdAsync()");
    }

    @Test
    public void testDoSomethingWithCompletable() throws Exception {
        // Setup
        willAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            return CompletableFuture.completedFuture(callable.call());
        }).given(mockFoo).submitWithCompletable((Callable<?>) any(Callable.class));
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.doSomethingWithCompletable("userId");

        // Verify the results
    }

    @Test
    public void testDoSomethingWithCompletable_FooReturnsFailure() throws Exception {
        // Setup
        // Configure Foo.submitWithCompletable(...).
        final CompletableFuture<User> userCompletableFuture = new CompletableFuture<>();
        userCompletableFuture.completeExceptionally(new Exception("message"));
        given(mockFoo.submitWithCompletable((Callable<?>) any(Callable.class))).willReturn(userCompletableFuture);

        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.doSomethingWithCompletable("userId");

        // Verify the results
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testDoSomethingWithCompletable_FooThrowsException() throws Exception {
        // Setup
        given(mockFoo.submitWithCompletable((Callable<?>) any(Callable.class))).willThrow(Exception.class);

        // Run the test
        myClassUnderTest.doSomethingWithCompletable("userId");
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }
}
