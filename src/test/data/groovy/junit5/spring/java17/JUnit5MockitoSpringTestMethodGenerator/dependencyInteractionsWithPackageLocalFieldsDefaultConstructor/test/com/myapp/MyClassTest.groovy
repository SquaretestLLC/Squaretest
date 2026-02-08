package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.invocation.InvocationOnMock

import java.sql.SQLException
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.logging.Logger

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.userServiceAdapter = mock(UserServiceAdapter.class)
        myClassUnderTest.executorService = mock(ExecutorService.class)
        myClassUnderTest.logger = mock(Logger.class)
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        verify(myClassUnderTest.logger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        assert [] == result
        verify(myClassUnderTest.logger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(myClassUnderTest.executorService).submit(any(Callable.class))
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(myClassUnderTest.logger).info("getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(myClassUnderTest.executorService).submit(any(Callable.class))
        when(myClassUnderTest.userServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(myClassUnderTest.logger).info("getAllUsersAsync()")
    }

    @Test
    void testStoreUserSync1() {
        // Setup
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).putUser(any(User.class))
    }

    @Test
    void testStoreUserAsync1() {
        // Setup
        def user = new User()
        doAnswer({ InvocationOnMock invocation ->
            ((Runnable) invocation.getArguments()[0]).run()
            return CompletableFuture.completedFuture(null)
        }).when(myClassUnderTest.executorService).submit(any(Runnable.class))

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).putUser(any(User.class))
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        when(myClassUnderTest.userServiceAdapter.getUserWithId("userId")).thenReturn(new User())

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
        }).when(myClassUnderTest.executorService).submit(any(Callable.class))
        when(myClassUnderTest.userServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        verify(myClassUnderTest.logger).info("getUserWithIdAsync()")
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).doSomethingThatThrows()
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException1() {
        // Setup
        doThrow(IOException.class).when(myClassUnderTest.userServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
        })
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
        verify(myClassUnderTest.userServiceAdapter).doSomethingThatThrows()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException1() {
        // Setup
        doThrow(IOException.class).when(myClassUnderTest.userServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
        })
    }
}