package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testGetTheStringg1() {
        assertThatThrownBy(() -> myClassUnderTest.getTheStringg1("id"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testGetTheString2() {
        assertThatThrownBy(() -> myClassUnderTest.getTheString2("id")).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testGetTheString3() {
        assertThat(myClassUnderTest.getTheString3("id")).isEqualTo("id");
    }

    @Test
    public void testGetTheString4() {
        assertThatThrownBy(() -> myClassUnderTest.getTheString4(new FooData()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testGetTheString5() {
        assertThatThrownBy(() -> myClassUnderTest.getTheString5(new FooData())).isInstanceOf(RuntimeException.class);
    }
}
