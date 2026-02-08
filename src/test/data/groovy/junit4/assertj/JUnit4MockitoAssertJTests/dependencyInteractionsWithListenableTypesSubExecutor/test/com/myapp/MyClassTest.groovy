package com.myapp

import com.google.common.util.concurrent.SettableFuture
import com.myapp.exec.SubListeningExecutorService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner

import java.sql.SQLException
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.logging.Logger

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private SubListeningExecutorService mockExecutorService
    @Mock
    private Logger mockLogger
    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockUserServiceAdapter, mockExecutorService, mockLogger, mockFoo)
    }

    @Test
    void testGetAllUsersSync() {
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
        assertThat(result).isEqualTo([])
        verify(mockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def settableFuture = SettableFuture.create()
            settableFuture.set(callable.call())
            return settableFuture
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
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def settableFuture = SettableFuture.create()
            settableFuture.set(callable.call())
            return settableFuture
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
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class))
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        def user = new User()
        doAnswer({ InvocationOnMock invocation ->
            ((Runnable) invocation.getArguments()[0]).run()
            def settableFuture = SettableFuture.create()
            settableFuture.set(null)
            return settableFuture
        }).when(mockExecutorService).submit(any(Runnable.class))

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
        verify(mockUserServiceAdapter).putUser(any(User.class))
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def settableFuture = SettableFuture.create()
            settableFuture.set(callable.call())
            return settableFuture
        }).when(mockExecutorService).submit(any(Callable.class))
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()")
    }

    @Test
    void testDoSomethingWithCompletable() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(mockFoo).submitWithCompletable(any(Callable.class))
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.doSomethingWithCompletable("userId")

        // Verify the results
    }

    @Test
    void testDoSomethingWithCompletable_FooReturnsFailure() {
        // Setup
        // Configure Foo.submitWithCompletable(...).
        def userCompletableFuture = new CompletableFuture<>()
        userCompletableFuture.completeExceptionally(new Exception("message"))
        when(mockFoo.submitWithCompletable(any(Callable.class))).thenReturn(userCompletableFuture)

        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.doSomethingWithCompletable("userId")

        // Verify the results
    }

    @Test
    void testDoSomethingWithCompletable_FooThrowsException() {
        // Setup
        when(mockFoo.submitWithCompletable(any(Callable.class))).thenThrow(Exception.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomethingWithCompletable("userId")
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
        }).isInstanceOf(SQLException.class)
    }
}
