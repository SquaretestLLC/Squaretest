package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    public void testLeftGetterAndSetter() {
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertThat(myClassUnderTest.getKey()).isEqualTo(left);
    }

    @Test
    public void testGetValue() {
        assertThat(myClassUnderTest.getValue()).isEqualTo("result");
    }

    @Test
    public void testSetValue() {
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
