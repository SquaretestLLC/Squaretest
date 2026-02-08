package com.myapp;

import org.junit.jupiter.api.Test;

class MyClassTest {

    @Test
    void testStore1() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store1(new FooData());
    }

    @Test
    void testStore2() throws Exception {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store2("fooData");
    }

    @Test
    void testStore3() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store3(new SearchParams(), new FooData());
    }

    @Test
    void testStore4() {
        final MyClass myClassUnderTest = new MyClass();
        myClassUnderTest.store4(null, new FooData());
    }

    @Test
    void testStore5() {
        MyClass.store5(new FooData());
    }

    @Test
    void testStore6() {
        MyClass.store6("fooData");
    }

    @Test
    void testStore7() {
        MyClass.store7(new SearchParams(), new FooData());
    }

    @Test
    void testStore8() {
        MyClass.store8(null, new FooData());
    }
}
