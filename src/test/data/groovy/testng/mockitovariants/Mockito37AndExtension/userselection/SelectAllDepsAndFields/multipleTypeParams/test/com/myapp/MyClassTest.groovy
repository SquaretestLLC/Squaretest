package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertThrows

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
