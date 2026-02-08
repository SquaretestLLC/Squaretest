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
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(),
                MoreExecutors.directExecutor(), mockLogger, 0);
    }

    @Test
    public void testGetAllUsersSync() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsyncWithExecutor() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    public void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    public void testStoreUserSync() {
        // Setup
        final User user = new User();

        // Run the test
        myClassUnderTest.storeUserSync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    public void testStoreUserAsync() {
        // Setup
        final User user = new User();

        // Run the test
        final Future<?> result = myClassUnderTest.storeUserAsync(user);

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class));
    }

    @Test
    public void testGetUserWithIdSync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    public void testGetUserWithIdAsync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()");
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDepProtected() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDepProtected();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testDoSomethingThatThrowsDifferentExceptionThanDepProtected_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDepProtected();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDepPackageLocal() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDepPackageLocal();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
    }

    @Test(expectedExceptions = {SQLException.class})
    public void testDoSomethingThatThrowsDifferentExceptionThanDepPackageLocal_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDepPackageLocal();
    }

    @Test
    public void testDoSomethingWithStringProtected() {
        assertEquals("result", MyClass.doSomethingWithStringProtected("str"));
    }

    @Test
    public void testDoSomethingWithStringPackageLocal() {
        assertEquals("result", MyClass.doSomethingWithStringPackageLocal("str"));
    }

    @Test
    public void testDoSomethingWithString() {
        assertEquals("result", MyClass.doSomethingWithString("str"));
    }

    @Test
    public void testCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.call2MethodsThatThrow();
    }

    @Test(expectedExceptions = {IOException.class})
    public void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        myClassUnderTest.call2MethodsThatThrow();
    }

    @Test
    public void testSafeCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows();
        verify(mockUserServiceAdapter).doSomethingThatThrows1();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();
    }
}
