package com.myapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Test
    public void testStore1() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store1(new FooData());
    }

    @Test
    public void testStore2() throws Exception {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store2("fooData");
    }

    @Test
    public void testStore3() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store3(new SearchParams(), new FooData());
    }

    @Test
    public void testStore4() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store4(null, new FooData());
    }

    @Test
    public void testStore5() {
        MyClass.store5(new FooData());
    }

    @Test
    public void testStore6() {
        MyClass.store6("fooData");
    }

    @Test
    public void testStore7() {
        MyClass.store7(new SearchParams(), new FooData());
    }

    @Test
    public void testStore8() {
        MyClass.store8(null, new FooData());
    }
}
