package com.myapp;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(TestSubscriber.create(), TestSubscriber.create(),
                Flowable.just(Arrays.asList("value")));
    }

    @Test
    void testDoSomething() {
        myClassUnderTest.doSomething("key");
    }
}
