package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.robolectric.RobolectricTestRunner

import java.util.concurrent.Callable
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private ScheduledExecutorService mockScheduledExecutorService
    @Mock
    private Logger mockLogger

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockScheduledExecutorService,
                MoreExecutors.directExecutor(), mockLogger, 0)
    }

    @Test
    void testScheduleGetAllUsersAsync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def mockFuture = mock(ScheduledFuture.class)
            doReturn(callable.call()).when(mockFuture).get()
            doReturn(true).when(mockFuture).isDone()
            return mockFuture
        }).when(mockScheduledExecutorService).schedule(any(Callable.class), eq(60L), eq(TimeUnit.SECONDS))
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.scheduleGetAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testScheduleGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def mockFuture = mock(ScheduledFuture.class)
            doReturn(callable.call()).when(mockFuture).get()
            doReturn(true).when(mockFuture).isDone()
            return mockFuture
        }).when(mockScheduledExecutorService).schedule(any(Callable.class), eq(60L), eq(TimeUnit.SECONDS))
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.scheduleGetAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
    }
}
