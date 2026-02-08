package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L)
    }

    @Test
    void testCreateNewConnection() {
        assertThat(myClassUnderTest.createNewConnection()).isNull()
    }
}
