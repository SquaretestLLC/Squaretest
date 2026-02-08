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

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testGetTheStringg1() {
        myClassUnderTest.getTheStringg1("id")
    }

    @Test(expectedExceptions = [IllegalStateException.class])
    void testGetTheString2() {
        myClassUnderTest.getTheString2("id")
    }

    @Test
    void testGetTheString3() {
        assert "id" == myClassUnderTest.getTheString3("id")
    }

    @Test(expectedExceptions = [UnsupportedOperationException.class])
    void testGetTheString4() {
        myClassUnderTest.getTheString4(new FooData())
    }

    @Test(expectedExceptions = [RuntimeException.class])
    void testGetTheString5() {
        myClassUnderTest.getTheString5(new FooData())
    }
}
