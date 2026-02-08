package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(),
                MoreExecutors.directExecutor(), mockLogger, 0);
    }

    @Test
    void testGetAllUsersSync() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        then(mockLogger).should().info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());
        given(mockUserServiceAdapter.getUsers()).willReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
        then(mockLogger).should().warning("Warn: getAllUsersAsync()");
        then(mockLogger).should().fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());
        given(mockUserServiceAdapter.getUsers()).willReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
        then(mockLogger).should().warning("Warn: getAllUsersAsync()");
        then(mockLogger).should().fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsyncWithExecutor() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());
        given(mockUserServiceAdapter.getUsers()).willReturn(Arrays.asList(new User()));

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
        then(mockLogger).should().warning("Warn: getAllUsersAsync()");
        then(mockLogger).should().fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());
        given(mockUserServiceAdapter.getUsers()).willReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
        then(mockLogger).should().warning("Warn: getAllUsersAsync()");
        then(mockLogger).should().fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testStoreUserSync() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        then(mockUserServiceAdapter).should().putUser(any(User.class));
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        final User user = new User();

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        then(mockUserServiceAdapter).should().putUser(any(User.class));
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        then(mockLogger).should().info("getUserWithIdAsync()");
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep());
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep());
    }

    @Test
    void testCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
        then(mockUserServiceAdapter).should().doSomethingThatThrows1();
    }

    @Test
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.call2MethodsThatThrow());
    }

    @Test
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.call2MethodsThatThrow());
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test
    void testSafeCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
        then(mockUserServiceAdapter).should().doSomethingThatThrows1();
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.safeCall2MethodsThatThrow());
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        assertThrows(RuntimeException.class, () -> myClassUnderTest.safeCall2MethodsThatThrow());
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }
}
