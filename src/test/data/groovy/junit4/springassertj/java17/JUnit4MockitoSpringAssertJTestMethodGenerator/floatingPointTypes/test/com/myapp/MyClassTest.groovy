package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.within

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testAddDoubles1() {
        assertThat(myClassUnderTest.addDoubles(0.0d, 0.0d)).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testAddBigDoubles1() {
        assertThat(myClassUnderTest.addBigDoubles(0.0d, 0.0d)).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testAddFloats1() {
        assertThat(myClassUnderTest.addFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f))
    }

    @Test
    void testAddBigFloats1() {
        assertThat(myClassUnderTest.addBigFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f))
    }

    @Test
    void testStaticAddDoubles1() {
        assertThat(MyClass.staticAddDoubles(0.0d, 0.0d)).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testStaticAddBigDoubles1() {
        assertThat(MyClass.staticAddBigDoubles(0.0d, 0.0d)).isEqualTo(0.0d, within(0.0001))
    }

    @Test
    void testStaticAddFloats1() {
        assertThat(MyClass.staticAddFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f))
    }

    @Test
    void testStaticAddBigFloats1() {
        assertThat(MyClass.staticAddBigFloats(0.0f, 0.0f)).isEqualTo(0.0f, within(0.0001f))
    }
}