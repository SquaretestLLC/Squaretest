package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testDoSomethingCool1() {
        myClassUnderTest.doSomethingCool()
    }

    @Test
    void testDoSomethingInC1() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result")
    }
}