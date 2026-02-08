package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.logging.Logger

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.*

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private Logger mockLogger

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockUserServiceAdapter, MoreExecutors.newDirectExecutorService(),
                MoreExecutors.directExecutor(), mockLogger, 0)
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
        assertThat(result).isEqualTo([])
        then(mockLogger).should().info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())
        given(mockUserServiceAdapter.getUsers()).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
        then(mockLogger).should().warning("Warn: getAllUsersAsync()")
        then(mockLogger).should().fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())
        given(mockUserServiceAdapter.getUsers()).willReturn([])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
        then(mockLogger).should().warning("Warn: getAllUsersAsync()")
        then(mockLogger).should().fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsyncWithExecutor() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())
        given(mockUserServiceAdapter.getUsers()).willReturn([new User()])

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
        then(mockLogger).should().warning("Warn: getAllUsersAsync()")
        then(mockLogger).should().fine("Fine: getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsyncWithExecutor_UserServiceAdapterGetUsersReturnsNoItems() {
        // Setup
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())
        given(mockUserServiceAdapter.getUsers()).willReturn([])

        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
        then(mockLogger).should().warning("Warn: getAllUsersAsync()")
        then(mockLogger).should().fine("Fine: getAllUsersAsync()")
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
        given(mockUserServiceAdapter.getUserWithId("userId")).willReturn(new User())

        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
        then(mockLogger).should().info("getUserWithIdAsync()")
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep_UserServiceAdapterThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows()

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
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep_UserServiceAdapterThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCall2MethodsThatThrow() {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow()

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
        then(mockUserServiceAdapter).should().doSomethingThatThrows1()
    }

    @Test
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.call2MethodsThatThrow()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows1()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.call2MethodsThatThrow()
        }).isInstanceOf(IOException.class)
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
    }

    @Test
    void testSafeCall2MethodsThatThrow() {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow()

        // Verify the results
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
        then(mockUserServiceAdapter).should().doSomethingThatThrows1()
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrowsThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.safeCall2MethodsThatThrow()
        }).isInstanceOf(RuntimeException.class)
    }

    @Test
    void testSafeCall2MethodsThatThrow_UserServiceAdapterDoSomethingThatThrows1ThrowsIOException() {
        // Setup
        willThrow(IOException.class).given(mockUserServiceAdapter).doSomethingThatThrows1()

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.safeCall2MethodsThatThrow()
        }).isInstanceOf(RuntimeException.class)
        then(mockUserServiceAdapter).should().doSomethingThatThrows()
    }
}
