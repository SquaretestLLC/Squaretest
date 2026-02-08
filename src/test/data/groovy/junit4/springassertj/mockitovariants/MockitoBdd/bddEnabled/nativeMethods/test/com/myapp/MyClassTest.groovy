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
    void testDoSomethingCool() {
        myClassUnderTest.doSomethingCool()
    }

    @Test
    void testDoSomethingInC() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result")
    }
}
