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

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.*

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
        given(mockUserServiceAdapter.getUsers()).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUsers()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
        assert [] == result
        then(mockLogger).should().info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        willAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def settableFuture = SettableFuture.create()
            settableFuture.set(callable.call())
            return settableFuture
        }).given(mockExecutorService).submit(any(Callable.class))
        given(mockUserServiceAdapter.getUsers()).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        willAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def settableFuture = SettableFuture.create()
            settableFuture.set(callable.call())
            return settableFuture
        }).given(mockExecutorService).submit(any(Callable.class))
        given(mockUserServiceAdapter.getUsers()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
    }

    @Test
    void testStoreUserSync() {
        // Setup
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
        then(mockUserServiceAdapter).should().putUser(any(User.class))
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        def user = new User()
        willAnswer({ InvocationOnMock invocation ->
            ((Runnable) invocation.getArguments()[0]).run()
            def settableFuture = SettableFuture.create()
            settableFuture.set(null)
            return settableFuture
        }).given(mockExecutorService).submit(any(Runnable.class))

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
        then(mockUserServiceAdapter).should().putUser(any(User.class))
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        willAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            def settableFuture = SettableFuture.create()
            settableFuture.set(callable.call())
            return settableFuture
        }).given(mockExecutorService).submit(any(Callable.class))
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        then(mockLogger).should().info("getUserWithIdAsync()")
    }

    @Test
    void testDoSomethingWithCompletable() {
        // Setup
        willAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).given(mockFoo).submitWithCompletable(any(Callable.class))
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())

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
        given(mockFoo.submitWithCompletable(any(Callable.class))).willReturn(userCompletableFuture)

        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())

        // Run the test
        def result = myClassUnderTest.doSomethingWithCompletable("userId")

        // Verify the results
    }

    @Test(expected = RuntimeException.class)
    void testDoSomethingWithCompletable_FooThrowsException() {
        // Setup
        given(mockFoo.submitWithCompletable(any(Callable.class))).willThrow(Exception.class)

        // Run the test
        myClassUnderTest.doSomethingWithCompletable("userId")
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
    }

    @Test(expected = IOException.class)
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
    }

    @Test(expected = SQLException.class)
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
    }
}
