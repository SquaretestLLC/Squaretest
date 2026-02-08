package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

import static org.junit.Assert.assertNull
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    @InjectMocks
    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
