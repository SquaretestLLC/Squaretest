package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingCool() {
        myClassUnderTest.doSomethingCool();
    }

    @Test
    public void testDoSomethingInC() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result");
    }
}
