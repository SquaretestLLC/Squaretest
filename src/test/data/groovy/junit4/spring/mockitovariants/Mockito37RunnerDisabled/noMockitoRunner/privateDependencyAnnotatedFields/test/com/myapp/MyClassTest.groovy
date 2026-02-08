package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.util.ReflectionTestUtils

import static org.junit.Assert.assertNull
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    @InjectMocks
    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        ReflectionTestUtils.setField(myClassUnderTest, "mExecutorService", MoreExecutors.newDirectExecutorService())
        // TODO: Set the following fields: mSocketFactory.
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
