package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertNull
import static org.mockito.Mockito.mock

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.mSocketFactory = mock(SocketImplFactory.class)
    }

    @Test
    void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}