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
    void testDoSomethingCool1() {
        myClassUnderTest.doSomethingCool()
    }

    @Test
    void testDoSomethingInC1() {
        assert "result" == myClassUnderTest.doSomethingInC()
    }
}