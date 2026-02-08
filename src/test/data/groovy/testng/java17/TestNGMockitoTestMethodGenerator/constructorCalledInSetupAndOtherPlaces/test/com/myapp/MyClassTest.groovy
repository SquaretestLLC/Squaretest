package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private UserServiceAdapter theMockUserServiceAdapter
    @Mock
    private Logger theMockLogger
    @Mock
    private Foo theMockFoo

    private MyClass myClass

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClass = new MyClass(theMockUserServiceAdapter, MoreExecutors.newDirectExecutorService(), theMockLogger,
                theMockFoo)
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        when(theMockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClass.getAllUsersSync()

        // Verify the results
        verify(theMockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersSync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(theMockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClass.getAllUsersSync()

        // Verify the results
        assert [] == result
        verify(theMockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        when(theMockUserServiceAdapter.getUsers()).thenReturn([new User()])

        // Run the test
        def result = myClass.getAllUsersAsync()

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testGetAllUsersAsync_UserServiceAdapterReturnsNoItems() {
        // Setup
        when(theMockUserServiceAdapter.getUsers()).thenReturn([])

        // Run the test
        def result = myClass.getAllUsersAsync()

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClass.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync3() {
        // Setup
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClass.getUserWithIdAsync("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync()")
    }

    @Test
    void testGetUserWithIdAsync11() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(theMockFoo).submit(any(Callable.class))
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClass.getUserWithIdAsync1("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync1()")
    }

    @Test
    void testGetUserWithIdAsync1_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        def userFuture = CompletableFuture.failedFuture(new Exception("message"))
        when(theMockFoo.submit(any(Callable.class))).thenReturn(userFuture)

        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClass.getUserWithIdAsync1("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync1()")
    }

    @Test
    void testGetUserWithIdAsync21() {
        // Setup
        doAnswer({ InvocationOnMock invocation ->
            def callable = (Callable<?>) invocation.getArguments()[0]
            return CompletableFuture.completedFuture(callable.call())
        }).when(theMockFoo).submit(any(Callable.class))
        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClass.getUserWithIdAsync2("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()")
    }

    @Test
    void testGetUserWithIdAsync2_FooReturnsFailure() {
        // Setup
        // Configure Foo.submit(...).
        def userFuture = CompletableFuture.failedFuture(new Exception("message"))
        when(theMockFoo.submit(any(Callable.class))).thenReturn(userFuture)

        when(theMockUserServiceAdapter.getUserWithId("userId")).thenReturn(new User())

        // Run the test
        def result = myClass.getUserWithIdAsync2("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()")
    }
}