package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass<String, String> myClassUnderTest

    @BeforeMethod
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

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testSetValue_ThrowsUnsupportedOperationException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [ClassCastException.class])
    void testSetValue_ThrowsClassCastException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [NullPointerException.class])
    void testSetValue_ThrowsNullPointerException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [IllegalArgumentException.class])
    void testSetValue_ThrowsIllegalArgumentException() {
        myClassUnderTest.setValue("setValueParam")
    }

    @Test(expectedExceptions = [IllegalStateException.class])
    void testSetValue_ThrowsIllegalStateException() {
        myClassUnderTest.setValue("setValueParam")
    }
}
