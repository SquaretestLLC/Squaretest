package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private ScheduledExecutorService mockScheduledExecutorService;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockScheduledExecutorService,
                MoreExecutors.directExecutor(), mockLogger, 0);
    }

    @Test
    void testScheduleGetAllUsersAsync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final ScheduledFuture<?> mockFuture = mock(ScheduledFuture.class);
            doReturn(callable.call()).when(mockFuture).get();
            doReturn(true).when(mockFuture).isDone();
            return mockFuture;
        }).when(mockScheduledExecutorService).schedule((Callable<?>) any(Callable.class), eq(60L),
                eq(TimeUnit.SECONDS));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.scheduleGetAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }

    @Test
    void testScheduleGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User());
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final ScheduledFuture<?> mockFuture = mock(ScheduledFuture.class);
            doReturn(callable.call()).when(mockFuture).get();
            doReturn(true).when(mockFuture).isDone();
            return mockFuture;
        }).when(mockScheduledExecutorService).schedule((Callable<?>) any(Callable.class), eq(60L),
                eq(TimeUnit.SECONDS));
        when(mockUserServiceAdapter.getUsers()).thenReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.scheduleGetAllUsersAsync();

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()");
        verify(mockLogger).warning("Warn: getAllUsersAsync()");
        verify(mockLogger).fine("Fine: getAllUsersAsync()");
    }
}
