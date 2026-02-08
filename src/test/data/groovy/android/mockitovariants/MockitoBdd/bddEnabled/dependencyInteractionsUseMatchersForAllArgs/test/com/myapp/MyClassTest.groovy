package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock

import java.util.concurrent.Callable
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.BDDMockito.*
import static org.mockito.Mockito.mock
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())
        willAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def mockFuture = mock(ScheduledFuture.class)
            willReturn(callable.call()).given(mockFuture).get()
            willReturn(true).given(mockFuture).isDone()
            return mockFuture
        }).given(mockScheduledExecutorService).schedule(any(Callable.class), eq(60L), eq(TimeUnit.SECONDS))
        given(mockUserServiceAdapter.getUsers()).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.scheduleGetAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
        then(mockLogger).should().warning("Warn: getAllUsersAsync()")
        then(mockLogger).should().fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testScheduleGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())
        willAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def mockFuture = mock(ScheduledFuture.class)
            willReturn(callable.call()).given(mockFuture).get()
            willReturn(true).given(mockFuture).isDone()
            return mockFuture
        }).given(mockScheduledExecutorService).schedule(any(Callable.class), eq(60L), eq(TimeUnit.SECONDS))
        given(mockUserServiceAdapter.getUsers()).willReturn([])

        // Run the test
        def result = myClassUnderTest.scheduleGetAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
        then(mockLogger).should().warning("Warn: getAllUsersAsync()")
        then(mockLogger).should().fine("Fine: getAllUsersAsync()")
    }
}
