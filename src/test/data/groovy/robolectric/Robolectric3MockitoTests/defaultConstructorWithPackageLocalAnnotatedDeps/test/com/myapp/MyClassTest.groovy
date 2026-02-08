package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner

import static org.junit.Assert.assertNull
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass()
        myClassUnderTest.mExecutorService = MoreExecutors.newDirectExecutorService()
        myClassUnderTest.mSocketFactory = mockSocketFactory
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
