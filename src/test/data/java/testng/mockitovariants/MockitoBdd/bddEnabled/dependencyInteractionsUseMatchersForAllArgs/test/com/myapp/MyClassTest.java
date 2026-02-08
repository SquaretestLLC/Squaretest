package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter;
    @Mock
    private ScheduledExecutorService mockScheduledExecutorService;
    @Mock
    private Logger mockLogger;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockScheduledExecutorService,
                MoreExecutors.directExecutor(), mockLogger, 0);
    }

    @Test
    public void testScheduleGetAllUsersAsync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());
        willAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final ScheduledFuture<?> mockFuture = mock(ScheduledFuture.class);
            willReturn(callable.call()).given(mockFuture).get();
            willReturn(true).given(mockFuture).isDone();
            return mockFuture;
        }).given(mockScheduledExecutorService).schedule((Callable<?>) any(Callable.class), eq(60L),
                eq(TimeUnit.SECONDS));
        given(mockUserServiceAdapter.getUsers()).willReturn(Arrays.asList(new User()));

        // Run the test
        final Future<List<User>> result = myClassUnderTest.scheduleGetAllUsersAsync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
        then(mockLogger).should().warning("Warn: getAllUsersAsync()");
        then(mockLogger).should().fine("Fine: getAllUsersAsync()");
    }

    @Test
    public void testScheduleGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User());
        willAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0];
            final ScheduledFuture<?> mockFuture = mock(ScheduledFuture.class);
            willReturn(callable.call()).given(mockFuture).get();
            willReturn(true).given(mockFuture).isDone();
            return mockFuture;
        }).given(mockScheduledExecutorService).schedule((Callable<?>) any(Callable.class), eq(60L),
                eq(TimeUnit.SECONDS));
        given(mockUserServiceAdapter.getUsers()).willReturn(Collections.emptyList());

        // Run the test
        final Future<List<User>> result = myClassUnderTest.scheduleGetAllUsersAsync();

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()");
        then(mockLogger).should().warning("Warn: getAllUsersAsync()");
        then(mockLogger).should().fine("Fine: getAllUsersAsync()");
    }
}
