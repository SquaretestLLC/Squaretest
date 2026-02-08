package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import java.util.concurrent.ExecutorService

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService
    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L)
    }

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        def result = MyClass.getInstance()

        // Verify the results
    }

    @Test
    void testCreateNewConnection() {
        // Setup
        when(mockSocketFactory.createSocketImpl()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.createNewConnection()

        // Verify the results
    }
}
