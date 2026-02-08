package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
