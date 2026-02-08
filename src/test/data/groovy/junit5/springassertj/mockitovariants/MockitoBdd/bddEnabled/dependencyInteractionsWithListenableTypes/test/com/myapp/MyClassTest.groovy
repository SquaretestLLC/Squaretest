package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock

import java.sql.SQLException
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.logging.Logger

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private UserServiceAdapter mockUserServiceAdapter
    @Mock
    private Logger mockLogger
    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockUserServiceAdapter,
                MoreExecutors.listeningDecorator(MoreExecutors.newDirectExecutorService()), mockLogger, mockFoo)
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
        given(mockUserServiceAdapter.getUsers()).willReturn([new User()])

        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
        then(mockLogger).should().info("getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
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

    @Test
    void testDoSomethingWithCompletable_FooThrowsException() {
        // Setup
        given(mockFoo.submitWithCompletable(any(Callable.class))).willThrow(Exception.class)

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
        }).isInstanceOf(SQLException.class)
    }
}
