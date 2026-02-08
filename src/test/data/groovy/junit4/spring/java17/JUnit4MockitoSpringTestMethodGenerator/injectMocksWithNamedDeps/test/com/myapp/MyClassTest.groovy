package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner

import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private ExecutorService mainExecService
    @Mock
    private ExecutorService secondExecService
    @Mock
    private Logger mockLogger

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mainExecService, secondExecService, mockLogger)
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        assert [] == result
        verify(mockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mainExecService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mainExecService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync1() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(secondExecService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()")
    }
}