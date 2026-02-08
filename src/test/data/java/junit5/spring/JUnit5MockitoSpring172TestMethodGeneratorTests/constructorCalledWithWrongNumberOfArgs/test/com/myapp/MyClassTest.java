package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(), mockLogger,
                new Foo());
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final List<User> result = myClassUnderTest.getAllUsersSync();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
        verify(mockLogger).info("getAllUsersSync()");
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(List.of(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.getAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final User result = myClassUnderTest.getUserWithIdSync("userId");

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync3() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()");
    }

    @Test
    void testGetUserWithIdAsync11() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync1("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync1()");
    }

    @Test
    void testGetUserWithIdAsync21() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());

        // Run the test
        final Future<User> result = myClassUnderTest.getUserWithIdAsync2("userId");

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync2()");
    }
}