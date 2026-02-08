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
import java.util.logging.Logger

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private Logger theMockLogger

    private MyClass myClass

    @Before
    void setUp() {
        initMocks(this)
        myClass = new MyClass()
        myClass.foo = mock(Foo.class)
        myClass.logger = theMockLogger
    }

    @Test
    void testGetAllUsersSync1() {
        // Setup
        // Run the test
        def result = myClass.getAllUsersSync()

        // Verify the results
        verify(theMockLogger).info("getAllUsersSync()")
    }

    @Test
    void testGetAllUsersAsync1() {
        // Setup
        // Run the test
        def result = myClass.getAllUsersAsync()

        // Verify the results
        verify(theMockLogger).info("getAllUsersAsync()")
    }

    @Test
    void testGetUserWithIdSync1() {
        // Setup
        // Run the test
        def result = myClass.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync3() {
        // Setup
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
        }).when(myClass.foo).submit(any(Callable.class))

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
        when(myClass.foo.submit(any(Callable.class))).thenReturn(userFuture)

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
        }).when(myClass.foo).submit(any(Callable.class))

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
        when(myClass.foo.submit(any(Callable.class))).thenReturn(userFuture)

        // Run the test
        def result = myClass.getUserWithIdAsync2("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()")
    }
}