package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assert left == myClassUnderTest.getKey()
    }

    @Test
    void testGetValue() {
        def myClassUnderTest = new MyClass<>()
        assert "result" == myClassUnderTest.getValue()
    }

    @Test
    void testSetValue() {
        def myClassUnderTest = new MyClass<>()
        assert "result" == myClassUnderTest.setValue("setValueParam")
    }
}
