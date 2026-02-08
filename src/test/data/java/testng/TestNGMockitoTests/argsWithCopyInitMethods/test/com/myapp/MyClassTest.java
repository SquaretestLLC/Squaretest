package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testProcessFoo0() throws Exception {
        myClassUnderTest.processFoo0(new FooWithFutureConstructor(CompletableFuture.completedFuture(null)));
    }

    @Test
    public void testProcessFoo1() {
        myClassUnderTest.processFoo1(null);
    }

    @Test
    public void testProcessFoo2() {
        myClassUnderTest.processFoo2(new FooWithMultipleConstrucotrs("str"));
    }

    @Test
    public void testProcessFoo4() {
        myClassUnderTest.processFoo4(null);
    }

    @Test
    public void testProcessFoo3() {
        myClassUnderTest.processFoo3(FooWithMultipleStaticInitMethods.fromStr("str"));
    }
}
