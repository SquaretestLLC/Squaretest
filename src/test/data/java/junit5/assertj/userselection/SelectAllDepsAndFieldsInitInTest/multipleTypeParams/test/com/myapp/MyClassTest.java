package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertThat(myClassUnderTest.getKey()).isEqualTo(left);
    }

    @Test
    void testGetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertThat(myClassUnderTest.getValue()).isEqualTo("result");
    }

    @Test
    void testSetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertThat(myClassUnderTest.setValue("setValueParam")).isEqualTo("result");
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam"))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam")).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam")).isInstanceOf(IllegalStateException.class);
    }
}
