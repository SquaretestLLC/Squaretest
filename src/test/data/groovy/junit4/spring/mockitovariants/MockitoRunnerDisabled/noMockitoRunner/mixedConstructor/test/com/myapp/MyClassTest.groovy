package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.junit.Assert.assertNull
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L)
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
