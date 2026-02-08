package com.myapp


import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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