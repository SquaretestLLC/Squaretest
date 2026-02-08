package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertThrows

@CompileStatic
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
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.setValue("setValueParam")
        })
        assertThrows(ClassCastException.class, {
            myClassUnderTest.setValue("setValueParam")
        })
        assertThrows(NullPointerException.class, {
            myClassUnderTest.setValue("setValueParam")
        })
        assertThrows(IllegalArgumentException.class, {
            myClassUnderTest.setValue("setValueParam")
        })
        assertThrows(IllegalStateException.class, {
            myClassUnderTest.setValue("setValueParam")
        })
    }
}
