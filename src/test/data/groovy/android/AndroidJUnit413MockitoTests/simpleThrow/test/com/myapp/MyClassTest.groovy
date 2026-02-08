package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertThrows

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
