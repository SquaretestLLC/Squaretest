package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
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
