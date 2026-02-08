package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.concurrent.CompletableFuture

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
