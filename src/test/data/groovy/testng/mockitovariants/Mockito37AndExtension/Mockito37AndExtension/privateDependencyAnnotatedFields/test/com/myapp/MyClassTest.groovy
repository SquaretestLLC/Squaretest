package com.myapp

import groovy.transform.CompileStatic
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.testng.Assert.assertNull

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    @InjectMocks
    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
