package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.util.concurrent.CompletableFuture

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
    void testProcessFoo0() {
        myClassUnderTest.processFoo0(new FooWithFutureConstructor(CompletableFuture.completedFuture(null)))
    }

    @Test
    void testProcessFoo1() {
        myClassUnderTest.processFoo1(null)
    }

    @Test
    void testProcessFoo2() {
        myClassUnderTest.processFoo2(new FooWithMultipleConstrucotrs("str"))
    }

    @Test
    void testProcessFoo4() {
        myClassUnderTest.processFoo4(null)
    }

    @Test
    void testProcessFoo3() {
        myClassUnderTest.processFoo3(FooWithMultipleStaticInitMethods.fromStr("str"))
    }
}
