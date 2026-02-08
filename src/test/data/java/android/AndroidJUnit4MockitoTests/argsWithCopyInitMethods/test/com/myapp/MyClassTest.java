package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
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
