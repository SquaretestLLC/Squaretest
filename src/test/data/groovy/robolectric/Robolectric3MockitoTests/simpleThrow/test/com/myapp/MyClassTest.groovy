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

    @Test(expected = UnsupportedOperationException.class)
    void testGetTheStringg1() {
        myClassUnderTest.getTheStringg1("id")
    }

    @Test(expected = IllegalStateException.class)
    void testGetTheString2() {
        myClassUnderTest.getTheString2("id")
    }

    @Test
    void testGetTheString3() {
        assert "id" == myClassUnderTest.getTheString3("id")
    }

    @Test(expected = UnsupportedOperationException.class)
    void testGetTheString4() {
        myClassUnderTest.getTheString4(new FooData())
    }

    @Test(expected = RuntimeException.class)
    void testGetTheString5() {
        myClassUnderTest.getTheString5(new FooData())
    }
}
