package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

@CompileStatic
class MyClassTest {

    private MyClass<String, String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testLeftGetterAndSetter() {
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assert left == myClassUnderTest.getKey()
    }

    @Test
    void testGetValue() {
        assert "result" == myClassUnderTest.getValue()
    }

    @Test
    void testSetValue() {
        assert "result" == myClassUnderTest.setValue("setValueParam")
    }

    @Test(expected = UnsupportedOperationException.class)
    void testSetValue_ThrowsUnsupportedOperationException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expected = ClassCastException.class)
    void testSetValue_ThrowsClassCastException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expected = NullPointerException.class)
    void testSetValue_ThrowsNullPointerException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expected = IllegalArgumentException.class)
    void testSetValue_ThrowsIllegalArgumentException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expected = IllegalStateException.class)
    void testSetValue_ThrowsIllegalStateException() {
        myClassUnderTest.setValue("setValueParam")
    }
}
