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

    @Test
    void testStore1() {
        myClassUnderTest.store1(new FooData())
    }

    @Test
    void testStore2() {
        myClassUnderTest.store2("fooData")
    }

    @Test
    void testStore3() {
        myClassUnderTest.store3(new SearchParams(), new FooData())
    }

    @Test
    void testStore4() {
        myClassUnderTest.store4(null, new FooData())
    }

    @Test
    void testStore5() {
        MyClass.store5(new FooData())
    }

    @Test
    void testStore6() {
        MyClass.store6("fooData")
    }

    @Test
    void testStore7() {
        MyClass.store7(new SearchParams(), new FooData())
    }

    @Test
    void testStore8() {
        MyClass.store8(null, new FooData())
    }
}
