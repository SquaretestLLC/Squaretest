package com.myapp


import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import java.util.logging.Logger

import static org.mockito.Mockito.verify
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
        // Run the test
        def result = myClass.getUserWithIdAsync1("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync1()")
    }

    @Test
    void testGetUserWithIdAsync21() {
        // Setup
        // Run the test
        def result = myClass.getUserWithIdAsync2("userId")

        // Verify the results
        verify(theMockLogger).info("getUserWithIdAsync2()")
    }
}