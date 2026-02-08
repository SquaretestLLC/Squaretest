package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
