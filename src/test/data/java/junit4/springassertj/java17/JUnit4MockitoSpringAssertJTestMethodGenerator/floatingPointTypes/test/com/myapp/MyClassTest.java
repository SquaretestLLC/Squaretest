package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testAddDoubles1() {
        assertThat(myClassUnderTest.addDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testAddBigDoubles1() {
        assertThat(myClassUnderTest.addBigDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testAddFloats1() {
        assertThat(myClassUnderTest.addFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    public void testAddBigFloats1() {
        assertThat(myClassUnderTest.addBigFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    public void testStaticAddDoubles1() {
        assertThat(MyClass.staticAddDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testStaticAddBigDoubles1() {
        assertThat(MyClass.staticAddBigDoubles(0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    public void testStaticAddFloats1() {
        assertThat(MyClass.staticAddFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    public void testStaticAddBigFloats1() {
        assertThat(MyClass.staticAddBigFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }
}