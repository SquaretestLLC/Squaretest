package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testCreateNewConnection() {
        assertThat(MyClass.INSTANCE.createNewConnection()).isNull();
    }

    @Test
    void testDoSomething() {
        MyClass.INSTANCE.doSomething();
    }
}
