package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import java.sql.SQLException
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private Logger mockLogger

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(),
                MoreExecutors.directExecutor(), mockLogger, 0)
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
        assert [] == result
        verify(mockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsyncWithExecutor() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        when(mockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())
        when(mockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor()

        // Verify the results
        verify(mockLogger).info("getAllUsersAsync()")
        verify(mockLogger).warning("Warn: getAllUsersAsync()")
        verify(mockLogger).fine("Fine: getAllUsersAsync()")
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
        when(mockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        verify(mockLogger).info("getUserWithIdAsync()")
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
    }

    @Test(expected = IOException.class)
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
    }

    @Test(expected = SQLException.class)
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
    }

    @Test
    void testCall2MethodsThatThrow() {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
        verify(mockUserServiceAdapter).doSomethingThatThrows1()
    }

    @Test(expected = IOException.class)
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.call2MethodsThatThrow()
    }

    @Test(expected = IOException.class)
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1()

        // Run the test
        myClassUnderTest.call2MethodsThatThrow()
    }

    @Test
    void testSafeCall2MethodsThatThrow() {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow()

        // Verify the results
        verify(mockUserServiceAdapter).doSomethingThatThrows()
        verify(mockUserServiceAdapter).doSomethingThatThrows1()
    }

    @Test(expected = RuntimeException.class)
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow()
    }

    @Test(expected = RuntimeException.class)
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() {
        // Setup
        doThrow(IOException.class).when(mockUserServiceAdapter).doSomethingThatThrows1()

        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow()
    }
}
