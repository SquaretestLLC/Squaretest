package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.mock

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.mSocketFactory = mock(SocketImplFactory.class)
    }

    @Test
    void testCreateNewConnection1() {
        assertThat(myClassUnderTest.createNewConnection()).isNull()
    }
}