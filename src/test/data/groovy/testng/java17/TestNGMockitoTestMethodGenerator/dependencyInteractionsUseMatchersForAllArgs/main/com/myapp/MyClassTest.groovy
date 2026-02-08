package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.mockito.Mock

import java.util.Arrays
import java.util.List
import java.util.concurrent.*
import java.util.logging.Logger

import static org.testng.Assert.assertEquals
import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private ScheduledExecutorService mockScheduledExecutorService
    @Mock
    private Executor mockExecutor
    @Mock
    private Logger mockLogger

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockScheduledExecutorService, mockExecutor, mockLogger, 0)
    }

    @Test
    void testScheduleGetAllUsersAsync() {
        // Setup
        final Future<List<User>> expectedResult = CompletableFuture.completedFuture(Arrays.asList())
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        doAnswer(invocation -> {
            final Callable<?> callable = (Callable<?>) invocation.getArguments()[0]
            final ScheduledFuture<?> mockFuture = mock(ScheduledFuture.class)
            doReturn(callable.call()).when(mockFuture).get()
            doReturn(true).when(mockFuture).isDone()
            return mockFuture
        }).when(mockScheduledExecutorService).schedule((Callable<?>) any(Callable.class), eq(0L), eq(TimeUnit.MILLISECONDS))
        when(mockUserServiceAdapter.getUsers()).thenReturn(Arrays.asList())

        // Run the test
        final Future<List<User>> result = myClassUnderTest.scheduleGetAllUsersAsync()

        // Verify the results
        assertEquals(expectedResult, result)
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
    }
}
