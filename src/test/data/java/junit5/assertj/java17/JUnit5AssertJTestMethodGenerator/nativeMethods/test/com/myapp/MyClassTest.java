package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomethingCool1() {
        myClassUnderTest.doSomethingCool();
    }

    @Test
    void testDoSomethingInC1() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result");
    }
}