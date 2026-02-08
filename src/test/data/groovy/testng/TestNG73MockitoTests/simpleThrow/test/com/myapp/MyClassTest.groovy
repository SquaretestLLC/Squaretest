package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.testng.Assert.assertThrows

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testGetTheStringg1() {
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.getTheStringg1("id")
        })
    }

    @Test
    void testGetTheString2() {
        assertThrows(IllegalStateException.class, {
            myClassUnderTest.getTheString2("id")
        })
    }

    @Test
    void testGetTheString3() {
        assert "id" == myClassUnderTest.getTheString3("id")
    }

    @Test
    void testGetTheString4() {
        assertThrows(UnsupportedOperationException.class, {
            myClassUnderTest.getTheString4(new FooData())
        })
    }

    @Test
    void testGetTheString5() {
        assertThrows(RuntimeException.class, {
            myClassUnderTest.getTheString5(new FooData())
        })
    }
}
