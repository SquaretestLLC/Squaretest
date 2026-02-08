package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        def myClassUnderTest = new MyClass<>()
        def left = "setKeyParam"
        myClassUnderTest.setKey(left)
        assertThat(myClassUnderTest.getKey()).isEqualTo(left)
    }

    @Test
    void testGetValue() {
        def myClassUnderTest = new MyClass<>()
        assertThat(myClassUnderTest.getValue()).isEqualTo("result")
    }

    @Test
    void testSetValue() {
        def myClassUnderTest = new MyClass<>()
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
