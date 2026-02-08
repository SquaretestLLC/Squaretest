package com.myapp


import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import java.util.concurrent.ExecutorService

import static org.mockito.Mockito.mock
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorServiceToUseForThings
    @Mock
    private Object mockDefaultListener

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockExecutorServiceToUseForThings, mockDefaultListener)
    }

    @Test
    void testPerformOperation1() {
        // Setup
        def mockListener = mock(Object.class)

        // Run the test
        myClassUnderTest.performOperation(mockListener)

        // Verify the results
    }
}