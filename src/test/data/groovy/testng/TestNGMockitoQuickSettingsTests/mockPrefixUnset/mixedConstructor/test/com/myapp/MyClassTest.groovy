package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory socketFactory

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), socketFactory, 0L)
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
