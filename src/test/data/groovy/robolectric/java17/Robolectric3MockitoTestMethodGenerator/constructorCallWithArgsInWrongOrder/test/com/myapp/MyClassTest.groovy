package com.myapp


import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.robolectric.RobolectricTestRunner

import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private ExecutorService mockUserServiceAdapter
    @Mock
    private Logger mockFoo
    @Mock
    private Foo mockLogger

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(new UserServiceAdapter(), mockUserServiceAdapter, mockFoo, mockLogger)
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        verify(mockFoo).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockUserServiceAdapter).submit(any(Callable.class))

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockFoo).info("getAllUsersAsync()")
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync3() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockUserServiceAdapter).submit(any(Callable.class))

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync()")
    }

    @Test
    void testGetUserWithIdAsync11() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockLogger).submit(any(Callable.class))

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync1("userId")

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync1()")
    }

    @Test
    void testGetUserWithIdAsync1_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        def userFuture = CompletableFuture.failedFuture(new Exception("message"))
        when(mockLogger.submit(any(Callable.class))).thenReturn(userFuture)

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync1("userId")

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync1()")
    }

    @Test
    void testGetUserWithIdAsync21() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockLogger).submit(any(Callable.class))

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync2("userId")

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync2()")
    }

    @Test
    void testGetUserWithIdAsync2_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        def userFuture = CompletableFuture.failedFuture(new Exception("message"))
        when(mockLogger.submit(any(Callable.class))).thenReturn(userFuture)

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync2("userId")

        // Verify the results
        verify(mockFoo).info("getUserWithIdAsync2()")
    }
}