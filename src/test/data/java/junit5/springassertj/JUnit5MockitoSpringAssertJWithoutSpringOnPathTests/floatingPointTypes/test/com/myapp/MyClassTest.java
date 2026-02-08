package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testAddDoubles() {
        assertThat(myClassUnderTest.addDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testAddBigDoubles() {
        assertThat(myClassUnderTest.addBigDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testAddFloats() {
        assertThat(myClassUnderTest.addFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testAddBigFloats() {
        assertThat(myClassUnderTest.addBigFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testStaticAddDoubles() {
        assertThat(MyClass.staticAddDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testStaticAddBigDoubles() {
        assertThat(MyClass.staticAddBigDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testStaticAddFloats() {
        assertThat(MyClass.staticAddFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testStaticAddBigFloats() {
        assertThat(MyClass.staticAddBigFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }
}
