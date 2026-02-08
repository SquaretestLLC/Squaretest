package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(),
                MoreExecutors.directExecutor(), mockLogger, 0);
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
        assertThat(result).isEqualTo(Collections.emptyList());
        then(mockLogger).should().info("getAllUsersSync()");
    }

    @Test
    public void testGetAllUsersAsync() {
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
    public void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
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
    public void testGetAllUsersAsyncWithExecutor() {
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
    public void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
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
    public void testDoSomethingThatThrowsSameExceptionAsDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test
    public void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep())
                .isInstanceOf(IOException.class);
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test
    public void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep())
                .isInstanceOf(SQLException.class);
    }

    @Test
    public void testCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
        then(mockUserServiceAdapter).should().doSomethingThatThrows1();
    }

    @Test
    public void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.call2MethodsThatThrow()).isInstanceOf(IOException.class);
    }

    @Test
    public void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.call2MethodsThatThrow()).isInstanceOf(IOException.class);
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }

    @Test
    public void testSafeCall2MethodsThatThrow() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow();

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
        then(mockUserServiceAdapter).should().doSomethingThatThrows1();
    }

    @Test
    public void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.safeCall2MethodsThatThrow()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() throws Exception {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows1();

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.safeCall2MethodsThatThrow()).isInstanceOf(RuntimeException.class);
        then(mockUserServiceAdapter).should().doSomethingThatThrows();
    }
}
