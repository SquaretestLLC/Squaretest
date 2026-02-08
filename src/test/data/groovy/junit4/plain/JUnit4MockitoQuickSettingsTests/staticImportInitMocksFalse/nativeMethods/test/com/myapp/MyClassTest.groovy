package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingCool() {
        myClassUnderTest.doSomethingCool()
    }

    @Test
    void testDoSomethingInC() {
        assert "result" == myClassUnderTest.doSomethingInC()
    }
}
