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
        // Run the test
        def result = myClassUnderTest.getAllUsersSync()

        // Verify the results
    }

    @Test
    void testGetAllUsersAsync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getAllUsersAsync()

        // Verify the results
    }

    @Test
    void testGetAllUsersAsyncWithExecutor() {
        // Setup
        // Run the test
        myClassUnderTest.getAllUsersAsyncWithExecutor()

        // Verify the results
    }

    @Test
    void testStoreUserSync() {
        // Setup
        def user = new User()

        // Run the test
        myClassUnderTest.storeUserSync(user)

        // Verify the results
    }

    @Test
    void testStoreUserAsync() {
        // Setup
        def user = new User()

        // Run the test
        def result = myClassUnderTest.storeUserAsync(user)

        // Verify the results
    }

    @Test
    void testGetUserWithIdSync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdSync("userId")

        // Verify the results
    }

    @Test
    void testGetUserWithIdAsync() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUserWithIdAsync("userId")

        // Verify the results
    }

    @Test
    void testDoSomethingThatThrowsSameExceptionAsDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testDoSomethingThatThrowsSameExceptionAsDep_ThrowsIOException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsSameExceptionAsDep()
    }

    @Test
    void testDoSomethingThatThrowsDifferentExceptionThanDep() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()

        // Verify the results
    }

    @Test(expected = SQLException.class)
    void testDoSomethingThatThrowsDifferentExceptionThanDep_ThrowsSQLException() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingThatThrowsDifferentExceptionThanDep()
    }

    @Test
    void testCall2MethodsThatThrow() {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow()

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testCall2MethodsThatThrow_ThrowsIOException() {
        // Setup
        // Run the test
        myClassUnderTest.call2MethodsThatThrow()
    }

    @Test
    void testSafeCall2MethodsThatThrow() {
        // Setup
        // Run the test
        myClassUnderTest.safeCall2MethodsThatThrow()

        // Verify the results
    }
}
