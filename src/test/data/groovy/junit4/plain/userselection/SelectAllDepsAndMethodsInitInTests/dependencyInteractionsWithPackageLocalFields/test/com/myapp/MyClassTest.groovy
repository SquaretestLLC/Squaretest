package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner

import java.sql.SQLException
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private ExecutorService mockExecutorService
    @Mock
    private Logger mockLogger

    @Test
    void testGetAllUsersSync() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        verify(mockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        assert [] == result
        verify(mockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockExecutorService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockExecutorService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testStoreUserSync() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class))
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        def user = new User()
        doAnswer({ InvocationOnMock invocation ->
            ((Runnable) invocation.getArguments()[0]).run()
            return CompletableFuture.completedFuture(null)
        }).when(mockExecutorService).submit(any(Runnable.class))

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class))
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockExecutorService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()")
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
    }

    @Test(expected = IOException.class)
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
    }

    @Test(expected = SQLException.class)
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mockUserServiceAdapter
        myClassUnderTest.executorService = mockExecutorService
        myClassUnderTest.logger = mockLogger
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
    }
}
