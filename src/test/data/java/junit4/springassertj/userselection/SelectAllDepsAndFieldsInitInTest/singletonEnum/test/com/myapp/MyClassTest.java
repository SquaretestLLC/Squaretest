package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testCreateNewConnection() {
        assertThat(MyClass.INSTANCE.createNewConnection()).isNull();
    }

    @Test
    public void testDoSomething() {
        MyClass.INSTANCE.doSomething();
    }
}
