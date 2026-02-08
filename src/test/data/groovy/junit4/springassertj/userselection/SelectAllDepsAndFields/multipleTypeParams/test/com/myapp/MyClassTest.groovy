package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    private MyClass<String, String> myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass<>()
    }

    @Test
    void testLeftGetterAndSetter() {
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assertThat(myClassUnderTest.getKey()).isEqualTo(left)
    }

    @Test
    void testGetValue() {
        assertThat(myClassUnderTest.getValue()).isEqualTo("result")
    }

    @Test
    void testSetValue() {
        assertThat(myClassUnderTest.setValue("setValueParam")).isEqualTo("result")
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(UnsupportedOperationException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(ClassCastException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(NullPointerException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(IllegalArgumentException.class)
        assertThatThrownBy({
            myClassUnderTest.setValue("setValueParam")
        }).isInstanceOf(IllegalStateException.class)
    }
}
